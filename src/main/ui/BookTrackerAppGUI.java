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

public class BookTrackerAppGUI extends JFrame implements ActionListener {
    protected LibraryApp bta;
    private String filePath = "./data/bookTrackerAppData.json";
    private DefaultListModel<String> libraryListModel;
    private JList<String> libraryList;

    public BookTrackerAppGUI() {
        super("Book Tracker App"); // Create the main app frame

        bta = new LibraryApp();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the program to exit when the close button is
                                                        // clicked
        setSize(850, 600); // Set window size
        setLocationRelativeTo(null);

        int columnPadding = 50;

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 255, 255));

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayout(1, 3));

        // Title section
        JLabel titleText = new JLabel("Book Tracker App");
        titleText.setSize(getWidth(), titleText.getMinimumSize().height);
        titleText.setFont(new Font("SansSerif", Font.BOLD, 32));
        titlePanel.add(titleText);

        // First column section
        JPanel firstColumn = new JPanel();
        JPanel firstColumnInfo = new JPanel();
        firstColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        firstColumn.setBackground(new Color(255, 0, 0));
        firstColumnInfo.setBackground(null);
        firstColumn.setLayout(new GridLayout(1, 1));
        firstColumnInfo.setLayout(new GridLayout(3, 1, 0, 75));

        // New library button
        JButton newLibraryButton = new JButton("New Library");
        newLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        newLibraryButton.setBackground(new Color(255, 255, 0));
        firstColumnInfo.add(newLibraryButton);

        // View statistics button
        JButton viewStatsButton = new JButton("View Statistics");
        viewStatsButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewStatsButton.setBackground(new Color(0, 255, 255));
        firstColumnInfo.add(viewStatsButton);

        // Save library button
        JButton saveLibraryButton = new JButton("Save Library");
        saveLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        saveLibraryButton.setBackground(new Color(255, 0, 255));
        firstColumnInfo.add(saveLibraryButton);

        // Second column section
        JPanel secondColumn = new JPanel();
        JPanel secondColumnInfo = new JPanel();
        secondColumn
                .setBorder(BorderFactory.createEmptyBorder(columnPadding, columnPadding, columnPadding, columnPadding));
        secondColumn.setBackground(new Color(0, 255, 0));
        secondColumnInfo.setBackground(null);
        secondColumn.setLayout(new GridLayout(1, 1));
        secondColumnInfo.setLayout(new GridLayout(3, 1, 0, 75));

        // Remove library button
        JButton removeLibraryButton = new JButton("Remove Library");
        removeLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        removeLibraryButton.setBackground(new Color(0, 255, 255));
        secondColumnInfo.add(removeLibraryButton);

        // View all books button
        JButton viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        viewAllBooksButton.setBackground(new Color(255, 0, 255));
        secondColumnInfo.add(viewAllBooksButton);

        // Load library button
        JButton loadLibraryButton = new JButton("Load Library");
        loadLibraryButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        loadLibraryButton.setBackground(new Color(255, 255, 0));
        secondColumnInfo.add(loadLibraryButton);

        // Third column section
        JPanel thirdColumn = new JPanel();
        JPanel thirdColumnInfo = new JPanel();
        thirdColumn.setBorder(
                BorderFactory.createEmptyBorder(columnPadding, columnPadding / 2, columnPadding, columnPadding / 2));
        thirdColumn.setBackground(new Color(0, 0, 255));
        thirdColumnInfo.setBackground(null);
        // Set layout for roughly 2:1 ratio
        thirdColumn.setLayout(new GridLayout(1, 1));
        GridBagLayout columnGridBagLayout = new GridBagLayout();
        thirdColumnInfo.setLayout(columnGridBagLayout);
        GridBagConstraints columnGridBagConstraints = new GridBagConstraints();
        columnGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // View library panel
        JPanel viewLibraryPanel = new JPanel();

        GridBagLayout viewLibraryGridBagLayout = new GridBagLayout();
        GridBagConstraints viewLibraryGridBagConstraints = new GridBagConstraints();
        viewLibraryGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        viewLibraryPanel.setLayout(viewLibraryGridBagLayout);
        viewLibraryPanel.setBackground(new Color(0, 255, 0));
        columnGridBagConstraints.weightx = 1.0;
        columnGridBagConstraints.weighty = 0.6;
        columnGridBagConstraints.gridx = 0;
        columnGridBagConstraints.gridy = 0;
        columnGridBagConstraints.fill = GridBagConstraints.BOTH;

        // Library list section
        libraryListModel = new DefaultListModel<String>();
        libraryList = new JList<String>(libraryListModel);
        libraryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        libraryList.setLayoutOrientation(JList.VERTICAL);

        // Library list scroll pane
        JScrollPane libraryListScrollPane = new JScrollPane(libraryList);
        libraryListScrollPane.setSize(1000, 1000);
        viewLibraryGridBagConstraints.weightx = 1.0;
        viewLibraryGridBagConstraints.weighty = 0.9;
        viewLibraryGridBagConstraints.gridx = 0;
        viewLibraryGridBagConstraints.gridy = 0;
        viewLibraryGridBagConstraints.fill = GridBagConstraints.BOTH;
        viewLibraryPanel.add(libraryListScrollPane, viewLibraryGridBagConstraints);

        // Open library button
        JButton openLibraryButton = new JButton("Open selected library");
        openLibraryButton.setBackground(new Color(200, 55, 100));
        viewLibraryGridBagConstraints.weighty = 0.1;
        viewLibraryGridBagConstraints.gridy = 1;
        viewLibraryPanel.add(openLibraryButton, viewLibraryGridBagConstraints);

        thirdColumnInfo.add(viewLibraryPanel, columnGridBagConstraints);

        // Spacer panel
        JPanel spacerPanel = new JPanel();
        columnGridBagConstraints.weighty = 0.15;
        columnGridBagConstraints.gridy = 1;
        columnGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        thirdColumnInfo.add(spacerPanel, columnGridBagConstraints);

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
        exitButton.setBackground(new Color(255, 255, 0));
        columnGridBagConstraints.weighty = 0.15;
        columnGridBagConstraints.gridy = 2;
        columnGridBagConstraints.fill = GridBagConstraints.BOTH;
        thirdColumnInfo.add(exitButton, columnGridBagConstraints);

        // Set button actions, event listeners, and tooltips
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

        // Add all items to the main GUI frame
        add(titlePanel, BorderLayout.PAGE_START);
        firstColumn.add(firstColumnInfo);
        secondColumn.add(secondColumnInfo);
        thirdColumn.add(thirdColumnInfo);
        mainContentPanel.add(firstColumn, BorderLayout.LINE_START);
        mainContentPanel.add(secondColumn, BorderLayout.CENTER);
        mainContentPanel.add(thirdColumn, BorderLayout.LINE_END);
        add(mainContentPanel);
        setVisible(true); // Runs the GUI to show up
    }

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
            bta = loadLibrary();
        } else if ("open library".equals(e.getActionCommand())) {
            openLibrary();
        } else if ("exit".equals(e.getActionCommand())) {
            dispose();
        }
    }

    private void viewStats() {
        System.out.println("Showing all statistics...");
        bta.getAverageDuration();
        bta.getAveragePageCount();
        bta.getAverageWordCount();
    }

    private void viewAllBooks() {
        System.out.println("Showing all books' info...");
        for (Library lib : bta.getLibraries()) {
            lib.getBookCollection();
        }
    }

    private void openLibrary() {
        for (Library lib : bta.getLibraries()) {
            if (lib.getName().equals(libraryList.getSelectedValue())) {
                System.out.println(lib.getName() + " was selected.");
                return;
            }
        }

        System.out.println("No library was selected.");
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
    // MODIFIES: bta
    // EFFECTS: Reads in the book tracker app data from an external file
    private LibraryApp loadLibrary() {
        Reader reader = new Reader(filePath);

        try {
            bta = reader.readFromFile();
            JOptionPane.showMessageDialog(this,
                    "Loaded libraries from '" + reader.getFilePath() + "'!",
                    "Loaded libraries", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Unable to load libraries from '" + reader.getFilePath() + "'! Please try again.",
                    "Unable to load libraries", JOptionPane.ERROR_MESSAGE);
        }

        return bta;
    }

    // MODIFIES: bta
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
    // MODIFIES: bta
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
}
