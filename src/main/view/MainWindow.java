package main.view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Main Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel label = new JLabel("Hello, World!");
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gbc);

        JButton button = new JButton("Click me!");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        add(button, gbc);

        setVisible(true);
    }
}