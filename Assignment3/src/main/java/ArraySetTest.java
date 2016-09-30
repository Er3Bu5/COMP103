// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 3
 * Name:
 * Usercode:
 * ID:
 */

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/** ArraySetTest
 *  A JUnit class for testing ArraySet with a set of tests.
 */

public class ArraySetTest {

    private Set<String> set;

    /** initialise set to be an empty set before each test is run */
    @Before
    public void initialiseEmptySet() {
        set = new ArraySet<String>();
    }

    /** method to initialise the set with the n values "v1", "v2", ... */
    public void fillSet(int n) {
        for (int i = 1; i <= n; i++) {
            set.add("v" + i);
        }
    }
    //-------------------------------------------------------------------

    @Test
    public void testIsEmptyOnEmptySet() {
        assertTrue("A new array set should be empty", set.isEmpty());
    }

    @Test
    public void testEmptySetHasSizeZero() {
        assertEquals("An empty set should have size zero", 0, set.size());
    }

    @Test
    public void testEmptySetDoesNotContainNull() {
        assertFalse("An empty set should not contain null", set.contains(null));
    }

    @Test
    public void testAddingToSet() {
        for (int i = 1; i <= 20; i++) {
            assertTrue("Set should successfully add item " + i, set.add("v" + i));
            assertFalse("Set should not be empty after add", set.isEmpty());
            assertEquals("Size should be " + i + " after " + i + " adds", i, set.size());
        }
    }

    // The expected parameter in the Test annotation indicates
    // that this test case expects an IllegalArgumentException
    // The Unit test will fails if the given exception is not thrown.
    // Since JUnit 4.
    @Test (expected = IllegalArgumentException.class)
    public void testAddingNull() {
        set.add(null);
    }

    @Test
    public void testAddingDuplicates() {
        fillSet(20);

        for (int i = 1; i <= 20; i++) {
            String value = "v" + i;
            assertFalse("Set should not add duplicate item " + value, set.add(value));
            assertFalse("Set should not be empty after add.", set.isEmpty());
            assertEquals("Size should still be 20 after adding duplicate.", 20, set.size());
        }
    }

    @Test
    public void testContains() {
        fillSet(20);
        for (int i = 1; i <= 20; i++) {
            String goodValue = "v"+i;
            String badValue = "u"+i;
            assertTrue("Set should contain item " + goodValue, set.contains(goodValue));
            assertFalse("Set should not contain item " + badValue, set.contains(badValue));
        }
    }

    @Test
    public void testContainsNull() {
        fillSet(15);
        assertFalse("Set should not contain null.", set.contains(null));
    }

    @Test
    public void testRemovingItems() {
        fillSet(15);

        assertTrue("v15 should be removed successfully.", set.remove("v15"));
        assertEquals("Set should be size 14 after remove.", 14, set.size());
        assertFalse("Set should no longer contain v15.", set.contains("v15"));

        assertTrue("v1 should be removed successfully.", set.remove("v1"));
        assertEquals("Set should be size 13 after remove.", 13, set.size());
        assertFalse("Set should no longer contain v1.", set.contains("v1"));

        assertTrue("v10 should be removed successfully.", set.remove("v10"));
        assertEquals("Set should be size 12 after remove.", 12, set.size());
        assertFalse("Set should no longer contain v10.", set.contains("v10"));

        assertFalse("v0 should not be removed successfully.", set.remove("v0"));
        assertEquals("Set should still be size 12 after remove.", 12, set.size());
    }

    @Test
    public void testRemovingAllItems() {
        fillSet(15);

        for (int i = 1; i <= 15; i++) {
            String goodValue = "v"+i;
            String badValue = "u"+i;
            assertTrue(goodValue+" should be removed successfully.", set.remove(goodValue));
            assertFalse("Set should no longer contain "+goodValue, set.contains(goodValue));
            assertFalse(badValue+" should not be removed successfully.", set.remove(badValue));
            assertEquals("Set should be one smaller after remove.", (15-i), set.size());
        }
        assertTrue("Set should be empty after removing all.", set.isEmpty());
        assertFalse("Set should not contain null after removing all.", set.contains(null));
    }
}

