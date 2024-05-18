package main.proto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatPanel extends JPanel {

    public JLabel welcome;
    public JButton backButton; // Utilisez un JButton pour le bouton de retour

    public MenuPanel menuPanel;

    public StatPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        welcome = new JLabel("Welcome to the Stat Window");

        backButton = new BackButton(menuPanel);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridwidth = 1;

        gbc.gridy = 1;
        add(welcome, gbc);
        gbc.gridy = 2;
        add(backButton, gbc);
    }
    public void actionPerformed(ActionEvent e) {
        menuPanel.previousWindow();
    }
}
