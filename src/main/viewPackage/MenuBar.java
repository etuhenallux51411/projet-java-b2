package main.viewPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    private MainWindow mainWindow;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem home;
    private JMenuItem add;
    private JMenuItem listing;
    private JMenuItem exit;

    public MenuBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        menuBar = new JMenuBar();

        menu = new JMenu("test");

        home = new JMenuItem("Accueil");
        home.addActionListener(this);

        add = new JMenuItem("Ajouter");
        add.addActionListener(this);

        listing = new JMenuItem("Listing");
        listing.addActionListener(this);

        exit = new JMenuItem("Quitter");
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
//            mainWindow.showWelcomePanel();
//        } else if (e.getSource() == add) {
//            mainWindow.showAddPanel();
//        } else if (e.getSource() == listing) {
//            mainWindow.showListingPanel();
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }
}
