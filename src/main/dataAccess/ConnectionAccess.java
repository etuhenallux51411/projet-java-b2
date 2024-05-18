package main.dataAccess;

import main.exception.ConnectionAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionAccess {
    private static String URL = "jdbc:mysql://localhost:3306/social_network";
    private static String USER = "root";
    private static String PASSWORD = "test1234";
    private static Connection connection;

    private static void getConnection() throws ConnectionAccessException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionAccessException(e.getMessage());
        }
    }

    public static Connection getInstance() throws ConnectionAccessException {
        if (connection == null) getConnection();
        return connection;
    }
}
