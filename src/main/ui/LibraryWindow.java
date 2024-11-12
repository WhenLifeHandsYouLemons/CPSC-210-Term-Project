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
        backgroundColor = new Color(100, 100, 100);
        defaultButtonColor = new Color(200, 200, 200);
        warningButtonColor = new Color(255, 50, 50);
        transparentColor = new Color(0, 0, 0, 0);
    }

    // EFFECTS: Creates all GUI elements for the title bar and returns the JPanel
    // object
    private JPanel createTitleSection() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(transparentColor);

        // Title section
        JLabel titleText = new JLabel(this.library.getName());
        titleText.setSize(getWidth(), titleText.getMinimumSize().height);
        titleText.setFont(new Font("SansSerif", Font.BOLD, 32));
        titlePanel.add(titleText);

        return titlePanel;
    }

    // REQUIRES: columnPadding > 0
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the left column and returns the left
    // column section
    private JPanel createLeftColumnSection(int columnPadding) {
        JPanel leftColumn = new JPanel();
        JPanel leftColumnInfo = new JPanel();
        leftColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        leftColumn.setBackground(backgroundColor);
        leftColumnInfo.setBackground(null);
        leftColumn.setLayout(new GridLayout(1, 1));
        leftColumnInfo.setLayout(new GridLayout(2, 1, 0, 75));

        // New library button
        newBookButton = new JButton("New Book");
        newBookButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        newBookButton.setBackground(defaultButtonColor);
        leftColumnInfo.add(newBookButton);

        // View statistics button
        viewStatsButton = new JButton("View Statistics");
        viewStatsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewStatsButton.setBackground(defaultButtonColor);
        leftColumnInfo.add(viewStatsButton);

        leftColumn.add(leftColumnInfo);

        return leftColumn;
    }

    // REQUIRES: columnPadding > 0
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the right column and returns the
    // right column section
    private JPanel createRightColumnSection(int columnPadding) {
        JPanel rightColumn = new JPanel();
        JPanel rightColumnInfo = new JPanel();
        rightColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        rightColumn.setBackground(backgroundColor);
        rightColumnInfo.setBackground(null);
        rightColumn.setLayout(new GridLayout(1, 1));
        rightColumnInfo.setLayout(new GridLayout(2, 1, 0, 75));

        // Remove library button
        removeBookButton = new JButton("Remove Book");
        removeBookButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        removeBookButton.setBackground(defaultButtonColor);
        rightColumnInfo.add(removeBookButton);

        // View all books button
        viewBooksButton = new JButton("View Books");
        viewBooksButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewBooksButton.setBackground(defaultButtonColor);
        rightColumnInfo.add(viewBooksButton);

        rightColumn.add(rightColumnInfo);

        return rightColumn;
    }

    // EFFECTS: Creates all GUI elements for the back button at the bottom and
    // returns the JPanel object
    private JPanel createBackButtonSection() {
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBackground(backgroundColor);
        backButtonPanel.setSize(WIDTH, HEIGHT);

        // Title section
        backButton = new JButton("Back");
        backButton.setSize(getWidth(), backButton.getMinimumSize().height);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        backButton.setBackground(warningButtonColor);
        backButtonPanel.add(backButton);

        return backButtonPanel;
    }

    // REQUIRES: All JButton variables are not null
    // MODIFIES: this
    // EFFECTS: Sets the buttons' actions and event listeners
    private void setButtonFunctionality() {
        newBookButton.setActionCommand("new book");
        removeBookButton.setActionCommand("remove book");
        viewStatsButton.setActionCommand("view stats");
        viewBooksButton.setActionCommand("view books");
        backButton.setActionCommand("back");

        newBookButton.addActionListener(this);
        removeBookButton.addActionListener(this);
        viewStatsButton.addActionListener(this);
        viewBooksButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    // Taken from:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    // REQUIRES: e is not null
    // EFFECTS: Handles button inputs and runs the respective methods
    public void actionPerformed(ActionEvent e) {
        if ("new book".equals(e.getActionCommand())) {
            createBook();
        } else if ("remove book".equals(e.getActionCommand())) {
            removeBook();
        } else if ("view stats".equals(e.getActionCommand())) {
            viewStats();
        } else if ("view books".equals(e.getActionCommand())) {
            viewBooks();
        } else if ("back".equals(e.getActionCommand())) {
            dispose();
        }
    }

    // REQUIRES: library is not null
    // EFFECTS: Opens a new window to display the user's reading statistics for the
    // current library
    private void viewStats() {
        new TextWindow("Reading statistics for " + library.getName(), library.getAveragePageCount(),
                library.getAverageWordCount(),
                library.getAverageDuration());
    }

    // REQUIRES: library is not null
    // EFFECTS: Creates a window to show all books tracked in the current library
    private void viewBooks() {
        new TextWindow(this.library);
    }

    // REQUIRES: library is not null
    // MODIFIES: this
    // EFFECTS: Asks user to choose a book to delete and deletes the given book
    private void removeBook() {
        Object[] books = new Object[library.getBookCollection().size() + 1];
        books[0] = "";
        for (int i = 0; i < library.getBookCollection().size(); i++) {
            books[i + 1] = library.getBookCollection().get(i).getName();
        }

        String bookNameInput = (String) JOptionPane.showInputDialog(this, "What book do you want to delete?",
                "Delete a book", JOptionPane.DEFAULT_OPTION, null, books, "");

        if (bookNameInput != null && bookNameInput.length() >= 1) {
            library.removeBookFromLibrary(bookNameInput);
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks for user input for the book name to create
    private void createBook() {
        String bookNameInput = JOptionPane.showInputDialog(this,
                "What's the name of the book you want to add?", "Add a new book",
                JOptionPane.DEFAULT_OPTION);
        String pageCountInput = JOptionPane.showInputDialog(this,
                "What's the page count of the book?", "Add a new book",
                JOptionPane.DEFAULT_OPTION);
        String wordCountInput = JOptionPane.showInputDialog(this,
                "What's the word count of the book?", "Add a new book",
                JOptionPane.DEFAULT_OPTION);
        String durationInput = JOptionPane.showInputDialog(this,
                "How long did you take to finish the book?", "Add a new book",
                JOptionPane.DEFAULT_OPTION);

        try {
            if (bookNameInput != null && bookNameInput.length() >= 1) {
                library.addBookToHistory(new Book(bookNameInput, Integer.parseInt(pageCountInput),
                        Integer.parseInt(wordCountInput), Integer.parseInt(durationInput)));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to add the book to the current library! Please try again.",
                    "Unable to add book", JOptionPane.ERROR_MESSAGE);
        }
    }
}
