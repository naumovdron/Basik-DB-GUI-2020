package lab4;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    private static String url = "jdbc:";
    private static String user = "root";
    private static String password = "localhost";
    private static Connection connection;
}
