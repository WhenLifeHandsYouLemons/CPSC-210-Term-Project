package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBook {
    private Book testBook1;
    private Book testBook2;

    @BeforeEach
    void runBefore() {
        testBook1 = new Book("Book 1", 100, 5000, 60);
        testBook2 = new Book("Book 2", 250, 25000, 100);
    }

    @Test
    void testConstructor() {
        assertEquals("Book 1", testBook1.getName());
        assertEquals(100, testBook1.getPageCount());
        assertEquals(5000, testBook1.getWordCount());
        assertEquals(60, testBook1.getDuration());

        assertEquals("Book 2", testBook2.getName());
        assertEquals(250, testBook2.getPageCount());
        assertEquals(25000, testBook2.getWordCount());
        assertEquals(100, testBook2.getDuration());
    }
}
