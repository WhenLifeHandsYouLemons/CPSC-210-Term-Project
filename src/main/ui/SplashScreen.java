package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// Displays a splash screen gif before the program starts
public class SplashScreen extends JFrame {
    public SplashScreen() {
        super("Book Tracker App");

        JLabel splashImage = new JLabel(new ImageIcon("images/splash.gif"));
        this.add(splashImage);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Set the program to exit when the close button is clicked
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setSize(500, 500); // Set window size
        this.setLocationRelativeTo(null);

        this.setVisible(true);

        // Taken from Java's javax.swing.Timer tooltip documentation
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new BookTrackerAppGUI();
                dispose();
            }
        };

        Timer timer = new Timer(4000, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
}
