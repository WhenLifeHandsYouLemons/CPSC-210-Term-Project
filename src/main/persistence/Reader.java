package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;

import model.Book;
import model.LibraryApp;
import model.Library;

public class Reader {
    private String filePath;

    // REQUIRES: filePath is not empty
    // EFFECTS: Instantiates the Reader object and sets the read source path to the
    // given string
    public Reader(String filePath) {
        this.filePath = filePath;
    }

    // REQUIRES: filePath should not be empty
    // EFFECTS: Runs functions to read the file at filePath and convert the JSON to
    // a program state
    public LibraryApp readFromFile() throws IOException {
        String jsonData = readFile(filePath);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookTrackerApp(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bookTrackerApp from JSON object and returns it
    private LibraryApp parseBookTrackerApp(JSONObject jsonObject) {
        LibraryApp bookTrackerApp = new LibraryApp();

        for (Object libJson : jsonObject.getJSONArray("libraries")) {
            JSONObject lib = (JSONObject) libJson;

            String name = lib.getString("libraryName");

            bookTrackerApp.addLibrary(name);

            for (Object bookJson : lib.getJSONArray("bookCollection")) {
                JSONObject book = (JSONObject) bookJson;

                String bookName = book.getString("name");
                int pageCount = book.getInt("pageCount");
                int wordCount = book.getInt("wordCount");
                int duration = book.getInt("duration");

                Library library = bookTrackerApp.getLibraries().get(bookTrackerApp.getLibraries().size() - 1);

                library.addBookToHistory(new Book(bookName, pageCount, duration));
            }
        }

        return bookTrackerApp;
    }

    public String getFilePath() {
        return filePath;
    }
}
