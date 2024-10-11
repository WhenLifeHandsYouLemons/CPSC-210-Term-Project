package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Book;
import model.Library;

public class TestBookTrackerApp {
    private BookTrackerApp testBookTrackerApp;

    @BeforeEach
    void runBefore() {
        testBookTrackerApp = new BookTrackerApp();
    }

    @Test
    void testConstructor() {
        assertEquals(new ArrayList<Library>(), testBookTrackerApp.getLibraries());
    }

    @Test
    void testAddSingleLibrary() {
        testBookTrackerApp.addLibrary("Library 1");

        assertEquals("Library 1", testBookTrackerApp.getLibraries().get(0).getName());
    }

    @Test
    void testAddMultipleLibraries() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");
        testBookTrackerApp.addLibrary("Library 3");

        assertEquals("Library 1", testBookTrackerApp.getLibraries().get(0).getName());
        assertEquals("Library 2", testBookTrackerApp.getLibraries().get(1).getName());
        assertEquals("Library 3", testBookTrackerApp.getLibraries().get(2).getName());
    }

    @Test
    void testFindLibrary() {
        testBookTrackerApp.addLibrary("Library 1");

        assertEquals("Library 1", testBookTrackerApp.findLibrary("Library 1").getName());
        assertNull(testBookTrackerApp.findLibrary("Library 2"));

        testBookTrackerApp.addLibrary("Library 2");

        assertEquals("Library 1", testBookTrackerApp.findLibrary("Library 1").getName());
        assertEquals("Library 2", testBookTrackerApp.findLibrary("Library 2").getName());
    }

    @Test
    void testRemoveLibrary() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");
        testBookTrackerApp.addLibrary("Library 3");

        testBookTrackerApp.removeLibrary("Library 1");

        assertEquals("Library 2", testBookTrackerApp.getLibraries().get(0).getName());
        assertEquals("Library 3", testBookTrackerApp.getLibraries().get(1).getName());
        assertEquals(2, testBookTrackerApp.getLibraries().size());

        testBookTrackerApp.addLibrary("Library 1");

        testBookTrackerApp.removeLibrary("Library 3");

        assertEquals("Library 2", testBookTrackerApp.getLibraries().get(0).getName());
        assertEquals("Library 1", testBookTrackerApp.getLibraries().get(1).getName());
        assertEquals(2, testBookTrackerApp.getLibraries().size());
    }

    @Test
    void testAveragePageCount() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");

        Library library1 = testBookTrackerApp.findLibrary("Library 1");
        Book testBook1 = new Book("Book 1", 100, 60);
        library1.addBookToHistory(testBook1);

        Library library2 = testBookTrackerApp.findLibrary("Library 2");
        Book testBook2 = new Book("Book 2", 250, 100);
        Book testBook3 = new Book("Book 3", 230, 120);
        library2.addBookToHistory(testBook2);
        library2.addBookToHistory(testBook3);

        assertEquals(193.33, testBookTrackerApp.getAveragePageCount());
    }

    @Test
    void testAverageWordCount() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");

        Library library1 = testBookTrackerApp.findLibrary("Library 1");
        Book testBook1 = new Book("Book 1", 100, 60);
        library1.addBookToHistory(testBook1);

        Library library2 = testBookTrackerApp.findLibrary("Library 2");
        Book testBook2 = new Book("Book 2", 250, 100);
        Book testBook3 = new Book("Book 3", 230, 120);
        library2.addBookToHistory(testBook2);
        library2.addBookToHistory(testBook3);

        assertEquals(0.0, testBookTrackerApp.getAverageWordCount());
    }

    @Test
    void testAverageReadingDuration() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");

        Library library1 = testBookTrackerApp.findLibrary("Library 1");
        Book testBook1 = new Book("Book 1", 100, 60);
        library1.addBookToHistory(testBook1);

        Library library2 = testBookTrackerApp.findLibrary("Library 2");
        Book testBook2 = new Book("Book 2", 250, 100);
        Book testBook3 = new Book("Book 3", 230, 120);
        library2.addBookToHistory(testBook2);
        library2.addBookToHistory(testBook3);

        assertEquals(93.33, testBookTrackerApp.getAverageDuration());
    }
}
