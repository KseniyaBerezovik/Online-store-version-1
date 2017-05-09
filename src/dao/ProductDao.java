package dao;


import entity.Product;
import connection.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private static ProductDao INSTANCE = null;
    private static final Object LOCK = new Object();

    private ProductDao() {}

    public static ProductDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ProductDao();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Product> save(Product product) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO products (name, description, price, amount, img) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getAmount());
                preparedStatement.setString(5, product.getImg());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void addExistingProduct(long productID, int amount) {
        try(Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            int currentAmount = 0;
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT amount FROM products WHERE id = ?")) {
                preparedStatement.setLong(1, productID);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    currentAmount = resultSet.getInt(1);
                }
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE products SET amount = ? WHERE id = ?")) {
                preparedStatement.setInt(1, currentAmount + amount);
                preparedStatement.setLong(2, productID);
                preparedStatement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM products")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int amount = resultSet.getInt("amount");
                    String img = resultSet.getString("img");
                    products.add(new Product(id, name, description, price, amount, img));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Optional<Product> getByID(long id) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id = ?")){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    int amount = resultSet.getInt(5);
                    String img = resultSet.getString(6);
                    Product product = new Product(id, name, description, price,  amount, img);
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
