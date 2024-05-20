package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResearchLike extends JPanel {

    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton submitButton;

    public ResearchLike() {

        JLabel welcomeText = new JLabel("Selectionner une plage de dates, pour trouver les likes entre ces dates :");
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

        // date de départ
        JLabel startDateLabel = new JLabel("Date de début :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(startDateLabel, gbc);

        // Spinner de la date de début
        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "dd-MM-yyyy");
        startDateSpinner.setEditor(startDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(startDateSpinner, gbc);

        // date de fin
        JLabel endDateLabel = new JLabel("Date de fin :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(endDateLabel, gbc);

        // Spinner de la date de fin
        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "dd-MM-yyyy");
        endDateSpinner.setEditor(endDateEditor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(endDateSpinner, gbc);

        // Button de recherche
        submitButton = new JButton("Envoyer");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        submitButton.addActionListener(e -> submit());
    }

    private void submit() {
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);

        JOptionPane.showMessageDialog(this, "Selected date range: \nStart Date: " + startDateStr + "\nEnd Date: " + endDateStr);
    }
}
