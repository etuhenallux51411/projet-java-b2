package main.viewPackage;

import main.controllerPackage.LikeController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ResearchLike extends JPanel {

    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton submitButton;
    private JTable tableLikes;
    private LikeController likeController;
    private DefaultTableModel tableModel;

    private List<LikeModel> likes;

    public ResearchLike() throws ConnectionDataAccessException {
        JLabel welcomeText = new JLabel("Selectionner une plage de dates, pour trouver les likes entre ces dates :");
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

        JLabel startDateLabel = new JLabel("Date de début :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(startDateLabel, gbc);

        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(startDateSpinner, gbc);

        JLabel endDateLabel = new JLabel("Date de fin :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(endDateLabel, gbc);

        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(endDateSpinner, gbc);

        submitButton = new JButton("Envoyer");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        String[] columnNames = {"Nom de l'utilisateur", "Date du like", "Contenu de la publication"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableLikes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLikes);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        likeController = new LikeController();
        submitButton.addActionListener(e -> submit());
    }

    private void submit() {
        Date startDate = new Date(((java.util.Date) startDateSpinner.getValue()).getTime());
        Date endDate = new Date(((java.util.Date) endDateSpinner.getValue()).getTime());

        try {
            likes = likeController.getLikesBetween(startDate, endDate);
            System.out.println(likes.size());
            tableModel.setRowCount(0); // Clear existing rows
            for (LikeModel like : likes) {
                Object[] rowData = {
                        like.getUsername(),
                        like.getDate(),
                        like.getPostContent()
                };
                tableModel.addRow(rowData);
            }
        } catch (LikeSearchException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche des likes : " + e.getMessage());
        }
    }
}
