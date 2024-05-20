package main.viewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    private MainWindow mainWindow;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menuCRUD;
    private JMenu menuTacheMetier;
    private JMenu menuResearch;
    private JMenuItem home;
    private JMenuItem listing;
    private JMenuItem exit;
    private JMenuItem tacheMetier;
    private JMenuItem researchLike;
    private JMenuItem researchCommunity;
    private JMenuItem researchPrivateMessage;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuCRUD = new JMenu("CRUD");
        menuTacheMetier = new JMenu("Tâche métier");
        menuResearch = new JMenu("Recherches");

        home = new JMenuItem("Retour à l'acceuil");
        home.addActionListener(this);

        listing = new JMenuItem("Liste des utilisateurs");
        listing.addActionListener(this);

        researchLike = new JMenuItem("Combien de like entre deux dates ?");
        researchLike.addActionListener(this);

        researchCommunity = new JMenuItem("Qui est dans la communauté ?");
        researchCommunity.addActionListener(this);

        researchPrivateMessage = new JMenuItem("Qui a envoyé un message privé à qui ?");
        researchPrivateMessage.addActionListener(this);

        tacheMetier = new JMenuItem("Tâche métier");
        tacheMetier.addActionListener(this);

        exit = new JMenuItem("Quitter le programme");
        exit.addActionListener(this);

        menu.add(home);
        menu.addSeparator();
        menu.add(exit);

        menuCRUD.add(listing);

        menuResearch.add(researchLike);
        menuResearch.addSeparator();
        menuResearch.add(researchCommunity);
        menuResearch.addSeparator();
        menuResearch.add(researchPrivateMessage);

        menuTacheMetier.add(tacheMetier);

        menuBar.add(menu);
        menuBar.add(menuCRUD);
        menuBar.add(menuResearch);
        menuBar.add(menuTacheMetier);

        mainWindow.setJMenuBar(menuBar);
    }

    public JMenuItem getHomeItem() {
        return home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            mainWindow.switchPanel(mainWindow.getHomePanel());
        } else if (e.getSource() == tacheMetier) {
            mainWindow.switchPanel(mainWindow.getTacheMetierPanel());
        }
        else if (e.getSource() == listing) {
            mainWindow.switchPanel(mainWindow.getListingPanel());
        }
        else if (e.getSource() == researchLike) {
            mainWindow.switchPanel(mainWindow.getResearchLike());
        }
        else if (e.getSource() == researchCommunity) {
            mainWindow.switchPanel(mainWindow.getResearchCommunity());
        }
        else if (e.getSource() == researchPrivateMessage) {
            mainWindow.switchPanel(mainWindow.getResearchPrivateMessage());
        }
        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
