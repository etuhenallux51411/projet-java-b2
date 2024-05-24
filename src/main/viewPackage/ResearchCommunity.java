package main.viewPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import main.controllerPackage.CommunityController;
import main.exceptionPackage.CommunityDAOException;
import main.exceptionPackage.ConnectionDataAccessException;
import main.modelPackage.CommunityModel;
import main.modelPackage.NonEditableTableModel;
import main.modelPackage.MemberModel;

public class ResearchCommunity  extends JPanel implements ActionListener {
    private MainWindow mainWindow;
    private JComboBox<String> communityComboBox;
    private CommunityController communityController;
    private DefaultTableModel tableModel;
    private CommunityModel selectedCommunity;

    public ResearchCommunity(MainWindow mainWindow) throws ConnectionDataAccessException {
        this.mainWindow = mainWindow;

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
        tableModel = new NonEditableTableModel(columnNames, 0);
        JTable tableUsers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsers);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        communityComboBox.addActionListener(this);
    }

    public void refreshComboBox() throws CommunityDAOException {
        if (communityComboBox.getItemCount() > 0) communityComboBox.removeAllItems();

        List<CommunityModel> communities = communityController.getAllCommunities();
        for (CommunityModel community : communities) {
            communityComboBox.addItem(community.getName());
        }
        communityComboBox.setSelectedIndex(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == communityComboBox) {
            try {
                String selectedCommunityName = (String) communityComboBox.getSelectedItem();

                for (CommunityModel community : communityController.getAllCommunities()) {
                    if (community.getName().equals(selectedCommunityName)) {
                        selectedCommunity = community;
                        break;
                    }
                }

                if (selectedCommunity == null) {
                    mainWindow.displayError("La communauté sélectionnée n'existe pas.");
                    return;
                }

                int communityId = selectedCommunity.getId();
                tableModel.setRowCount(0);
                List<MemberModel> members = communityController.getCommunityById(communityId);

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

            } catch (CommunityDAOException exception) {
                mainWindow.displayError(exception.toString());
            }
        }
    }
}
