package main.viewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    private final MainWindow mainWindow;
    private final JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem home;
    private final JMenuItem add;
    private final JMenuItem listing;
    private final JMenuItem exit;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");

        home = new JMenuItem("Retour à l'acceuil");
        home.addActionListener(this);

        add = new JMenuItem("Ajouter un utilisateur");
        add.addActionListener(this);

        listing = new JMenuItem("Liste des utilisateurs");
        listing.addActionListener(this);

        exit = new JMenuItem("Quitter le programme");
        exit.addActionListener(this);

        menu.add(home);
        menu.add(add);
        menu.add(listing);
        menu.addSeparator();
        menu.add(exit);

        menuBar.add(menu);

        mainWindow.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            mainWindow.switchPanel(mainWindow.getHomePanel());
        } else if (e.getSource() == add) {
            mainWindow.switchPanel(mainWindow.getAddUserPanel());
        } else if (e.getSource() == listing) {
            mainWindow.switchPanel(mainWindow.getListingPanel());
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
