package ui;

public class Main {
    public static void main(String[] args) {
        BookTrackerApp bookTrackerApp = new BookTrackerApp();

        // While user doesn't input "exit" (use do-while):
            // Ask for option input
            // Check what user inputted (use switch-case):
                // new library:
                    // Ask for library name input
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
                // view all stats:
                    // Show all stats
                // view all books read:
                    // Show all books and book info
                // remove library
                    // Ask for library name input
                    // Confirm if want to remove
                        // If confirmed, remove library
                        // Else do nothing
                // exit
                    // Exit the program
    }
}
