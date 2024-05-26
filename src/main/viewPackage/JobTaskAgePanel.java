package main.viewPackage;

import main.controllerPackage.UserController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.UserModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class JobTaskAgePanel extends JPanel implements ActionListener {

    private JComboBox<String> ageComoboBox;
    private JTable tableUsers;
    private double percentage;

    private List<UserModel> users;
    private UserController userController;
    private DefaultTableModel tableModel;
    private JLabel percentageLabel;

    public JobTaskAgePanel() throws ConnectionDataAccessException {
        JLabel welcomeText = new JLabel("Selectionne une tranche d'age pour voir le pourcentage d'utilisateurs correspondant :");
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
        userController = new UserController();
        ageComoboBox = new JComboBox<String>();
        ageComoboBox.addItem("0-17");
        ageComoboBox.addItem("18-25");
        ageComoboBox.addItem("26-49");
        ageComoboBox.addItem("50-64");
        ageComoboBox.addItem("65+");


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(ageComoboBox, gbc);


        userController = new UserController();

        String[] columnNames = {"Nom de l'utilisateur", "Email","Date de naissance"};
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

        ageComoboBox.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ageComoboBox) {
            try {
                tableModel.setRowCount(0);
                Date[] dateRange = getDateRange((String) ageComoboBox.getSelectedItem());
                users = userController.getAllUsers();
                int count = 0;
                for (UserModel user : users) {
                    if (user.getDateOfBirth().after(dateRange[1]) && user.getDateOfBirth().before(dateRange[0])) {
                        tableModel.addRow(new Object[]{user.getUsername(), user.getEmail(),user.getDateOfBirth()});
                        count++;
                    }
                }
                percentage = (double) count / users.size() * 100;
                percentageLabel.setText("Pourcentage d'utilisateurs correspondant : " + percentage + "%");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public Date[] getDateRange(String ageRange)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Date startDate;
        Date endDate;

        switch (ageRange) {
            case "0-17":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -17);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -0);
                startDate = cal.getTime();
                break;
            case "18-25":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -25);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -18);
                startDate = cal.getTime();
                break;
            case "26-49":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -49);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -26);
                startDate = cal.getTime();
                break;
            case "50-64":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -64);
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -50);
                startDate = cal.getTime();
                break;
            case "65+":
                cal.setTime(now);
                cal.add(Calendar.YEAR, -120); // 120 Age maximum
                endDate = cal.getTime();
                cal.setTime(now);
                cal.add(Calendar.YEAR, -65);
                startDate = cal.getTime();
                break;
            default:
                throw new IllegalArgumentException("Invalid age range");
        }
        return new Date[]{startDate, endDate};
    }

}
