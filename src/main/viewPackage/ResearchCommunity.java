package main.viewPackage;
import javax.naming.CommunicationException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.controllerPackage.CommunityController;
import main.controllerPackage.UserController;
import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.CommunityException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.UserSearchException;
import main.modelPackage.CommunityModel;
import main.modelPackage.MemberModel;
import main.modelPackage.UserModel;

public class ResearchCommunity  extends JPanel implements ActionListener {
    private JComboBox<String> communityComboBox;

    private CommunityController communityController;


    private DefaultTableModel tableModel;

    private JTable tableUsers;

    private List<MemberModel> members;

    private UserController userController;



    public ResearchCommunity() throws ConnectionDataAccessException {
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

        communityController = new CommunityController();
        communityComboBox = new JComboBox<>();


        communityComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(communityComboBox, gbc);



        String[] columnNames = {"Nom de l'utilisateur", "Rue et numéro ", "Ville", "Code postal", "Pays"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableUsers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsers);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);
        try {
            List<CommunityModel> communities = communityController.getAllCommunities();
            for (CommunityModel community : communities) {
                communityComboBox.addItem(community.getName());
            }
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
        communityComboBox.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == communityComboBox) {
            try {
                String selectedCommunityName = (String) communityComboBox.getSelectedItem();
                CommunityModel selectedCommunity = null;

                for (CommunityModel community : communityController.getAllCommunities()) {
                    if (community.getName().equals(selectedCommunityName)) {
                        selectedCommunity = community;
                        break;
                    }
                }

                if (selectedCommunity == null) {
                    JOptionPane.showMessageDialog(this, "Veuillez sélectionner une communauté valide");
                    return;
                }

                int communityId = selectedCommunity.getId();

                tableModel.setRowCount(0);
                members = communityController.getCommunityById(communityId);

                for (MemberModel member : members) {
                    Object[] data = {
                            member.getUsername(),
                            member.getStreetAndNumber(),
                            member.getLocationName(),
                            member.getZipCode(),
                            member.getCountry()
                    };
                    tableModel.addRow(data);
                }

            } catch (CommunicationException exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
                tableModel.setRowCount(0);
            }
        }
    }

}
