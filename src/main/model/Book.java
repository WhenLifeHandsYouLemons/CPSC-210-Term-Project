package model;

import org.json.JSONObject;

// Stores information on the name, page count, word count, and reading duration of a book
public class Book {
    private String name;
    private int pageCount;
    private int wordCount;
    private int duration;

    // REQUIRES: name is not empty and pageCount > 0 and duration > 0
    // MODIFIES: this
    // EFFECTS: Instantiates a new Book with the given name, pageCount, and duration
    // (in minutes)
    public Book(String name, int pageCount, int duration) {
        this.name = name;
        this.pageCount = pageCount;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getDuration() {
        return duration;
    }

    // EFFECTS: Converts the data in the book object to JSON format
    public JSONObject convertToJson() {
        return null; // stub
    }
}
