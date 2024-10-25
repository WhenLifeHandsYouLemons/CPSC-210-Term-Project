package ui;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Book;
import model.Library;

// Stores a list of libraries, manages the libraries, and shows statistics on all
//  books stored in every library.
public class BookTrackerApp {
    private List<Library> libraries;

    // MODIFIES: this
    // EFFECTS: Initialises an empty list of libraries
    public BookTrackerApp() {
        libraries = new ArrayList<Library>();
    }

    // REQUIRES: name is not empty
    // MODIFIES: this
    // EFFECTS: Creates a new library with the given name and adds it to the list of
    // libraries
    public void addLibrary(String name) {
        libraries.add(new Library(name));
    }

    // REQUIRES: A Library with the given name exists in libraries
    // MODIFIES: this
    // EFFECTS: Removes a library with the given name from libraries
    public void removeLibrary(String name) {
        int index = 0;

        for (Library library : libraries) {
            if (library.getName().equals(name)) {
                libraries.remove(index);
                break;
            }

            index++;
        }
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    // REQUIRES: libraries.length() > 0
    // EFFECTS: Returns the Library with the given name or null if none is found
    public Library findLibrary(String name) {
        for (Library library : libraries) {
            if (library.getName().equals(name)) {
                return library;
            }
        }

        return null;
    }

    // REQUIRES: libraries.length() > 0
    // EFFECTS: Calculates and returns the average page count of all books tracked
    public double getAveragePageCount() {
        double avg = 0;
        int count = 0;

        for (Library library : libraries) {
            for (Book book : library.getBookCollection()) {
                avg += book.getPageCount();
            }

            count += library.getBookCollection().size();
        }

        avg /= count;

        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // REQUIRES: libraries.length() > 0
    // EFFECTS: Calculates and returns the average word count of the books tracked
    public double getAverageWordCount() {
        double avg = 0;
        int count = 0;

        for (Library library : libraries) {
            for (Book book : library.getBookCollection()) {
                avg += book.getWordCount();
            }

            count += library.getBookCollection().size();
        }

        avg /= count;

        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // REQUIRES: libraries.length() > 0
    // EFFECTS: Calculates and returns the average reading duration of the books
    // tracked
    public double getAverageDuration() {
        double avg = 0;
        int count = 0;

        for (Library library : libraries) {
            for (Book book : library.getBookCollection()) {
                avg += book.getDuration();
            }

            count += library.getBookCollection().size();
        }

        avg /= count;

        avg = Math.round(avg * 100.0) / 100.0;

        return avg;
    }

    // EFFECTS: Adds each library's information into the JSON object and returns it
    public JSONObject convertToJson() {
        return null; // stub
    }
}
