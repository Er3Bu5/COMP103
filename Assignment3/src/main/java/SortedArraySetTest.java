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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/** SortedArraySetTest
 *  A JUnit class for testing SortedArraySet with a set of tests.
 */

public class SortedArraySetTest {

    private List<String> items = Arrays.asList("v13","v18","v09","v12","v16","v06","v10","v02","v04","v08","v17","v07","v01","v03","v14","v20","v15","v11","v05","v19");
    private Set<String> set;

    /** initialise set to be an empty set before each test is run */
    @Before
    public void initialiseEmptySet() {
        set = new SortedArraySet<String>();
    }

    /** method to initialise the set */
    public void fillSet() {
        List<String> its = Arrays.asList("v14","v07","v05","v10","v20","v16","v01","v03","v13","v18","v06","v17","v04","v19","v12","v09","v08","v11","v02","v15");

        for (String it : its) {           
            set.add(it);
        }
    }

    //--------------------------------------------------------------------------------

    @Test
    public void testIsEmptyOnEmptySet() {
        assertTrue("A new set should be empty", set.isEmpty());
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
        int size = 0;
        for (String item : items) {
            size++;
            assertTrue("Set should successfully add item " + item, set.add(item));
            assertFalse("Set should not be empty after add", set.isEmpty());
            assertEquals("Size should be " + size + " after " + size + " adds", size, set.size());
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
        fillSet();

        for (String item : items ){
            assertFalse("Set should not add duplicate item " + item, set.add(item));
            assertFalse("Set should not be empty after add.", set.isEmpty());
            assertEquals("Size should still be 20 after adding duplicate.", 20, set.size());
        }
    }

    @Test
    public void testContains() {
        fillSet();
        for (String goodValue : items) {
            String badValue = "u"+goodValue;
            assertTrue("Set should contain item " + goodValue, set.contains(goodValue));
            assertFalse("Set should not contain item " + badValue, set.contains(badValue));
        }
    }

    @Test
    public void testContainsNull() {
        fillSet();
        assertFalse("Set should not contain null.", set.contains(null));
    }

    @Test
    public void testRemovingItems() {
        fillSet();

        assertTrue("v20 should be removed successfully.", set.remove("v20"));
        assertEquals("Set should be size 19 after remove.", 19, set.size());
        assertFalse("Set should no longer contain v20.", set.contains("v20"));

        assertTrue("v01 should be removed successfully.", set.remove("v01"));
        assertEquals("Set should be size 18 after remove.", 18, set.size());
        assertFalse("Set should no longer contain v01.", set.contains("v01"));

        assertTrue("v10 should be removed successfully.", set.remove("v10"));
        assertEquals("Set should be size 17 after remove.", 17, set.size());
        assertFalse("Set should no longer contain v10.", set.contains("v10"));

        assertFalse("v0 should not be removed successfully.", set.remove("v0"));
        assertEquals("Set should still be size 17 after remove.", 17, set.size());
    }

    @Test
    public void testRemovingAllItems() {
        fillSet();

        int size = 20;      
        for (String goodValue : items){
            String badValue = "u"+goodValue;
            assertTrue(goodValue+" should be removed successfully.", set.remove(goodValue));
            assertFalse("Set should no longer contain "+goodValue, set.contains(goodValue));
            assertFalse(badValue+" should not be removed successfully.", set.remove(badValue));
            assertEquals("Set should be one smaller after remove.", (--size), set.size());
        }
        assertTrue("Set should be empty after removing all.", set.isEmpty());
        assertFalse("Set should not contain null after removing all.", set.contains(null));
    }
}

