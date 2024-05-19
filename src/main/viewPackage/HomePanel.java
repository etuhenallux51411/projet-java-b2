package main.viewPackage;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private JLabel welcomeText;

    public HomePanel(){
        welcomeText = new JLabel(
                "<html><body><div color='black'><center>Bienvenue dans l'application de simulant un réseau social </center><br/" +
                        "<p>Voici les fonctionnalités de base :</p>" +
                        "<ul>" +
                        "<li> Opérations CRUD </li>" +
                        "<li> Recherches </li>" +
                        "<li> Tâche métier </li>" +
                        "<li> Animation de like (Thread) </li>" +
                        "</ul>" +
                        "<p></p></div></body></html>"
        );

        setLayout(new BorderLayout());
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeText, BorderLayout.CENTER);
    }
}
