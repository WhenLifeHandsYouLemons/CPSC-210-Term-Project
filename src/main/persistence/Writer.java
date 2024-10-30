package persistence;

import org.json.JSONObject;

import model.LibraryApp;

import java.io.*;

public class Writer {
    private String filePath;
    private JSONObject jsonObject;
    private PrintWriter printWriter; // Taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // REQUIRES: filePath is not empty
    // EFFECTS: Instantiates the Writer object, creates a new JSONObject for saving
    // data, and saves the filePath
    public Writer(String filePath) {
        jsonObject = new JSONObject();
        this.filePath = filePath;
    }

    // REQUIRES: bookTrackerApp should be initialised and not null
    // MODIFIES: jsonObject
    // EFFECTS: Runs functions to convert the current program state to JSON and save
    // it to filePath
    public void writeToFile(LibraryApp bookTrackerApp) throws FileNotFoundException {
        this.jsonObject = bookTrackerApp.convertToJson();
        System.out.println(jsonObject.toString(4));
        String jsonString = convertToString();
        saveToFile(jsonString);
    }

    // REQUIRES: JSONObject is not null
    // EFFECTS: Converts the given jsonObject to a string
    private String convertToString() {
        return this.jsonObject.toString(4);
    }

    // REQUIRES: filePath is not empty
    // EFFECTS: Saves the given stringToSave to the filePath
    public void saveToFile(String stringToSave) throws FileNotFoundException {
        printWriter = new PrintWriter(new File(filePath));
        printWriter.print(stringToSave);
        printWriter.close();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getFilePath() {
        return filePath;
    }
}
