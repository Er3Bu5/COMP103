// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 8
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;

/**
 * Implements a priority queue based on a heap that is
 * represented with an array.
 */
class HeapArrayQueue<E extends Comparable<? super E>> extends AbstractQueue<E> {

    @SuppressWarnings("unchecked")
    private E[] data = (E[]) (new Comparable[7]);
    private int count = 0;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the element with the top priority in the queue.
     * <p>
     * HINT: This is like 'poll()' without the removal of the element.
     *
     * @returns the next element if present, or 'null' if the queue is empty.
     */
    public E peek() {
        /*# YOUR CODE HERE */
        if (count == 0) {
            return null;
        }
        return data[1];
    }

    /**
     * Removes the element with the top priority from the queue and returns it.
     * <p>
     * HINT: The 'data' array should contain a heap so the element with the top priority
     * sits at index '0'. After its removal, you need to restore the heap property again,
     * using 'sinkDownFromIndex(...)'.
     *
     * @returns the next element in the queue, or 'null' if the queue is empty.
     */
    public E poll() {
        /*# YOUR CODE HERE */
        if (count == 0) {
            return null;
        }
        E temp = data[1];
        data[1] = data[count];
        count--;
        sinkDownFromIndex(1);
        return temp;
    }

    /**
     * Enqueues an element.
     * <p>
     * If the element to be added is 'null', it is not added.
     * <p>
     * HINT: Make use of 'ensureCapacity' to make sure that the array can
     * accommodate one more element.
     *
     * @param element - the element to be added to the queue
     * @returns true, if the element could be added
     */
    public boolean offer(E element) {
        /*# YOUR CODE HERE */
        if (element == null) {
            return false;
        }
        count++;
        ensureCapacity();
        data[count] = element;
        bubbleUpFromIndex(count);
        return true;
    }

    private void sinkDownFromIndex(int nodeIndex) {
        /*# YOUR CODE HERE */
        int largestChild = getChild(nodeIndex);
        if (largestChild == -1) {
            return;
        }
        if (data[nodeIndex].compareTo(data[largestChild]) > 0) {
            swap(largestChild, nodeIndex);
            sinkDownFromIndex(largestChild);
        }
    }

    private int getChild(int nodeIndex) { // helper class
        int leftIndex = nodeIndex * 2;
        int rightIndex = nodeIndex * 2 + 1;
        if (leftIndex >= data.length || data[leftIndex] == null) return -1;
        if (rightIndex >= data.length || data[rightIndex] == null) return leftIndex;
        if (data[leftIndex].compareTo(data[rightIndex]) > 0) return leftIndex;
        return rightIndex;
    }

    private void bubbleUpFromIndex(int nodeIndex) {
        /*# YOUR CODE HERE */
        int parentIndex = getParent(nodeIndex);
        if (getParent(nodeIndex) >= 1) {
            if (data[nodeIndex].compareTo(data[parentIndex]) < 0) {
                swap(nodeIndex, parentIndex);
                bubbleUpFromIndex(parentIndex);
            }
        }
    }

    private int getParent(int nodeIndex) {
        if (nodeIndex == 1) {
            return 1;
        } else {
            return (nodeIndex) / 2;
        }
    }

    /**
     * Swaps two elements in the supporting array.
     */
    private void swap(int from, int to) {
        E temp = data[from];
        data[from] = data[to];
        data[to] = temp;
    }

    /**
     * Increases the size of the supporting array, if necessary
     */
    private void ensureCapacity() {
        if (count == data.length) {
            @SuppressWarnings("unchecked")
            E[] newData = (E[]) new Comparable[data.length * 2];

            // copy data elements
            for (int loop = 0; loop < count; loop++) {
                newData[loop] = data[loop];
            }
            data = newData;
        }
    }

    // no iterator implementation required for this assignment
    public Iterator<E> iterator() {
        return null;
    }
}
