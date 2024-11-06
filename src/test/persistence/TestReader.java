// Taken from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.LibraryApp;
import model.Library;

public class TestReader {
    @Test
    void testConstructor() {
        Reader reader = new Reader("./data/bookTrackerAppData.json");

        assertEquals("./data/bookTrackerAppData.json", reader.getFilePath());
    }

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            reader.readFromFile();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        Reader reader = new Reader("./data/testReaderEmptyBookTrackerApp.json");
        try {
            LibraryApp bta = reader.readFromFile();
            assertEquals(0, bta.getLibraries().size());
            assertEquals(new ArrayList<Library>(), bta.getLibraries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        Reader reader = new Reader("./data/testReaderGeneralWorkRoom.json");
        try {
            LibraryApp bta = reader.readFromFile();

            assertEquals(2, bta.getLibraries().size());
            assertEquals("LibA", bta.getLibraries().get(0).getName());
            assertEquals("LibB", bta.getLibraries().get(1).getName());
            Library libA = bta.getLibraries().get(0);
            Library libB = bta.getLibraries().get(1);
            assertEquals(1, libA.getBookCollection().size());
            assertEquals(2, libB.getBookCollection().size());
            assertEquals(1, libA.getBookCollection().size());
            assertEquals("BookA", libA.getBookCollection().get(0).getName());
            assertEquals("BookB", libB.getBookCollection().get(0).getName());
            assertEquals("BookC", libB.getBookCollection().get(1).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
