package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;

import model.Book;
import model.Library;
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
    public void readFromFile(BookTrackerApp bookTrackerApp) throws FileNotFoundException {
        // stub

    // EFFECTS: reads source file as string and returns it
    // Taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        return contentBuilder.toString();
    }

    // EFFECTS: parses bookTrackerApp from JSON object and returns it
    private BookTrackerApp parseBookTrackerApp(JSONObject jsonObject) {
        BookTrackerApp bookTrackerApp = new BookTrackerApp();
        return bookTrackerApp;
    }

    public String getFilePath() {
        return ""; // stub
    }
}
