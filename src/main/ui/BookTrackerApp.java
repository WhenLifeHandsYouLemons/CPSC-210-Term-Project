package ui;

import java.util.List;
import java.util.ArrayList;

import model.Library;

// 
public class BookTrackerApp {
    private List<Library> libraries;

    // MODIFIES: this
    // EFFECTS: Initialises an empty list of libraries
    public BookTrackerApp() {
        libraries = new ArrayList<Library>();
    }

    // REQUIRES: name is not empty
    // MODIFIES: this
    // EFFECTS: Creates a new library with the given name and adds it to the list of libraries
    public void addLibrary(String name) {
        libraries.add(new Library(name));
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    // REQUIRES: libraries.length() > 0
    // EFFECTS: Returns the Library with the given name or null if none is found
    public Library findLibrary(String name) {
        for (Library library : libraries) {
            if (library.getLibraryName().equals(name)) {
                return library;
            }
        }

        return null;
    }
}
