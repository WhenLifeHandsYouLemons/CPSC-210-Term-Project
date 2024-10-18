package ui;

import java.util.Scanner;
import java.util.List;

import model.Book;
import model.Library;
import persistence.Writer;
import persistence.Reader;

public class Main {
    public static void main(String[] args) {
        // Create scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String option;

        BookTrackerApp bookTrackerApp = new BookTrackerApp();

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
            }
        } while (!option.equals("exit"));

        scanner.close();
    }

    private static void printHomeHelpMessage() {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("Choose one of the following options:\n");
        System.out.println("'new library' - Adds a new library");
        System.out.println("'view library' - Opens a library");
        System.out.println("'view stats' - Prints out your total reading statistics");
        System.out.println("'view all books' - Prints out all the books you've added");
        System.out.println("'remove library' - Removes a library");
        System.out.println("'exit' - Closes the program");
        System.out.println("----------------------------------------------------------");
    }

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

    private static void removeLibraryOption(BookTrackerApp bookTrackerApp, Scanner scanner) {
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

    private static void newLibraryOption(BookTrackerApp bookTrackerApp, Scanner scanner) {
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

    private static void viewAllBooksOption(BookTrackerApp bookTrackerApp) {
        int index = 1;

        // Show all books and book info
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

    private static void viewStatisticsOption(BookTrackerApp bookTrackerApp) {
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

    private static void viewLibraryOption(BookTrackerApp bookTrackerApp, Scanner scanner) {
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

    private static void listAllLibraries(BookTrackerApp bookTrackerApp) {
        System.out.println("\nLibraries available:");
        List<Library> libraries = bookTrackerApp.getLibraries();
        for (Library library : libraries) {
            System.out.print("- ");
            System.out.println(library.getName());
        }
        System.out.println("");
    }

    private static void addBookOption(Library selectedLibrary, Scanner scanner) {
        try {
            System.out.print("Enter the name of the book: ");
            String bookName = scanner.nextLine();
            System.out.print("Enter the number of pages: ");
            int pageCount = scanner.nextInt();
            System.out.print("Enter the time taken to read the book (in whole minutes): ");
            int readingDuration = scanner.nextInt();

            if (bookName != "" && pageCount > 0 && readingDuration > 0) {
                Book newBook = new Book(bookName, pageCount, readingDuration);
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
