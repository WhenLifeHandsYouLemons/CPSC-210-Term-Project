package ui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.LibraryApp;
import model.Library;
import persistence.Reader;
import persistence.Writer;

// Creates the GUI interface for the BookTrackerApp
public class BookTrackerAppGUI extends JFrame implements ActionListener {
    protected LibraryApp bta;

    private String filePath = "./data/bookTrackerAppData.json";

    private DefaultListModel<String> libraryListModel;
    private JList<String> libraryList;

    private JButton newLibraryButton;
    private JButton removeLibraryButton;
    private JButton viewStatsButton;
    private JButton viewAllBooksButton;
    private JButton saveLibraryButton;
    private JButton loadLibraryButton;
    private JButton openLibraryButton;
    private JButton exitButton;

    private Color backgroundColor;
    private Color defaultButtonColor;
    private Color warningButtonColor;
    private Color transparentColor;

    // EFFECTS: Creates a window to show the main app page, creates the
    // BookTrackerApp instance
    public BookTrackerAppGUI() {
        super("Book Tracker App"); // Create the main app frame

        initialiseColors();

        bta = new LibraryApp();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Set the program to exit when the close button is
        // clicked
        this.setSize(850, 600); // Set window size
        this.setLocationRelativeTo(null);

        int columnPadding = 50;

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayout(1, 3));

        JPanel titleSection = createTitleSection();

        JPanel firstColumnSection = createFirstColumnSection(columnPadding);
        JPanel secondColumnSection = createSecondColumnSection(columnPadding);
        JPanel thirdColumnSection = createThirdColumnSection(columnPadding);

        setButtonFunctionality();

        // Add all items to the main GUI frame
        this.add(titleSection, BorderLayout.PAGE_START);
        mainContentPanel.add(firstColumnSection, BorderLayout.LINE_START);
        mainContentPanel.add(secondColumnSection, BorderLayout.CENTER);
        mainContentPanel.add(thirdColumnSection, BorderLayout.LINE_END);
        this.add(mainContentPanel);
        this.setVisible(true); // Runs the GUI to show up
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
        JLabel titleText = new JLabel("Book Tracker App");
        titleText.setSize(getWidth(), titleText.getMinimumSize().height);
        titleText.setFont(new Font("SansSerif", Font.BOLD, 32));
        titlePanel.add(titleText);

