package ui;

import javax.swing.*;

import model.Library;

// Creates a window to show library information and buttons to interact with it
public class LibraryWindow extends JFrame {
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
}
