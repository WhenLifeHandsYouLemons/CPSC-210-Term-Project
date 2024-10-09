package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBook {
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;

    @BeforeEach
    void runBefore() {
        testBook1 = new Book("Book 1", 100, 60);
        testBook2 = new Book("Book 2", 250, 100);
        testBook3 = new Book("Book 3", 230, 120);
    }

    @Test
    void testConstructor() {
        assertEquals("Book 1", testBook1.getName());
        assertEquals(100, testBook1.getPageCount());
        assertEquals(0, testBook1.getWordCount());
        assertEquals(60, testBook1.getDuration());

        assertEquals("Book 2", testBook1.getName());
        assertEquals(250, testBook1.getPageCount());
        assertEquals(0, testBook1.getWordCount());
        assertEquals(100, testBook1.getDuration());
    }
}
