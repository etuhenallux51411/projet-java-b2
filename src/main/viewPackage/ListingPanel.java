package main.viewPackage;

import main.controllerPackage.UserController;
import main.exceptionPackage.LocalityException;
import main.modelPackage.ListingTableModel;
import main.modelPackage.UserModel;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.CountriesDAOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListingPanel extends JPanel implements ActionListener {

    private MainWindow mainWindow;
    private JTable tableUsers;
    private ArrayList<UserModel> users;
    private List<String> columnNames;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonAdd;
    private JPanel addUserPanel;
    private JScrollPane scrollPane;

    private UserController userController;

    public ListingPanel(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;
        userController = new UserController();

        setLayout(new BorderLayout());

        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(0, 20)); // 20 pixels height gap
        add(gapPanel, BorderLayout.CENTER);

        // Table in the center
        tableUsers = new JTable();
        scrollPane = new JScrollPane(tableUsers);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonAdd = new JButton("Ajouter un utilisateur");
        buttonAdd.addActionListener(this);

        buttonUpdate = new JButton("Modifier un utilisateur");
        buttonDelete = new JButton("Supprimer un utilisateur");

        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonUpdate);
        buttonPanel.add(buttonDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        columnNames = userController.getColumnsNames();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            try {
                this.addUserPanel = new AddUserPanel(mainWindow);
                mainWindow.switchPanel(addUserPanel);
            } catch (CountriesDAOException | ConnectionDataAccessException | LocalityException ex) {
                mainWindow.displayError(ex.toString());
            }
        }
    }

    public void refreshUsersData() {
        users = new ArrayList<>(userController.getAllUsers());
        tableUsers = updateTable(users, columnNames);
        scrollPane.setViewportView(tableUsers);
    }

    public JTable updateTable(List<UserModel> users, List<String> columnsNames) {
        Object[][] data = new Object[users.size()][columnsNames.size()];
        for (int i = 0; i < users.size(); i++) {
            UserModel user = users.get(i);
            data[i][0] = user.getId();
            data[i][1] = user.getEmail();
            data[i][2] = user.getUsername();
            data[i][3] = user.getPassword();
            data[i][4] = user.getDateOfBirth();
            data[i][5] = user.getGender();
            data[i][6] = user.getCreatedAt();
            data[i][7] = user.getStreetAndNumber();
            data[i][8] = user.getPhoneNumber();
            data[i][9] = user.getBio();
            data[i][10] = user.isAdmin();
            data[i][11] = user.getHome();
        }
        ListingTableModel model = new ListingTableModel(data, columnsNames.toArray());
        return new JTable(model);
    }
}
