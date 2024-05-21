package main.viewPackage;

import main.dataAccessPackage.ConnectionDataAccess;
import main.exceptionPackage.ConnectionDataAccessException;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static final String WINDOW_TITLE = "Social Network";
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int X_BOUNDS = (SCREEN_SIZE.width - FRAME_WIDTH) / 2;
    private static final int Y_BOUNDS = (SCREEN_SIZE.height - FRAME_HEIGHT) / 2;

    private JPanel homePanel;
    private JPanel listingPanel;
    private JPanel tacheMetierPanel;

    private JPanel researchPrivateMessage;
    private JPanel researchLike;
    private JPanel researchCommunity;
    private MenuBar menuBar;

    public MainWindow() {
        super(WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(X_BOUNDS, Y_BOUNDS, FRAME_WIDTH, FRAME_HEIGHT);

        try {
            ConnectionDataAccess.getInstance();
            listingPanel = new ListingPanel(this);
            homePanel = new HomePanel();
            tacheMetierPanel = new TacheMetierPanel();
            menuBar = new MenuBar(this);
            researchPrivateMessage = new ResearchPrivateMessage();
            researchLike = new ResearchLike();
            researchCommunity = new ResearchCommunity();
        } catch (ConnectionDataAccessException e) {
            displayError(e.toString());
            System.exit(1);
        }

        switchPanel(homePanel);
        setVisible(true);
    }

    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void switchPanel(JPanel panel) {
        if (panel == listingPanel)
            ((ListingPanel) listingPanel).refreshUsersData();
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public JPanel getHomePanel() {
        return homePanel;
    }

    public JPanel getListingPanel() {
        return listingPanel;
    }

    public JPanel getTacheMetierPanel() {
        return tacheMetierPanel;
    }

    public JPanel getResearchPrivateMessage() {
        return researchPrivateMessage;
    }

    public JPanel getResearchLike() {
        return researchLike;
    }

    public JPanel getResearchCommunity() {
        return researchCommunity;
    }
}