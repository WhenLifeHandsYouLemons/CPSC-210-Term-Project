package ui;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.*;
import persistence.*;

// Creates the basic text (console) interface for the BookTrackerApp
public class BookTrackerAppConsole {
    public BookTrackerAppConsole() {
        // Create scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String option = "";

        // Create main application instance
        LibraryApp bookTrackerApp = new LibraryApp();

        // Create serialiser instances for save/load features
        String filePath = "./data/bookTrackerAppData.json";
        Writer writer = new Writer(filePath);
        Reader reader = new Reader(filePath);

        // Run main program
        homePage(bookTrackerApp, scanner, option, writer, reader);

        // Close scanner to stop memory leaks
        scanner.close();
    }

    // MODIFIES: bookTrackerApp, option, writer, reader
    // EFFECTS: Prints out available user options and asks for user input until
    // "exit" is typed
    private static void homePage(LibraryApp bookTrackerApp, Scanner scanner, String option, Writer writer,
            Reader reader) {
        do {
            printHomeHelpMessage();

            option = scanner.nextLine();
            if (option.equals("new library")) {
                newLibraryOption(bookTrackerApp, scanner);
            } else if (option.equals("view library")) {
                viewLibraryOption(bookTrackerApp, scanner);
            } else if (option.equals("view stats")) {
                viewStatisticsOption(bookTrackerApp);
            } else if (option.equals("view all books")) {
                viewAllBooksOption(bookTrackerApp);
            } else if (option.equals("remove library")) {
                removeLibraryOption(bookTrackerApp, scanner);
            } else if (option.equals("save library")) {
                saveLibraryOption(writer, bookTrackerApp);
            } else if (option.equals("load library")) {
                bookTrackerApp = loadLibraryOption(reader, bookTrackerApp);
            }
        } while (!option.equals("exit"));
    }

    // MODIFIES: writer
    // EFFECTS: Saves all the data currently stored in bookTrackerApp to a JSON file
    private static void saveLibraryOption(Writer writer, LibraryApp bookTrackerApp) {
        try {
            writer.writeToFile(bookTrackerApp);
            System.out.println("Saved libraries to '" + writer.getFilePath() + "'!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save libraries to '" + writer.getFilePath() + "'! Please try again.");
        }
    }

    // MODIFIES: bookTrackerApp
    // EFFECTS: Reads app data from a JSON file into the given bookTrackerApp
    // instance
    private static LibraryApp loadLibraryOption(Reader reader, LibraryApp bookTrackerApp) {
        try {
            bookTrackerApp = reader.readFromFile();
            System.out.println("Loaded libraries from '" + reader.getFilePath() + "'!");
        } catch (IOException e) {
            System.out.println("Unable to load libraries from '" + reader.getFilePath() + "'! Please try again.");
        }

        return bookTrackerApp;
    }

    // EFFECTS: Prints out the options available to the user on the home page
    private static void printHomeHelpMessage() {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("Choose one of the following options:\n");
        System.out.println("'new library' - Adds a new library");
        System.out.println("'view library' - Opens a library");
        System.out.println("'view stats' - Prints out your total reading statistics");
        System.out.println("'view all books' - Prints out all the books you've added");
        System.out.println("'remove library' - Removes a library");
        System.out.println("'save library' - Saves all libraries and books tracked to an external file.");
        System.out.println("'load library' - Loads libraries and books tracked from an external file.");
        System.out.println("'exit' - Closes the program");
        System.out.println("----------------------------------------------------------");
    }

    // EFFECTS: Prints out the options available to the user inside a library
    private static void printLibraryHelpMessage(Library selectedLibrary) {
        System.out.println("\n----------------------------------------------------------");
        System.out.println(selectedLibrary.getName());
        System.out.println("Choose one of the following options:\n");
        System.out.print("'add book' - Adds a new book to ");
        System.out.println(selectedLibrary.getName());
        System.out.print("'view stats' - Prints out your reading statistics for ");
        System.out.println(selectedLibrary.getName());
        System.out.print("'view books' - Prints out the books you've added to ");
        System.out.println(selectedLibrary.getName());
        System.out.print("'remove book' - Removes a book from ");
        System.out.println(selectedLibrary.getName());
        System.out.println("'back' - Exits the library and goes back to the home page");
        System.out.println("----------------------------------------------------------");
    }

