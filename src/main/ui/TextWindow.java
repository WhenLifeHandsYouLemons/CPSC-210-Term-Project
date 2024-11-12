package ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Book;
import model.Library;

// Creates a basic window to display text
public class TextWindow extends JFrame {
    // EFFECTS: Creates a text window that displays the reading statistics
    public TextWindow(String title, double pageCount, double wordCount, double duration) {
        super(title);

        setSize(400, 200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea(" " + title + ": \n\n"
                + " " + "Average book length (in pages): " + pageCount + " \n"
                + " " + "Average book length (in words): " + wordCount + " \n"
                + " " + "Average reading duration (in minutes): " + duration + " ");

        textArea.setEditable(false);
        textArea.setHighlighter(null);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));

        this.add(textArea);

        setVisible(true);
    }

    // EFFECTS: Creates a text window that displays all the books tracked in the app
    public TextWindow(List<Library> libraries) {
        super("All books tracked");

        setSize(500, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String fullText = "";
        for (String t : getAllBooks(libraries)) {
            fullText += t;
        }

        JTextArea textArea = new JTextArea(fullText);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setEditable(false);
        textArea.setHighlighter(null);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));

        this.add(scrollPane);

        setVisible(true);
    }

    // EFFECTS: Creates a text window that displays all the books tracked in the app
    public TextWindow(Library library) {
        super("Books tracked in " + library.getName());

        setSize(500, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String fullText = "";
        for (String t : getAllBooks(library)) {
            fullText += t;
        }

        JTextArea textArea = new JTextArea(fullText);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setEditable(false);
        textArea.setHighlighter(null);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));

        this.add(scrollPane);

        setVisible(true);
    }

    // EFFECTS: Goes through the list of libraries and creates a list of strings to
    // be joined
    private List<String> getAllBooks(List<Library> libraries) {
        List<String> text = new ArrayList<String>();

        text.add(" All books:\n");
        int index = 1;

        for (Library library : libraries) {
            for (Book book : library.getBookCollection()) {
                text.add("\n");
                text.add(" " + index + "");
                text.add(". ");
                text.add(book.getName());
                text.add("\n");
                text.add("     Page count: ");
                text.add(book.getPageCount() + "");
                text.add("\n");
                text.add("     Word count: ");
                text.add(book.getWordCount() + "");
                text.add("\n");
                text.add("     Reading duration (minutes): ");
                text.add(book.getDuration() + "");

                index++;
            }
        }

        text.add(" ");

        return text;
    }

    // EFFECTS: Goes through the library and creates a list of strings to be joined
    private List<String> getAllBooks(Library library) {
        List<String> text = new ArrayList<String>();

        text.add(" All books in " + library.getName() + ":\n");
        int index = 1;

        for (Book book : library.getBookCollection()) {
            text.add("\n");
            text.add(" " + index + "");
            text.add(". ");
            text.add(book.getName());
            text.add("\n");
            text.add("     Page count: ");
            text.add(book.getPageCount() + "");
            text.add("\n");
            text.add("     Word count: ");
            text.add(book.getWordCount() + "");
            text.add("\n");
            text.add("     Reading duration (minutes): ");
            text.add(book.getDuration() + "");

            index++;
        }

        text.add(" ");

        return text;
    }
}
