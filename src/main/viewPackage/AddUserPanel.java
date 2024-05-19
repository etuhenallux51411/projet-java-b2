package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;

public class AddUserPanel extends JPanel {
    private JTextField username, password, email, dateOfBirth, city, phoneNumber, zipCode, street, houseNumber;
    private JComboBox<String> country, gender;

    public AddUserPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding between components

        JLabel label = new JLabel("Add User Panel");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(label, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        addField(gbc, "Username", username = new JTextField(20), 1);
        addField(gbc, "Password", password = new JPasswordField(20), 2);
        addField(gbc, "Email", email = new JTextField(20), 3);
        addField(gbc, "Date of Birth", dateOfBirth = new JTextField(20), 4);
        addField(gbc, "City", city = new JTextField(20), 5);
        addField(gbc, "Phone Number", phoneNumber = new JTextField(20), 6);
        addField(gbc, "Zip Code", zipCode = new JTextField(20), 7);
        addField(gbc, "Street", street = new JTextField(20), 8);
        addField(gbc, "House Number", houseNumber = new JTextField(20), 9);

        // Gender field as an example (you need to populate it accordingly)
        String[] genders = {"Male", "Female", "Other"};
        gender = new JComboBox<>(genders);
        addField(gbc, "Gender", gender, 10);

        // Country field populated from the database
        try {
            Connection connection = ConnectionDataAccess.getInstance();
            String sqlInstruction = "SELECT name FROM country";
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlInstruction);
            ArrayList<String> countries = new ArrayList<>();

            while (resultSet.next()) {
                countries.add(resultSet.getString("name"));
            }
            country = new JComboBox<>(countries.toArray(new String[0]));
            addField(gbc, "Country", country, 11);
        } catch (ConnectionDataAccessException | SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void addField(GridBagConstraints gbc, String labelText, Component field, int yPos) {
        gbc.gridx = 0;
        gbc.gridy = yPos;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        add(field, gbc);
    }
}