    // MODIFIES: bookTrackerApp
    // EFFECTS: Prints out all libraries available, asks for a library's name, and
    // opens that library
    private static void removeLibraryOption(LibraryApp bookTrackerApp, Scanner scanner) {
        System.out.println("Libraries available:");
        for (Library library : bookTrackerApp.getLibraries()) {
            System.out.print("- ");
            System.out.println(library.getName());
        }

        // Ask for library name input
        System.out.print("Enter the name of the library to remove: ");
        String libraryName = scanner.nextLine();

        if (bookTrackerApp.findLibrary(libraryName) != null) {
            // Confirm if want to remove
            System.out.print("Are you sure you want to remove '");
            System.out.print(libraryName);
            System.out.println("'? (Enter 'yes' to delete)");

            String confirm = scanner.nextLine();
            if (confirm.equals("yes")) {
                // Remove library
                bookTrackerApp.removeLibrary(libraryName);
                System.out.print("The library '");
                System.out.print(libraryName);
                System.out.println("' has been removed successfully!");
            } else {
                System.out.println("The library was not removed!");
            }
        } else {
            System.out.println("The library you entered wasn't found!");
        }
    }

    // MODIFIES: bookTrackerApp
    // EFFECTS: Asks for the name of the new library and creates it
    private static void newLibraryOption(LibraryApp bookTrackerApp, Scanner scanner) {
        System.out.print("\nEnter the name of the library to be created: ");

        // Ask for library name input
        String name = scanner.nextLine();
        if (!name.equals("")) {
            bookTrackerApp.addLibrary(name);
            System.out.print("Created library titled '");
            System.out.print(name);
            System.out.println("' successfully!");
        }
    }

    // EFFECTS: Prints out information on all books that have been tracked
    private static void viewAllBooksOption(LibraryApp bookTrackerApp) {
        int index = 1;

        System.out.println("\n------------------------------------------------");
        System.out.println("All books:\n");
        for (Library library : bookTrackerApp.getLibraries()) {
            for (Book book : library.getBookCollection()) {
                System.out.print(index);
                System.out.print(". ");
                System.out.println(book.getName());
                System.out.print("    Page count: ");
                System.out.println(book.getPageCount());
                System.out.print("    Word count: ");
                System.out.println(book.getWordCount());
                System.out.print("    Reading duration (minutes): ");
                System.out.println(book.getDuration());

                index++;
            }
        }
        System.out.println("\n------------------------------------------------");
    }

    // EFFECTS: Prints out overall statistics for all books
    private static void viewStatisticsOption(LibraryApp bookTrackerApp) {
        System.out.println("\n------------------------------------------------");
        System.out.println("All Statistics:\n");
        System.out.print("Average book length (in pages): ");
        System.out.println(bookTrackerApp.getAveragePageCount());
        System.out.print("Average book length (in words): ");
        System.out.println(bookTrackerApp.getAverageWordCount());
        System.out.print("Average reading duration (in minutes): ");
        System.out.println(bookTrackerApp.getAverageDuration());
        System.out.println("\n------------------------------------------------");
    }

    // EFFECTS: Prints out all libraries available, asks for the name of a library
    // and opens it
    private static void viewLibraryOption(LibraryApp bookTrackerApp, Scanner scanner) {
        listAllLibraries(bookTrackerApp);

        System.out.print("Enter the name of the library you want to open: ");
        String option = scanner.nextLine();

        if (bookTrackerApp.findLibrary(option) != null) {
            Library selectedLibrary = bookTrackerApp.findLibrary(option);

            System.out.print("Opened '");
            System.out.print(selectedLibrary.getName());
            System.out.println("'!");

            listLibraryOptions(selectedLibrary, scanner);
        } else {
            System.out.println("Sorry! The library you've entered doesn't exist.");
        }
    }

    // EFFECTS: Prints out all available libraries
    private static void listAllLibraries(LibraryApp bookTrackerApp) {
        System.out.println("\nLibraries available:");
        List<Library> libraries = bookTrackerApp.getLibraries();
        for (Library library : libraries) {
            System.out.print("- ");
            System.out.println(library.getName());
        }
        System.out.println("");
    }

