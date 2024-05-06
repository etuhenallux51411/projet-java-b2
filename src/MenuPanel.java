import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class MenuPanel extends JPanel {

    private ButtonUser buttonUser;
    private ButtonResearch buttonResearch;
    private ButtonStat buttonStat;
    private ButtonQuit buttonQuit;
    private JLabel welcome;

    private UserPanel userWindow;

    private StatPanel statWindow;

    private ResearchPanel researchWindow;

    private BackButton backButton;


    private MenuWindow menuWindow;

    public MenuPanel(MenuWindow menuWindow) {

        this.menuWindow = menuWindow;

        welcome = new JLabel("Social Network ");
        welcome.setFont(new Font("Arial", Font.BOLD, 24));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcome);

        userWindow = new UserPanel(this);
        statWindow = new StatPanel(this);
        researchWindow = new ResearchPanel(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        buttonUser = new ButtonUser(this);
        gbc.gridy = 1;
        add(buttonUser, gbc);

        buttonResearch = new ButtonResearch(this);
        gbc.gridy = 2;
        add(buttonResearch, gbc);

        buttonStat = new ButtonStat(this);
        gbc.gridy = 3;
        add(buttonStat, gbc);

        buttonQuit = new ButtonQuit(this);
        gbc.gridy = 4;
        add(buttonQuit, gbc);
    }

    public void displayUserWindow() {
        switchPanel(userWindow);
    }

    public void displayResearchWindow() {
        switchPanel(researchWindow);
    }

    public void displayStatWindow() {
        switchPanel(statWindow);
    }


    public void previousWindow() {
       switchPanel(this);
    }

    public void switchPanel(JPanel newPanel) {
        // Nettoyez la fenêtre et affichez le nouveau panneau
        menuWindow.getContentPane().removeAll();
        menuWindow.getContentPane().add(newPanel);
        menuWindow.revalidate();
        menuWindow.repaint();
    }

    public BackButton getBackButton() {
        return backButton;
    }

    public void dispose() {
        menuWindow.dispose();
    }
}