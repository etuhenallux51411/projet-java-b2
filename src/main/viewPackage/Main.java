package main.viewPackage;

import main.dataAccessPackage.ConnectionDataAccess;

import java.sql.Statement;
import java.util.Locale;

import main.exceptionPackage.ConnectionDataAccessException;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();

    }


    public static void addCountry() throws ConnectionDataAccessException {
        ConnectionDataAccess connectionDataAccess = new ConnectionDataAccess();
        Connection connection = connectionDataAccess.getInstance();
        try {
            Statement statement = connection.createStatement();
            String[] countries = getAllCountries();
            for (String country : countries) {
                String query = "INSERT INTO country (name) VALUES ('" + country + "')";
                statement.executeUpdate(query);
            }
            System.out.printf("Countries added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static String[] getAllCountries() {
            // Utiliser Locale pour obtenir une liste de tous les pays
            String[] countryCodes = Locale.getISOCountries();
            String[] countryNames = new String[countryCodes.length];

            for (int i = 0; i < countryCodes.length; i++) {
                Locale locale = new Locale("", countryCodes[i]);
                countryNames[i] = locale.getDisplayCountry();
            }
            return countryNames;
        }
}
