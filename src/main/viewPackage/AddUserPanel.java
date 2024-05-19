package main.viewPackage;

import javax.swing.*;
import java.util.Locale;

public class AddUserPanel extends JPanel {

    private JTextField username, password, email, dateOfBirth,city,phoneNumber,zipdCode,street,houseNumber;
    private JComboBox<String> country,gender;



    public AddUserPanel() {
        JLabel label = new JLabel("Add User Panel");
        add(label);

        username = new JTextField(20);
        add(new JLabel("Username"));
        add(username);
        password = new JTextField(20);
        add(new JLabel("Password"));
        add(password);
        email = new JTextField(20);
        add(new JLabel("Email"));
        add(email);
        dateOfBirth = new JTextField(20);
        city = new JTextField(20);
        phoneNumber = new JTextField(20);
        zipdCode = new JTextField(20);
        street = new JTextField(20);
        houseNumber = new JTextField(20);

        String[] countries;
        country = new JComboBox();
        String [] genders = {"Man","Women","Other"};
        gender = new JComboBox(genders);
        add(new JLabel("Select Country"));
        add(country);
        add(new JLabel("Gender"));
        add(gender);
        setVisible(true);

    }
}