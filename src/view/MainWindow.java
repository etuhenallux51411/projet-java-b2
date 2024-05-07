package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static MainWindow instance;

    // Private constructor
    private MainWindow() {
        // Set the title
        super("Main Window");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setBackground(Color.WHITE);

        // Center the window
        setLocationRelativeTo(null);

        setVisible(true);
    }

    // Singleton
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }
}
