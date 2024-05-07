package proto;

import javax.swing.*;
import java.awt.event.*;

public class ButtonQuit extends JButton implements ActionListener {

    private MenuPanel menuPanel;

    public ButtonQuit(MenuPanel menuPanel) {
        super("Quit");
        this.menuPanel = menuPanel;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuPanel.dispose();
    }
}

