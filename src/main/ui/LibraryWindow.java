package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Library;

// Creates a window to show library information and buttons to interact with it
public class LibraryWindow extends JFrame implements ActionListener {
    private Library library;

    // REQUIRES: library is not null
    // EFFECTS: Creates a window for the library info to be shown
    public LibraryWindow(Library library) {
        super(library.getName());

        this.library = library;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(850, 600); // Set window size
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    // Taken from:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    // REQUIRES: e is not null
    // EFFECTS: Handles button inputs and runs the respective methods
    public void actionPerformed(ActionEvent e) {
        if ("new library".equals(e.getActionCommand())) {}
    }
}
