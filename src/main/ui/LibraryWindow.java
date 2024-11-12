package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Book;
import model.Library;

// Creates a window to show library information and buttons to interact with it
public class LibraryWindow extends JFrame implements ActionListener {
    private Library library;

    private JButton newBookButton;
    private JButton removeBookButton;
    private JButton viewStatsButton;
    private JButton viewBooksButton;
    private JButton backButton;

    private Color backgroundColor;
    private Color defaultButtonColor;
    private Color warningButtonColor;
    private Color transparentColor;

    // REQUIRES: library is not null
    // EFFECTS: Creates a window for the library info to be shown
    public LibraryWindow(Library library) {
        super(library.getName());

        this.library = library;

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(750, 500); // Set window size
        this.setLocationRelativeTo(null);

        initialiseColors();
        int columnPadding = 50;

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayout(1, 3));

        JPanel titleSection = createTitleSection();

        JPanel leftColumnSection = createLeftColumnSection(columnPadding);
        JPanel rightColumnSection = createRightColumnSection(columnPadding);

        JPanel backButtonSection = createBackButtonSection();

        setButtonFunctionality();

        // Adds all items to the main GUI frame
        this.add(titleSection, BorderLayout.PAGE_START);
        mainContentPanel.add(leftColumnSection, BorderLayout.LINE_START);
        mainContentPanel.add(rightColumnSection, BorderLayout.LINE_END);
        this.add(mainContentPanel);
        this.add(backButtonSection, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Initialises the default colours to be used across the app
    private void initialiseColors() {
    }

    // EFFECTS: Creates all GUI elements for the title bar and returns the JPanel
    // object
    private JPanel createTitleSection() {
        return null;
    }

    // REQUIRES: columnPadding > 0
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the left column and returns the left
    // column section
    private JPanel createLeftColumnSection(int columnPadding) {
        return null;
    }

    // REQUIRES: columnPadding > 0
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the right column and returns the
    // right column section
    private JPanel createRightColumnSection(int columnPadding) {
        return null;
    }

    // EFFECTS: Creates all GUI elements for the back button at the bottom and
    // returns the JPanel object
    private JPanel createBackButtonSection() {
        return null;
    }

    // REQUIRES: All JButton variables are not null
    // MODIFIES: this
    // EFFECTS: Sets the buttons' actions and event listeners
    private void setButtonFunctionality() {
    }

    // Taken from:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    // REQUIRES: e is not null
    // EFFECTS: Handles button inputs and runs the respective methods
    public void actionPerformed(ActionEvent e) {
    }

    // REQUIRES: library is not null
    // EFFECTS: Opens a new window to display the user's reading statistics for the
    // current library
    private void viewStats() {
    }

    // REQUIRES: library is not null
    // EFFECTS: Creates a window to show all books tracked in the current library
    private void viewBooks() {
    }

    // REQUIRES: library is not null
    // MODIFIES: this
    // EFFECTS: Asks user to choose a book to delete and deletes the given book
    private void removeBook() {
    }

    // MODIFIES: this
    // EFFECTS: Asks for user input for the book name to create
    private void createBook() {
    }
}
