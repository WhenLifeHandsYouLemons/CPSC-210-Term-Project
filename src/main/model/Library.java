package model;

import java.util.List;
import java.util.ArrayList;

public class Library {
    private String libraryName;
    private List<Book> bookCollection;

    // MODIFIES: this
    // EFFECTS: Initialises the collection array list with no items in it and assigns library name
    public Library(String name) {
        bookCollection = new ArrayList<Book>();
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    // REQUIRES: book is not null
    // MODIFIES: this
    // EFFECTS: Adds the given Book book to the end of bookCollection
    public void addBookToHistory(Book book) {
        this.bookCollection.add(book);
    }

    // REQUIRES: history.length() > 0
    // EFFECTS: Returns the book with the given name or null if none are found
    public Book findBook(String name) {
        for (Book book : this.bookCollection) {
            if (book.getName().equals(name)) {
                return book;
            }
        }

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
