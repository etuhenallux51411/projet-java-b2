package proto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ResearchPanel extends JPanel{

    public JLabel welcome;

    private MenuPanel menuPanel;

    private BackButton backButton;

    public ResearchPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        welcome = new JLabel("Welcome to the Research Window");
        welcome.setBounds(100, 100, 100, 100);
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

    // Ajoutez un ActionListener au bouton de retour pour gérer le clic
    public void actionPerformed(ActionEvent e) {
        menuPanel.previousWindow();
    }

}
