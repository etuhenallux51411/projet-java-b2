package main.viewPackage;

import main.modelPackage.UserModel;
import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class ListingPanel extends JPanel {

    private JTable tableUsers;
    private ArrayList<UserModel> users = new ArrayList<>();
    private String[] columnNames;
    private JButton buttonUpdate, buttonDelete, buttonAdd;

    public ListingPanel() {
        setLayout(new BorderLayout());

        // Top label
        JLabel label = new JLabel("Listing Panel", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        // Adding a vertical gap
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(0, 20)); // 20 pixels height gap
        add(gapPanel, BorderLayout.CENTER);

        // Table in the center
        tableUsers = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableUsers);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        buttonAdd = new JButton("Add User");
        buttonUpdate = new JButton("Update User");
        buttonDelete = new JButton("Delete User");

        buttonPanel.add(buttonAdd);
        buttonPanel.add(buttonUpdate);
        buttonPanel.add(buttonDelete);

        add(buttonPanel, BorderLayout.SOUTH);

        try {
            Connection con = ConnectionDataAccess.getInstance();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }
        } catch (ConnectionDataAccessException | SQLException e) {
            e.printStackTrace();
        }
        tableUsers = new JTable(new Object[][]{}, columnNames);
        scrollPane.setViewportView(tableUsers);
    }
}
