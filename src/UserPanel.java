import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPanel extends JPanel implements ActionListener {

    public JLabel welcome;
    public BackButton backButton;
    public MenuPanel menuPanel;

    public UserPanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;
        welcome = new JLabel("Welcome to the User Window");

        // Use the same backButton instead of creating a new one
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