package persistence;

import ui.BookTrackerApp;

public class Reader {
    // REQUIRES: filePath is not empty
    // EFFECTS: Instantiates the Reader object and sets the read source path to the
    // given string
    public Reader(String filePath) {
        // stub
    }

    // REQUIRES: filePath should not be empty
    // MODIFIES: bookTrackerApp
    // EFFECTS: Runs functions to read the file at filePath and convert the JSON to
    // a program state
    public void readFromFile(BookTrackerApp bookTrackerApp) {
        // stub
    }

    public String getFilePath() {
        return ""; // stub
    }
}
