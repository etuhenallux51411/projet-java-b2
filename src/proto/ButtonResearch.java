package proto;

import javax.swing.*;
import java.awt.event.*;

public class ButtonResearch extends JButton implements ActionListener
{
    private MenuPanel menuPanel;
    public ButtonResearch(MenuPanel menuPanel) {
        super("Research");
        this.menuPanel = menuPanel;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuPanel.displayResearchWindow();
    }
}
