package main.viewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    private final MainWindow mainWindow;
    private final JMenuBar menuBar;
    private final JMenu menu;

    private final JMenu menuCRUD;
    private final JMenu menuTacheMetier;

    private final JMenu menuResearch;
    private final JMenuItem home;

    private final JMenuItem addUser;

    private final JMenuItem listing;

    private final JMenuItem Research;
    private final JMenuItem exit;
    private final JMenuItem tacheMetier;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuCRUD = new JMenu("CRUD");
        menuTacheMetier = new JMenu("Tâche métier");
        menuResearch = new JMenu("Recherches");



        home = new JMenuItem("Retour à l'acceuil");
        home.addActionListener(this);


        addUser = new JMenuItem("Ajouter un utilisateur");
        addUser.addActionListener(this);

        listing = new JMenuItem("Liste des utilisateurs");
        listing.addActionListener(this);


        Research = new JMenuItem("Recherches");
        Research.addActionListener(this);

        tacheMetier = new JMenuItem("Tâche métier");
        tacheMetier.addActionListener(this);

        exit = new JMenuItem("Quitter le programme");
        exit.addActionListener(this);

        menu.add(home);
        menu.add(exit);


        menuCRUD.add(addUser);
        menuCRUD.addSeparator();
        menuCRUD.add(listing);


        menuResearch.add(Research);

        menuTacheMetier.add(tacheMetier);



        menuBar.add(menu);
        menuBar.add(menuCRUD);
        menuBar.add(menuResearch);
        menuBar.add(menuTacheMetier);


        mainWindow.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            mainWindow.switchPanel(mainWindow.getHomePanel());
        } else if (e.getSource() == addUser) {
            mainWindow.switchPanel(mainWindow.getAddUserPanel());
        } else if (e.getSource() == Research) {
            mainWindow.switchPanel(mainWindow.getResearchPanel());
        } else if (e.getSource() == tacheMetier) {
            mainWindow.switchPanel(mainWindow.getTacheMetierPanel());
        }
        else if (e.getSource() == listing) {
            mainWindow.switchPanel(mainWindow.getListingPanel());
        }
        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
