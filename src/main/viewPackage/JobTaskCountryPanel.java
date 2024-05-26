package main.viewPackage;

import main.controllerPackage.CountriesController;
import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class JobTaskCountryPanel extends JPanel implements ActionListener {

    private JComboBox<String> countryComboBox;
    private JTable tableUsers;
    private double percentage;
    private CountriesController countriesController;
    private List<UserModel> users;
    private UserController userController;
    private DefaultTableModel tableModel;
    private JLabel percentageLabel;

    public JobTaskCountryPanel() throws ConnectionDataAccessException {
        JLabel welcomeText = new JLabel("Selectionne un pays pour voir le pourcentage d'utilisateurs correspondant");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(welcomeText, gbc);

        // ajouter les pays dans la comboBox
        countriesController = new CountriesController();
        try {
            countryComboBox = new JComboBox<>(countriesController.getCountries().toArray(new String[0]));
        } catch (CountriesDAOException e) {
            e.printStackTrace();
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(countryComboBox, gbc);


        userController = new UserController();

        String[] columnNames = {"Nom de l'utilisateur", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableUsers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        userController = new UserController();

        percentageLabel = new JLabel("Pourcentage d'utilisateurs correspondant : ");
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        percentageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(percentageLabel, gbc);

        countryComboBox.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != countryComboBox) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un pays ");
        } else {
            try {
                // Réinitialiser le modèle de table
                tableModel.setRowCount(0);

                String selectedCountry = Objects.requireNonNull(countryComboBox.getSelectedItem()).toString();
                users = userController.getUsersByCountry(selectedCountry);
                if (users.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun utilisateur trouvé");
                    percentageLabel.setText("Pourcentage d'utilisateurs correspondant : 0%");
                } else {
                    for (UserModel user : users) {
                        Object[] rowData = {
                                user.getUsername(),
                                user.getEmail(),
                        };
                        tableModel.addRow(rowData);
                    }
                    percentage = (double) users.size() / userController.numbUser() * 100;
                    percentageLabel.setText(String.format("Pourcentage d'utilisateurs correspondant : %.2f%%", percentage));
                }
            } catch (UserSearchException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
                tableModel.setRowCount(0);
            }
        }
    }
}