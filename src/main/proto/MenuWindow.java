package main.proto;

import javax.swing.*;


public class MenuWindow extends JFrame  {

    private MenuPanel menuPanel;

        public MenuWindow() {
            super("Menu");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 1000, 1000);
            MenuPanel menuPanel = new MenuPanel(this);
            getContentPane().add(menuPanel);
            setVisible(true);
        }
    public void returnToMainMenu() {
        // Nettoyez la fenêtre et affichez le menu principal
        getContentPane().removeAll();
        getContentPane().add(menuPanel);
        revalidate();
        repaint();
    }
}

