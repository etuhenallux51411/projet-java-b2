import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ButtonStat extends JButton implements ActionListener{

    private MenuPanel menuPanel;
    public ButtonStat(MenuPanel menuPanel) {
        super("Stat");
        this.menuPanel = menuPanel;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuPanel.displayStatWindow();
    }
}
