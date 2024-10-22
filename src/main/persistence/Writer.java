package persistence;

import org.json.JSONObject;
import java.io.*;

import ui.BookTrackerApp;

public class Writer {
    // REQUIRES: filePath is not empty
    // EFFECTS: Instantiates the Writer object and creates a new JSONObject for saving data
    public Writer(String filePath) {
        // stub
    }

    // REQUIRES: bookTrackerApp should be initialised and not null
    // EFFECTS: Runs functions to convert the current program state to JSON and save it to filePath
    public void writeToFile(BookTrackerApp bookTrackerApp) {
        // stub
    }

    // REQUIRES: JSONObject is not null
    // EFFECTS: Converts the given jsonObject to a string
    public String convertToString(JSONObject jsonObject) {
        return "";  // stub
    }

    // REQUIRES: filePath is not empty
    // EFFECTS: Saves the given stringToSave to the filePath
    public void saveToFile(String stringToSave) {
        // stub
    }

    public JSONObject getJsonObject() {
        return null;    // stub
    }
}
