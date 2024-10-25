// Taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import model.Book;
import model.Library;
import ui.BookTrackerApp;

public class TestWriter {
    @Test
    void testConstructor() {
        Writer writer = new Writer("./data/bookTrackerAppData.json");
        assertFalse(writer.getJsonObject() == null);
        assertEquals("./data/bookTrackerAppData.json", writer.getFilePath());
    }

    @Test
    void testSaveToFileSuccessful() {
        Writer writer = new Writer("./data/testEmptyBookTrackerAppData.json");

        try {
            writer.saveToFile("Test string");
        } catch (FileNotFoundException e) {
            fail("Exception shouldn't have been thrown.");
        }
    }

    @Test
    void testSaveToFileUnsuccessful() {
        try {
            BookTrackerApp bta = new BookTrackerApp();
            Writer writer = new Writer("./data/my\0illegal:filename.json");
            writer.writeToFile(bta);
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookTrackerApp() {
        try {
            BookTrackerApp bta = new BookTrackerApp();
            Writer writer = new Writer("./data/testWriterEmptyBookTrackerApp.json");
            writer.writeToFile(bta);

            Reader reader = new Reader("./data/testWriterEmptyBookTrackerApp.json");
            bta = reader.readFromFile();
            assertEquals(new ArrayList<Library>(), bta.getLibraries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookTrackerApp() {
        try {
            BookTrackerApp bta = new BookTrackerApp();

            bta.addLibrary("LibA");
            bta.addLibrary("LibB");
            Library libA = bta.getLibraries().get(0);
            Library libB = bta.getLibraries().get(1);
            Book bookA = new Book("BookA", 12, 34);
            Book bookB = new Book("BookB", 56, 78);
            Book bookC = new Book("BookC", 910, 1112);
            libA.addBookToHistory(bookA);
            libB.addBookToHistory(bookB);
            libB.addBookToHistory(bookC);

            Writer writer = new Writer("./data/testWriterGeneralBookTrackerApp.json");
            writer.writeToFile(bta);

            Reader reader = new Reader("./data/testWriterGeneralBookTrackerApp.json");
            bta = reader.readFromFile();

            assertEquals(2, bta.getLibraries().size());
            assertEquals("LibA", bta.getLibraries().get(0).getName());
            assertEquals("LibB", bta.getLibraries().get(1).getName());
            libA = bta.getLibraries().get(0);
            libB = bta.getLibraries().get(1);
            assertEquals(1, libA.getBookCollection().size());
            assertEquals(2, libB.getBookCollection().size());
            assertEquals(1, libA.getBookCollection().size());
            assertEquals("BookA", libA.getBookCollection().get(0).getName());
            assertEquals("BookB", libB.getBookCollection().get(0).getName());
            assertEquals("BookC", libB.getBookCollection().get(1).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
