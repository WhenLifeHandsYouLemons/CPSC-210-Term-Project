package ui;

import java.util.Scanner;
import java.util.List;

import model.Book;
import model.Library;

public class Main {
    public static void main(String[] args) {
        // Create scanner object for user input
        Scanner scanner = new Scanner(System.in);
        String option;

        BookTrackerApp bookTrackerApp = new BookTrackerApp();

        do {
            // Ask for option input
            option = scanner.nextLine();

            // Check what user inputted (use switch-case):
            switch (option) {
                // new library:
                case "new library":
                    // Ask for library name input
                    break;
                // view library:
                    // Ask for option input
                    // Check what user inputted:
                        // add book:
                            // Ask for book info input
                        // view library stats:
                            // Show library stats
                        // view library books
                            // Show library books and book info
                        // remove book
                            // Ask for book name input
                            // Remove book from library
                        // go back
                            // Go back to previous menu
                case "view library":
                    break;
                // view all stats:
                case "view stats":
                    // Show all stats
                    break;
                // view all books read:
                case "view all books":
                    // Show all books and book info
                    break;
                // remove library
                case "remove library":
                    // Ask for library name input
                    // Confirm if want to remove
                        // If confirmed, remove library
                        // Else do nothing
                    break;
            }
        } while (!option.equals("exit"));

        scanner.close();
    }
}
