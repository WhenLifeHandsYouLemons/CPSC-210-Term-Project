package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLibrary {
    private Library testLibrary;
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;

    @BeforeEach
    void runBefore() {
        testLibrary = new Library("Library 1");
        testBook1 = new Book("Book 1", 100, 60);
        testBook2 = new Book("Book 2", 250, 100);
        testBook3 = new Book("Book 3", 230, 120);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testLibrary.getBookCollection().size());
        assertEquals("Library 1", testLibrary.getLibraryName());
    }

    @Test
    void testAddSingleBookToCollection() {
        testLibrary.addBookToHistory(testBook1);
        assertEquals(1, testLibrary.getBookCollection().size());
    }

    @Test
    void testAddMultipleBooksToCollection() {
        testLibrary.addBookToHistory(testBook1);
        testLibrary.addBookToHistory(testBook2);
        assertEquals(2, testLibrary.getBookCollection().size());

        testLibrary.addBookToHistory(testBook3);
        assertEquals(3, testLibrary.getBookCollection().size());

        testLibrary.addBookToHistory(testBook1);
        assertEquals(4, testLibrary.getBookCollection().size());

        assertEquals(testBook1, testLibrary.getBookCollection().get(0));
        assertEquals(testBook2, testLibrary.getBookCollection().get(1));
        assertEquals(testBook3, testLibrary.getBookCollection().get(2));
        assertEquals(testBook1, testLibrary.getBookCollection().get(3));
    }

    @Test
    void testFindBook() {
        testLibrary.addBookToHistory(testBook1);
        testLibrary.addBookToHistory(testBook2);
        testLibrary.addBookToHistory(testBook3);

        assertEquals(testBook1, testLibrary.findBook("Book 1"));
        assertEquals(testBook2, testLibrary.findBook("Book 2"));
        assertEquals(testBook3, testLibrary.findBook("Book 3"));

        assertNull(testLibrary.findBook("Book 4"));
    }
}
