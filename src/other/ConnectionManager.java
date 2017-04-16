package other;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    public static final String RESOURCE_PATH = "resource" + File.separator;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle(RESOURCE_PATH + "database");
        String url = resource.getString("url");
        String user = resource.getString("username");
        String pass = resource.getString("password");
        return DriverManager.getConnection(url, user, pass); }
}
