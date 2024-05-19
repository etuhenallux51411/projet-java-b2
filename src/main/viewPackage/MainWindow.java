package main.viewPackage;

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
    private JPanel addUserPanel;
    private JPanel listingPanel;
    private JPanel tacheMetierPanel;

    private JPanel researchPanel;
    private MenuBar menuBar;

    public MainWindow() {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(X_BOUNDS, Y_BOUNDS, FRAME_WIDTH, FRAME_HEIGHT);

        homePanel = new HomePanel();
        addUserPanel = new AddUserPanel();
        listingPanel = new ListingPanel();
        tacheMetierPanel = new TacheMetierPanel();
        menuBar = new MenuBar(this);
        researchPanel = new ResearchPanel();
        switchPanel(homePanel);
        setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    public JPanel getHomePanel() {
        return homePanel;
    }

    public JPanel getAddUserPanel() {
        return addUserPanel;
    }

    public JPanel getListingPanel() {
        return listingPanel;
    }

    public JPanel getTacheMetierPanel() {return tacheMetierPanel;}

    public JPanel getResearchPanel() {return new ResearchPanel();}


}