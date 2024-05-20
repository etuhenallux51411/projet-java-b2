package main.viewPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;

public class ResearchCommunity  extends JPanel{


        private JComboBox<String> communityComboBox;

        private JButton searchButton;




        public ResearchCommunity(){
            JLabel welcomeText = new JLabel("Selectionner la commmunauté pour laquelle vous voulez voir les utilisateurs :");
            welcomeText.setFont(new Font("Arial", Font.BOLD, 16));
            welcomeText.setHorizontalAlignment(SwingConstants.CENTER);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // titre
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(welcomeText, gbc);


            // ComboBox
            try {
                Connection connection = ConnectionDataAccess.getInstance();
                String sqlInstruction = "SELECT name FROM community";
                Statement preparedStatement = connection.createStatement();
                ResultSet resultSet = preparedStatement.executeQuery(sqlInstruction);
                ArrayList<String> communities = new ArrayList<>();

                while (resultSet.next()) {
                    communities.add(resultSet.getString("name"));
                }
                communityComboBox = new JComboBox<>(communities.toArray(new String[0]));
                communityComboBox.setPreferredSize(new Dimension(200, 30));
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(communityComboBox, gbc);
            } catch (ConnectionDataAccessException | SQLException e) {
                e.printStackTrace();
            }
            searchButton = new JButton("Envoyer");
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(searchButton, gbc);

            searchButton.addActionListener(e -> submit());

        }
    private void submit() {
        String community = (String) communityComboBox.getSelectedItem();
        System.out.println("Community selected: " + community);
    }
}
