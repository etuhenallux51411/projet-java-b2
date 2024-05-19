package main.dataAccessPackage;

import main.exceptionPackage.ConnectionDataAccessException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataAccess {
    private static String URL = "jdbc:mysql://localhost:3306/social_network";
    private static String USER = "root";
    private static String PASSWORD = "test1234";
    private static Connection connection;

    public static Connection getInstance() throws ConnectionDataAccessException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new ConnectionDataAccessException(e.getMessage());
            }
        }
        return connection;
    }
}
