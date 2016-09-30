// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 8
 * Name:
 * Usercode:
 * ID:
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.*;

/**
 * The test class QueueTests.
 *
 * @author  Thomas Kuehne
 */
public class TestsHeapArrayQueueCore
{
    Queue<Pizza> emptyQueue = new HeapArrayQueue<Pizza>();
    Queue<Pizza> nonEmptyQueue;

    // standard pizza, ordered at t=0 minutes
    Pizza standardPizza = new Pizza("karori", 0, 0, false);

    // urgent pizza, ordered later, but with earlier deadline
    Pizza urgentPizza = new Pizza("wilton", 0, 10, true);

    public TestsHeapArrayQueueCore(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        nonEmptyQueue = new HeapArrayQueue<Pizza>();
        nonEmptyQueue.offer(standardPizza);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testEmptyQueueIsEmpty() {
        assertTrue("A new queue should be empty", emptyQueue.isEmpty());
    }

    @Test
    public void testEmptyQueueHasSizeZero() {
        assertEquals("A new queue should have size 0", 0, emptyQueue.size());
    }

    @Test
    public void testNonEmptyQueueIsNotEmpty() {
        assertFalse("A non-empty queue should not be empty", nonEmptyQueue.isEmpty());
    }

    @Test
    public void testNonEmptyQueueHasSize() {
        assertEquals("A queue with one element should have size 1", 1, nonEmptyQueue.size());
    }

    @Test
    public void testNonEmptyQueueReturnsElement() {
        assertEquals("A queue with one element should return the element", standardPizza, nonEmptyQueue.peek());
    }

    @Test
    public void testNonEmptyQueueRemovesElement() {
        assertEquals("A queue with one element should return the element", standardPizza, nonEmptyQueue.poll());
        assertEquals("A queue with one element should contain none after poll", 0, nonEmptyQueue.size());
    }

    @Test
    public void QueueRespectsPriority() {
        nonEmptyQueue.offer(urgentPizza);
        assertEquals("Queue should return element with highest priority", urgentPizza, nonEmptyQueue.poll());
    }
}
