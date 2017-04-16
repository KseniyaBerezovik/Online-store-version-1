package dao;

import entity.Client;
import other.ConnectionManager;
import other.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao {
    private static ClientDao INSTANCE = null;
    private static final Object LOCK = new Object();

    private ClientDao() {}

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Client> save(Client client) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO clients (name, surname, email, pass, phone, address, role) " +
                            "VALUES (?, ?, ?, ?, ?, ? ,?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getSurname());
                preparedStatement.setString(3, client.getEmail());
                preparedStatement.setString(4, client.getPassword());
                preparedStatement.setString(5, client.getPhone());
                preparedStatement.setString(6, client.getAddress());
                preparedStatement.setObject(7, client.getRole().toString());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getLong(1));
                    return Optional.of(client);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clients")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("pass");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    Role role = Role.valueOf(resultSet.getString("role").toUpperCase());
                    clients.add(new Client(id, name, surname, email, password, phone, address, role));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Optional<Client> getByID(long id) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?")){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String email = resultSet.getString(4);
                    String password = resultSet.getString(5);
                    String phone = resultSet.getString(6);
                    String address = resultSet.getString(7);
                    Role role = Role.valueOf(resultSet.getString(8).toUpperCase());
                    Client client = new Client(id, name, surname, email, password, phone, address, role);
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Client> getByEmail(String email) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM clients WHERE email = ?")){
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String password = resultSet.getString(5);
                    String phone = resultSet.getString(6);
                    String address = resultSet.getString(7);
                    Role role = Role.valueOf(resultSet.getString(8).toUpperCase());
                    Client client = new Client(id, name, surname, email, password, phone, address, role);
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Client> getByEmailAndPass(String email, String pass) {
        try(Connection connection = ConnectionManager.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM clients WHERE email = ? AND pass = ?")){
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String phone = resultSet.getString(6);
                    String address = resultSet.getString(7);
                    Role role = Role.valueOf(resultSet.getString(8).toUpperCase());
                    Client client = new Client(id, name, surname, email, pass, phone, address, role);
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