    // MODIFIES: selectedLibrary
    // EFFECTS: Asks for the information about a book and adds it to the selected
    // library
    private static void addBookOption(Library selectedLibrary, Scanner scanner) {
        try {
            System.out.print("Enter the name of the book: ");
            String bookName = scanner.nextLine();
            System.out.print("Enter the number of pages: ");
            int pageCount = scanner.nextInt();
            System.out.print("Enter the number of words (estimate using: words_per_page * no_of_pages): ");
            int wordCount = scanner.nextInt();
            System.out.print("Enter the time taken to read the book (in whole minutes): ");
            int readingDuration = scanner.nextInt();

            if (bookName != "" && pageCount > 0 && wordCount > 0 && readingDuration > 0) {
                Book newBook = new Book(bookName, pageCount, wordCount, readingDuration);
                selectedLibrary.addBookToHistory(newBook);

                System.out.print("Added '");
                System.out.print(bookName);
                System.out.println("' to the library successfully!");
            } else {
                System.out.println("Sorry! Couldn't create the book.");
            }
        } catch (Exception e) {
            System.out.println("Sorry! Couldn't create the book and add it to library.");
        }
    }

    // EFFECTS: Prints out the overall statistics of the selected library
    private static void viewLibraryStatisticsOption(Library selectedLibrary) {
        System.out.println("\n------------------------------------------------");
        System.out.print(selectedLibrary.getName());
        System.out.println(" Statistics:\n");
        System.out.print("Average book length (in pages): ");
        System.out.println(selectedLibrary.getAveragePageCount());
        System.out.print("Average book length (in words): ");
        System.out.println(selectedLibrary.getAverageWordCount());
        System.out.print("Average reading duration (in minutes): ");
        System.out.println(selectedLibrary.getAverageDuration());
        System.out.println("\n------------------------------------------------");
    }

    // EFFECTS: Prints out information on all books in the selected library
    private static void viewLibraryBooksOption(Library selectedLibrary) {
        int index = 1;

        System.out.println("\n------------------------------------------------");
        System.out.print(selectedLibrary.getName());
        System.out.println("'s books:\n");
        for (Book book : selectedLibrary.getBookCollection()) {
            System.out.print(index);
            System.out.print(". ");
            System.out.println(book.getName());
            System.out.print("    Page count: ");
            System.out.println(book.getPageCount());
            System.out.print("    Word count: ");
            System.out.println(book.getWordCount());
            System.out.print("    Reading duration (minutes): ");
            System.out.println(book.getDuration());

            index++;
        }
        System.out.println("\n------------------------------------------------");
    }

    // MODIFIES: selectedLibrary
    // EFFECTS: Prints out all books in the selected library, asks for a book name,
    // and removes that book
    private static void removeBookOption(Library selectedLibrary, Scanner scanner) {
        System.out.println("Books available:");
        for (Book book : selectedLibrary.getBookCollection()) {
            System.out.print("- ");
            System.out.println(book.getName());
        }

        System.out.print("Enter the name of the book to remove: ");
        String bookName = scanner.nextLine();

        if (selectedLibrary.findBook(bookName) != null) {
            System.out.print("Are you sure you want to remove '");
            System.out.print(bookName);
            System.out.println("'? (Enter 'yes' to delete)");

            String confirm = scanner.nextLine();
            if (confirm.equals("yes")) {
                selectedLibrary.removeBookFromLibrary(bookName);
                System.out.print("The book '");
                System.out.print(bookName);
                System.out.println("' has been removed successfully!");
            } else {
                System.out.println("The book was not removed from the library!");
            }
        } else {
            System.out.println("The book you entered wasn't found in this library!");
        }
    }

    // Prints out available options in the library and asks for input on an option
    private static void listLibraryOptions(Library selectedLibrary, Scanner scanner) {
        String option;

        do {
            printLibraryHelpMessage(selectedLibrary);

            option = scanner.nextLine();
            switch (option) {
                case "add book":
                    addBookOption(selectedLibrary, scanner);
                    break;
                case "view stats":
                    viewLibraryStatisticsOption(selectedLibrary);
                    break;
                case "view books":
                    viewLibraryBooksOption(selectedLibrary);
                    break;
                case "remove book":
                    removeBookOption(selectedLibrary, scanner);
                    break;
            }
        } while (!option.equals("back"));
    }
}
