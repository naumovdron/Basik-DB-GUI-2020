package lab4.dbwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    private static String url = "jdbc:mysql://localhost:3306/mysql";
    private static String user = "root";
    private static String password = "root";
    private static Connection connection;
}
