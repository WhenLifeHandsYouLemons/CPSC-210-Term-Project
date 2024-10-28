package ui;

import javax.swing.*;

public class BookTrackerAppGUI {
    public static void main(String[] args) {
        // Create the main app frame
        JFrame frame = new JFrame("Book Tracker App");

        // Set the program to exit when the close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set window size
        frame.setSize(850, 600);

        JPanel titlePanel = new JPanel();

        // Adds the title panel to the frame
        frame.add(titlePanel);

        // Runs the GUI to show up
        frame.setVisible(true);
    }
}