        return titlePanel;
    }

    // REQUIRES: columnPadding > 1
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the first column and returns the first
    // column section
    private JPanel createFirstColumnSection(int columnPadding) {
        JPanel firstColumn = new JPanel();
        JPanel firstColumnInfo = new JPanel();
        firstColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        firstColumn.setBackground(backgroundColor);
        firstColumnInfo.setBackground(null);
        firstColumn.setLayout(new GridLayout(1, 1));
        firstColumnInfo.setLayout(new GridLayout(3, 1, 0, 75));

        // New library button
        newLibraryButton = new JButton("New Library");
        newLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        newLibraryButton.setBackground(defaultButtonColor);
        firstColumnInfo.add(newLibraryButton);

        // View statistics button
        viewStatsButton = new JButton("View Statistics");
        viewStatsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewStatsButton.setBackground(defaultButtonColor);
        firstColumnInfo.add(viewStatsButton);

        // Save library button
        saveLibraryButton = new JButton("Save Library");
        saveLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        saveLibraryButton.setBackground(defaultButtonColor);
        firstColumnInfo.add(saveLibraryButton);

        firstColumn.add(firstColumnInfo);

        return firstColumn;
    }

    // REQUIRES: columnPadding > 1
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the second column and returns the
    // second column section
    private JPanel createSecondColumnSection(int columnPadding) {
        JPanel secondColumn = new JPanel();
        JPanel secondColumnInfo = new JPanel();
        secondColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        secondColumn.setBackground(backgroundColor);
        secondColumnInfo.setBackground(null);
        secondColumn.setLayout(new GridLayout(1, 1));
        secondColumnInfo.setLayout(new GridLayout(3, 1, 0, 75));

        // Remove library button
        removeLibraryButton = new JButton("Remove Library");
        removeLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        removeLibraryButton.setBackground(defaultButtonColor);
        secondColumnInfo.add(removeLibraryButton);

        // View all books button
        viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewAllBooksButton.setBackground(defaultButtonColor);
        secondColumnInfo.add(viewAllBooksButton);

        // Load library button
        loadLibraryButton = new JButton("Load Library");
        loadLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        loadLibraryButton.setBackground(defaultButtonColor);
        secondColumnInfo.add(loadLibraryButton);

        secondColumn.add(secondColumnInfo);

        return secondColumn;
    }

    // REQUIRES: columnPadding > 1
    // MODIFIES: this
    // EFFECTS: Creates all GUI elements for the third column and returns the third
    // column section
    private JPanel createThirdColumnSection(int columnPadding) {
        JPanel thirdColumn = new JPanel();
        JPanel thirdColumnInfo = new JPanel();
        thirdColumn.setBorder(
                BorderFactory.createEmptyBorder(columnPadding, columnPadding / 2, columnPadding, columnPadding / 2));
        thirdColumn.setBackground(backgroundColor);
        thirdColumnInfo.setBackground(null);
        thirdColumn.setLayout(new GridLayout(1, 1));
        GridBagLayout columnGridBagLayout = new GridBagLayout();
        thirdColumnInfo.setLayout(columnGridBagLayout); // Set layout for roughly 2:1 ratio
        GridBagConstraints columnGridBagConstraints = createGridBagConstraints();

        JPanel viewLibraryPanel = createOpenLibrarySection(columnGridBagConstraints);

        thirdColumnInfo.add(viewLibraryPanel, columnGridBagConstraints);

        // Spacer panel
        JPanel spacerPanel = createSpacers(columnGridBagConstraints);
        thirdColumnInfo.add(spacerPanel, columnGridBagConstraints);

        // Exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        exitButton.setBackground(warningButtonColor);
        columnGridBagConstraints.weighty = 0.15;
        columnGridBagConstraints.gridy = 2;
        columnGridBagConstraints.fill = GridBagConstraints.BOTH;
        thirdColumnInfo.add(exitButton, columnGridBagConstraints);

        thirdColumn.add(thirdColumnInfo);

        return thirdColumn;
    }

    // REQUIRES: columnGridBagConstraints is not null
    // MODIFIES: this
    // EFFECTS: Creates the section for listing libraries and opening a selected
    // library
    private JPanel createOpenLibrarySection(GridBagConstraints columnGridBagConstraints) {
        JPanel viewLibraryPanel = new JPanel();

        GridBagLayout viewLibraryGridBagLayout = new GridBagLayout();
        GridBagConstraints viewLibraryGridBagConstraints = createGridBagConstraints();

        viewLibraryPanel.setLayout(viewLibraryGridBagLayout);
        viewLibraryPanel.setBackground(backgroundColor);
        columnGridBagConstraints.weighty = 0.6;

        // Library list section
        libraryListModel = new DefaultListModel<String>();
        libraryList = new JList<String>(libraryListModel);
        libraryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryList.setLayoutOrientation(JList.VERTICAL);

        // Library list scroll pane
        JScrollPane libraryListScrollPane = new JScrollPane(libraryList);
        libraryListScrollPane.setSize(1000, 1000);
        viewLibraryGridBagConstraints.weighty = 0.9;
        viewLibraryGridBagConstraints.gridy = 0;
        viewLibraryPanel.add(libraryListScrollPane, viewLibraryGridBagConstraints);

        // Open library button
        openLibraryButton = new JButton("Open selected library");
        openLibraryButton.setBackground(defaultButtonColor);
        openLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        viewLibraryGridBagConstraints.weighty = 0.1;
        viewLibraryGridBagConstraints.gridy = 1;
        viewLibraryPanel.add(openLibraryButton, viewLibraryGridBagConstraints);

        return viewLibraryPanel;
    }

    // EFFECTS: Creates a new GridBagConstraints object and returns it
    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;

        return constraints;
    }

    // REQUIRES: columnGridBagConstraints is not null
    // MODIFIES: columnGridBagConstraints
    // EFFECTS: Creates the GUI elements for the spacer panel
    private JPanel createSpacers(GridBagConstraints columnGridBagConstraints) {
        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(transparentColor);
        columnGridBagConstraints.weighty = 0.15;
        columnGridBagConstraints.gridy = 1;
        columnGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        return spacerPanel;
    }

    // REQUIRES: All JButton variables are not null
    // MODIFIES: this
    // EFFECTS: Sets the buttons' actions and event listeners
    private void setButtonFunctionality() {
        newLibraryButton.setActionCommand("new library");
        removeLibraryButton.setActionCommand("remove library");
        viewStatsButton.setActionCommand("view stats");
        viewAllBooksButton.setActionCommand("view all books");
        saveLibraryButton.setActionCommand("save library");
        loadLibraryButton.setActionCommand("load library");
        openLibraryButton.setActionCommand("open library");
        exitButton.setActionCommand("exit");

        newLibraryButton.addActionListener(this);
        viewStatsButton.addActionListener(this);
        saveLibraryButton.addActionListener(this);
        removeLibraryButton.addActionListener(this);
        viewAllBooksButton.addActionListener(this);
        loadLibraryButton.addActionListener(this);
        openLibraryButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    // Taken from:
    // https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
    // REQUIRES: e is not null
    // EFFECTS: Handles button inputs and runs the respective methods
    public void actionPerformed(ActionEvent e) {
        if ("new library".equals(e.getActionCommand())) {
            createLibrary();
        } else if ("remove library".equals(e.getActionCommand())) {
            removeLibrary();
        } else if ("view stats".equals(e.getActionCommand())) {
            viewStats();
        } else if ("view all books".equals(e.getActionCommand())) {
            viewAllBooks();
        } else if ("save library".equals(e.getActionCommand())) {
            saveLibrary();
        } else if ("load library".equals(e.getActionCommand())) {
            loadLibrary();
        } else if ("open library".equals(e.getActionCommand())) {
            openLibrary();
        } else if ("exit".equals(e.getActionCommand())) {
            dispose();
        }
    }

    // REQUIRES: bta is not null
    // EFFECTS: Creates a new window and passes the selected library information to
    // it
    private void openLibrary() {
        for (Library lib : bta.getLibraries()) {
            if (lib.getName().equals(libraryList.getSelectedValue())) {
                new LibraryWindow(lib);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "No library is selected! Please select a library first.",
                "Select a library", JOptionPane.ERROR_MESSAGE);
    }

    // REQUIRES: libraryListModel is not null
    // MODIFIES: this
    // EFFECTS: Refreshes the list of libraries currently created
    private void updateLibraryList() {
        libraryListModel.clear();

        for (Library lib : bta.getLibraries()) {
            libraryListModel.addElement(lib.getName());
        }
    }

    // REQUIRES: bta is not null, data folder exists
    // EFFECTS: Saves the bta app data to an external file
    private void saveLibrary() {
        Writer writer = new Writer(filePath);

        try {
            writer.writeToFile(bta);

            // Taken from:
            // https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
            JOptionPane.showMessageDialog(this,
                    "Saved libraries to '" + writer.getFilePath() + "'!",
                    "Saved libraries", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to save libraries to '" + writer.getFilePath() + "'! Please try again.",
                    "Unable to save libraries", JOptionPane.ERROR_MESSAGE);
        }
    }

    // REQUIRES: data folder exists
    // MODIFIES: this
    // EFFECTS: Reads in the book tracker app data from an external file
    private void loadLibrary() {
        Reader reader = new Reader(filePath);

        try {
            bta = reader.readFromFile();
            updateLibraryList();

            JOptionPane.showMessageDialog(this,
                    "Loaded libraries from '" + reader.getFilePath() + "'!",
                    "Loaded libraries", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load libraries from '" + reader.getFilePath() + "'! Please try again.",
                    "Unable to load libraries", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Asks for user input for the library name to create
    private void createLibrary() {
        String libraryNameInput = JOptionPane.showInputDialog(this,
                "What is the name of the library you want to create?", "Create a new library",
                JOptionPane.DEFAULT_OPTION);

        if (libraryNameInput != null && libraryNameInput.length() >= 1) {
            bta.addLibrary(libraryNameInput);
        }

        updateLibraryList();
    }

    // REQUIRES: bta is not null
    // MODIFIES: this
    // EFFECTS: Asks user to choose a library to delete and deletes the given
    // library
    private void removeLibrary() {
        Object[] libraries = new Object[bta.getLibraries().size() + 1];
        libraries[0] = "";
        for (int i = 0; i < bta.getLibraries().size(); i++) {
            libraries[i + 1] = bta.getLibraries().get(i).getName();
        }

        String libraryNameInput = (String) JOptionPane.showInputDialog(this, "What library do you want to delete?",
                "Delete a library", JOptionPane.DEFAULT_OPTION, null, libraries, "");

        if (libraryNameInput != null && libraryNameInput.length() >= 1) {
            bta.removeLibrary(libraryNameInput);
        }

        updateLibraryList();
    }

    // REQUIRES: bta is not null
    // EFFECTS: Opens a new window to display the user's reading statistics
    private void viewStats() {
        new TextWindow("Total reading statistics", bta.getAveragePageCount(),
                bta.getAverageWordCount(), bta.getAverageDuration());
    }

    // REQUIRES: bta is not null
    // EFFECTS: Creates a window to show all books tracked
    private void viewAllBooks() {
        new TextWindow(bta.getLibraries());
    }
}
