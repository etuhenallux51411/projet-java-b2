package proto;

import javax.swing.*;
import java.awt.event.*;

public class ButtonUser extends JButton implements ActionListener {
    private MenuPanel menuPanel;

    public ButtonUser(MenuPanel menuPanel) {
        super("User");
        this.menuPanel = menuPanel;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuPanel.displayUserWindow();
    }
}

