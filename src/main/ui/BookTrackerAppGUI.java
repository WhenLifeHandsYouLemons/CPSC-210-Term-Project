package ui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class BookTrackerAppGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Book Tracker App"); // Create the main app frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the program to exit when the close button is
                                                              // clicked
        frame.setSize(850, 600); // Set window size

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        mainContentPanel.setLayout(new GridLayout(1, 3));

        int buttonWidth = 200;
        int buttonHeight = 75;

        // Title section
        JLabel titleText = new JLabel("Book Tracker App");
        titleText.setSize(frame.getWidth(), titleText.getMinimumSize().height);
        titleText.setFont(new Font("SansSerif", Font.BOLD, 32));
        titlePanel.add(titleText);

        // First column section
        JPanel firstColumnPanel = new JPanel();
        firstColumnPanel.setLayout(null);
        firstColumnPanel.setBackground(new Color(255, 0, 0));

        // second column section
        JPanel secondColumnPanel = new JPanel();
        secondColumnPanel.setLayout(null);
        secondColumnPanel.setBackground(new Color(0, 255, 0));

        // third column section
        JPanel thirdColumnPanel = new JPanel();
        thirdColumnPanel.setLayout(null);
        thirdColumnPanel.setBackground(new Color(0, 0, 255));

        frame.add(titlePanel, BorderLayout.PAGE_START);
        mainContentPanel.add(firstColumnPanel, BorderLayout.LINE_START);
        mainContentPanel.add(secondColumnPanel, BorderLayout.CENTER);
        mainContentPanel.add(thirdColumnPanel, BorderLayout.LINE_END);
        frame.add(mainContentPanel);
        frame.setVisible(true); // Runs the GUI to show up
    }
}
