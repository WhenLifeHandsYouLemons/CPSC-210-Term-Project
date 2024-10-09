package model;

import java.util.List;

public class Library {

    // MODIFIES: this
        // stub
    // EFFECTS: Initialises the collection array list with no items in it and assigns library name
    public Library(String name) {
    }

        return null;    // stub
    public List<Book> getBookCollection() {
    }

    // REQUIRES: book is not null
    // MODIFIES: this
    // EFFECTS: Adds the given Book book to the end of bookCollection
    public void addBookToHistory(Book book) {
        // stub
    }

    // REQUIRES: history.length() > 0
    // EFFECTS: Returns the book with the given name or null if none are found
    public Book findBook(String name) {
        return null; // stub
    }

    public String getLibraryName() {
        return "";  // stub
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average page count of the library's books
    public double getAveragePageCount() {
        return 0;   // stub
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average word count of the library's books
    public double getAverageWordCount() {
        return 0;   // stub
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average reading duration of the library's books
    public double getAverageDuration() {
        return 0;   // stub
    }
}
