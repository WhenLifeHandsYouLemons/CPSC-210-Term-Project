package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertEquals("Library 1", testBookTrackerApp.getLibraries().get(0));
    }

    @Test
    void testAddMultipleLibraries() {
        testBookTrackerApp.addLibrary("Library 1");
        testBookTrackerApp.addLibrary("Library 2");
        testBookTrackerApp.addLibrary("Library 3");

        assertEquals("Library 1", testBookTrackerApp.getLibraries().get(0));
        assertEquals("Library 2", testBookTrackerApp.getLibraries().get(1));
        assertEquals("Library 3", testBookTrackerApp.getLibraries().get(2));
    }

    @Test
    void testFindLibrary() {
        testBookTrackerApp.addLibrary("Library 1");

        assertEquals("Library 1", testBookTrackerApp.findLibrary("Library 1").getLibraryName());
        assertNull(testBookTrackerApp.findLibrary("Library 2"));

        testBookTrackerApp.addLibrary("Library 2");

        assertEquals("Library 1", testBookTrackerApp.findLibrary("Library 1").getLibraryName());
        assertEquals("Library 2", testBookTrackerApp.findLibrary("Library 2").getLibraryName());
    }
}
