package dao;
import entity.Client;
import entity.Order;
import entity.Product;
import other.ConnectionManager;
import other.OrderStatus;

import java.sql.*;
import java.time.LocalDate;

import java.util.*;

public class OrderDao {
    private static OrderDao INSTANCE = null;
    private static final Object LOCK = new Object();

    private OrderDao() {}

    public static OrderDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderDao();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Order> save(Order order, Client client) {
        try(Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO orders (date_of_issue, status, id_client) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setObject(1, order.getDateOfIssue());
                preparedStatement.setObject(2, order.getStatus().toString().toLowerCase());
                preparedStatement.setLong(3, client.getId());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    order.setId(generatedKeys.getLong(1));
                }
            }

            for(Map.Entry<Product, Integer> pair : order.getProducts().entrySet()) {

                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO orders_products (id_order, id_product, amount_products) VALUES (?, ?, ?)")) {
                    preparedStatement.setLong(1, order.getId());
                    preparedStatement.setLong(2, pair.getKey().getId());
                    preparedStatement.setInt(3, pair.getValue());
                    preparedStatement.executeUpdate();
                }

                int numberOfProductsBeforeUpdate = ProductDao.getInstance().getByID(pair.getKey().getId()).get().getAmount();
                int currentNumberOfProducts = numberOfProductsBeforeUpdate - pair.getValue();

                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE products SET amount = ? WHERE id = ?")) {
                    preparedStatement.setInt(1, currentNumberOfProducts);
                    preparedStatement.setLong(2, pair.getKey().getId());
                    preparedStatement.executeUpdate();
                }

            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(order);
    }

    public Set<Order> getOrdersByClient(Client client) {
        Set<Order> resultOrders = new HashSet<>();
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT " +
                            "o.id AS id_order, " +
                            "o.date_of_issue, " +
                            "o.closing_date, " +
                            "o.status, " +
                            "p.id AS id_product, " +
                            "p.name AS product_name, " +
                            "p.description, " +
                            "p.price, " +
                            "op.amount_products AS amount_product_in_order " +
                            "FROM orders AS o  " +
                            "JOIN orders_products AS op ON o.id = op.id_order " +
                            "JOIN products AS p ON p.id = op.id_product " +
                            "WHERE o.id_client = ?")) {
                preparedStatement.setLong(1, client.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultOrders = parseResult(resultSet);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultOrders;
    }

    private Set<Order> parseResult(ResultSet resultSet) throws SQLException {
        Set<Order> resultOrders = new HashSet<>();
        while (resultSet.next()) {
            long idOrder = resultSet.getLong("id_order");
            LocalDate dateOfIssue = LocalDate.parse(resultSet.getObject("date_of_issue").toString());
            LocalDate closingDate;
            if(resultSet.getObject("closing_date") == null) {
                closingDate = null;
            } else {
                closingDate = LocalDate.parse(resultSet.getObject("closing_date").toString());
            }
            OrderStatus status = OrderStatus.valueOf(resultSet.getString("status").toUpperCase());
            Order order = new Order(idOrder, dateOfIssue, closingDate, status);

            long idProduct = resultSet.getLong("id_product");
            String productName = resultSet.getString("product_name");
            String description = resultSet.getString("description");
            double price = resultSet.getDouble("price");
            int amountProductsInOrder = resultSet.getInt("amount_product_in_order");
            Product product = new Product(idProduct, productName, description, price);

            if (resultOrders.contains(order)) {
                for(Order o : resultOrders) {
                    if (o.equals(order)) {
                        o.getProducts().put(product, amountProductsInOrder);
                    }
                }
            } else {
                order.getProducts().put(product, amountProductsInOrder);
                resultOrders.add(order);
            }
        }
        return resultOrders;
    }

    public Set<Order> getOrdersByProduct(Product product) {
        Set<Order> resultOrders = new HashSet<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT  o.id AS id_order, " +
                            "o.date_of_issue, " +
                            "o.closing_date, " +
                            "o.status, " +
                            "p.id AS id_product, " +
                            "p.name AS product_name, " +
                            "p.description, " +
                            "p.price, " +
                            "op.amount_products AS amount_product_in_order " +
                            "FROM products AS p " +
                            "JOIN orders_products AS op ON op.id_product = p.id " +
                            "JOIN orders AS o ON o.id = op.id_order " +
                            "WHERE id_order IN (SELECT id_order FROM orders_products AS op WHERE op.id_product = ?)")) {
                preparedStatement.setLong(1, product.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultOrders = parseResult(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultOrders;
    }

    public void deleteOrdersBeforeDate(LocalDate date) {
        try(Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE op FROM orders_products AS op " +
                            "JOIN orders AS o ON o.id = op.id_order " +
                            "WHERE o.date_of_issue < ?")) {
                preparedStatement.setString(1, date.toString());
                preparedStatement.executeUpdate();
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM orders WHERE date_of_issue < ?")) {
                preparedStatement.setString(1, date.toString());
                preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
