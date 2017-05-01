package dao;

import connection.ConnectionManager;
import entity.Cart;
import entity.Client;
import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartDao {
    private static CartDao INSTANCE = null;
    private static final Object LOCK = new Object();

    private CartDao() {}

    public static CartDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new CartDao();
                }
            }
        }
        return INSTANCE;
    }

    public void addProduct(Product product, int amount, Client client) {
        Integer amountProductInBD = getAmountConcreteProductInCart(product, client);
        if(amountProductInBD == 0) {
            try(Connection connection = ConnectionManager.getConnection()){
                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO carts (id_product, amount, id_client) VALUES (?, ?, ?)")) {
                    preparedStatement.setLong(1, product.getId());
                    preparedStatement.setInt(2, amount);
                    preparedStatement.setLong(3, client.getId());
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try(Connection connection = ConnectionManager.getConnection()){
                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE carts SET amount = ? WHERE id_client = ? AND id_product = ?")) {
                    preparedStatement.setInt(1, amount + amountProductInBD);
                    preparedStatement.setLong(2, client.getId());
                    preparedStatement.setLong(3, product.getId());
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteProduct(Product product, int amount, Client client) {
        int amountProductsInBD = getAmountConcreteProductInCart(product, client);
        if(amountProductsInBD < amount) {
            return false;
        }

        if(amountProductsInBD == amount) {
            try(Connection connection = ConnectionManager.getConnection()) {
                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM carts WHERE id_product = ? AND id_client = ?")) {
                    preparedStatement.setLong(1, product.getId());
                    preparedStatement.setLong(2, client.getId());
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(amountProductsInBD > amount) {
            try(Connection connection = ConnectionManager.getConnection()) {
                try(PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE carts SET amount = ? WHERE id_client = ? AND id_product = ?")) {
                    preparedStatement.setInt(1, amountProductsInBD - amount);
                    preparedStatement.setLong(2, client.getId());
                    preparedStatement.setLong(3, product.getId());
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private int getAmountConcreteProductInCart(Product product, Client client) {
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT amount FROM carts WHERE id_product = ? AND id_client = ?")) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setLong(2, client.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    return resultSet.getInt("amount");
                    }
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Cart getCartByClient(Client client) {
        Map<Product, Integer> cart = new HashMap<>();
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id_product, amount FROM carts WHERE id_client = ?")) {
                preparedStatement.setLong(1, client.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Product product = ProductDao.getInstance().getByID(resultSet.getLong("id_product")).get();
                    int amount = resultSet.getInt("amount");
                    cart.put(product, amount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Cart(cart ,client);
    }

    public int getAmountProductsInCart(Client client) {
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT sum(amount) FROM carts WHERE id_client = ?")) {
                preparedStatement.setLong(1, client.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void clear(Client client) {
        try(Connection connection = ConnectionManager.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM carts WHERE id_client = ?")) {
                preparedStatement.setLong(1, client.getId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
