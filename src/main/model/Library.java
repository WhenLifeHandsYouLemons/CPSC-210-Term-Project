package model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Stores a list of books and gives statistics based on those.
public class Library {
    private String libraryName;
    private List<Book> bookCollection;

    // MODIFIES: this
    // EFFECTS: Initialises the collection array list with no items in it and
    // assigns library name
    public Library(String name) {
        bookCollection = new ArrayList<Book>();
        this.libraryName = name;
    }

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    // REQUIRES: book is not null
    // MODIFIES: this
    // EFFECTS: Adds the given Book book to the end of bookCollection
    public void addBookToHistory(Book book) {
        this.bookCollection.add(book);

        EventLog.getInstance().logEvent(new Event("Added a book to a library"));
    }

    // REQUIRES: history.length() > 0
    // EFFECTS: Returns the book with the given name or null if none are found
    public Book findBook(String name) {
        for (Book book : this.bookCollection) {
            if (book.getName().equals(name)) {
                return book;
            }
        }

        return null;
    }

    // REQUIRES: A Book with the given name exists in bookCollection
    // MODIFIES: this
    // EFFECTS: Removes a book with the given name from bookCollection
    public void removeBookFromLibrary(String name) {
        int index = 0;

        for (Book book : bookCollection) {
            if (book.getName().equals(name)) {
                bookCollection.remove(index);
                break;
            }

            index++;
        }

        EventLog.getInstance().logEvent(new Event("Removed a book from a library"));
    }

    public String getName() {
        return this.libraryName;
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average page count of the library's books
    public double getAveragePageCount() {
        double avg = 0;

        for (Book book : bookCollection) {
            avg += book.getPageCount();
        }

        avg /= bookCollection.size();

        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average word count of the library's books
    public double getAverageWordCount() {
        double avg = 0.0;

        for (Book book : bookCollection) {
            avg += book.getWordCount();
        }

        avg /= bookCollection.size();
        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // REQUIRES: bookCollection.length() > 0
    // EFFECTS: Calculates and returns the average reading duration of the library's
    // books
    public double getAverageDuration() {
        double avg = 0;

        for (Book book : bookCollection) {
            avg += book.getDuration();
        }

        avg /= bookCollection.size();
        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // EFFECTS: Converts library information and adds book information into JSON
    // format and returns it
    public JSONObject convertToJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("libraryName", libraryName);
        jsonObject.put("bookCollection", convertBooksToJson());

        return jsonObject;
    }

    // EFFECTS: Converts all stored book data to JSON format and returns it
    private JSONArray convertBooksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book : bookCollection) {
            jsonArray.put(book.convertToJson());
        }

        return jsonArray;
    }
}
