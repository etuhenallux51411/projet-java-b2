package main.proto;

import javax.swing.*;
import java.awt.event.*;

public class BackButton extends JButton implements ActionListener
{
    private MenuPanel menuPanel;

    public BackButton(MenuPanel menuPanel) {
        super("Back");
        this.menuPanel = menuPanel;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuPanel.previousWindow();
    }
}