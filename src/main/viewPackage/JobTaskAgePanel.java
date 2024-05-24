package main.viewPackage;

import javax.swing.*;
import java.awt.*;

public class JobTaskAgePanel extends JPanel {

    public JobTaskAgePanel() {
        JLabel welcomeText = new JLabel("Selectionner un age pour voir le pourcentage d'utilisateurs correspondant");
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
    }
}
