package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.BookTrackerApp;

public class TestWriter {
    private BookTrackerApp app;
    private Writer writer;
    private Reader reader;

    @BeforeEach
    void runBefore() {
        app = new BookTrackerApp();
        writer = new Writer("./data/bookTrackerAppData.json");
    }

    @Test
    void testConstructor() {
        assertFalse(writer.getJsonObject() == null);
    }
}
