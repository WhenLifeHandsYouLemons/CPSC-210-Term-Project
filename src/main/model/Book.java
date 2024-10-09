package model;

public class Book {
    private String name;
    private int pageCount;
    private int wordCount;
    private int duration;

    // MODIFIES: this
    // EFFECTS: Instantiates a new Book with the given name, pageCount, and duration (in minutes)
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
}
