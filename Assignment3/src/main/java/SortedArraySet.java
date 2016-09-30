// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 3
 * Name:
 * Usercode:
 * ID:
 */

import java.util.*;

/**
 * SortedArraySet - a Set collection;
 *
 *  The implementation uses an array to store the items
 *  and a count variable to store the number of items in the array.
 * 
 *  The items in the set should be stored in positions
 *    0, 1,... (count-1) of the array.
 * 
 *  The length of the array when the set is first created should be 10. 
 * 
 *  Items in the array are kept in order, based on the "compareTo()" method.
 *  
 *  Binary search is used for searching for items.  
 *  
 *  Note that a set does not allow null items or duplicates.
 *  Attempting to add null should throw an IllegalArgumentException exception
 *  Adding an item which is already present should simply return false, without
 *    changing the set.
 *  It should always compare items using equals()  (not using ==)
 *  When the array is full, it will create a new array of double the current
 *    size, and copy all the items over to the new array
 */

// We need "extends Comparable" so that we can use the "compareTo()" method
public class SortedArraySet <E extends Comparable> extends AbstractSet <E> {
    private int currentLength=10;
    private int count=0;
    private E[] data;
    // Data fields
    /*# Copy your declrations from ArraySet here */
    /*# YOUR CODE HERE */

    // --- Constructors --------------------------------------

    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    public SortedArraySet() {
        data = (E[]) new String[currentLength];
    }

    // --- Methods --------------------------------------
    /** 
     * @return the number of items in the set  
     */
    public int size () {
        return count;
    }

    /** 
     *  Adds the specified item to this set 
     *  (if it is not already in the set).
     *  Will not add a null value (throws an IllegalArgumentException in this case).
     *  
     *  @param item the item to be added to the set
     *  @return true if the collection changes, and false if it did not change.
     */
    public boolean add(E item) {
        if(item == null)
            throw new IllegalArgumentException();
        int aIndex = findIndexOf(item);
        if (data[aIndex] == null || !data[aIndex].equals(item)){
            ensureCapacity();
            for (int i = count; i > aIndex; i--){
                data[i] = data[i-1];
            }
            data[aIndex]=item;
            count++;
            return true;
        }
        return false;
    }

    /** 
    * @return true if this set contains the specified item.
    *        
    */
    @SuppressWarnings("unchecked")  // stops Java complaining about the call to compare 
    public boolean contains(Object item) {
        E itm=(E)item;
        if (itm != null){
        int aIndex = findIndexOf(itm);
         if (aIndex >= count) return false; 
            if (itm.equals(data[aIndex])){
                return true;
            }  
        }
        return false;
    }

    /** 
    *  Removes an item matching a given item.
    *  @return true if the item was present and then removed.
    *  Makes no changes to the set and returns false if the item is not present.
    */
    @SuppressWarnings("unchecked")  // stops Java complaining about the call to compare 
    public boolean remove (Object item) {
        E itm = (E) item;
        int aIndex = findIndexOf(itm);
        //If findIndexOf is giving an insertion point, its possible for it to be at the end of the array, which breaks for v20
        if (aIndex >= count) return false; 
        if (data[aIndex] != null && data[aIndex].equals(itm)){
            count--;
            for (int i = aIndex; i <count; i++){
                data[i] = data[i+1];
            }
            return true;
        }
        return false;
    }

    /** 
    * 
    * Ensures data array has sufficient capacity (length)
    * to accomodate a new item 
    */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    private void ensureCapacity () {
        if (size()+1 >= data.length)
            data = Arrays.copyOf(data,data.length*2);
    }

    // It is much more convenient to define the following method 
    // and use it in the methods above.

    /** 
    *  Finds the index of where an item is in the dataarray,
    *  (or where it ought to be, if it's not there).
    *  Assumes that the item is not null.
    *  
    *  Uses binary search and requires that the items are kept in order.
    *  Uses the "compareTo()" method to compare two items with each other, e.g., as in
    *  "item1.compareTo(item2)", resulting in 
    *  0, if the items are equal,
    *  a value lower than 0, if item1 is smaller than item2,
    *  a value greater than 0, if item1 is greater than item2.
    *         
    *  @return the index of the item, or 
    *               the index where it should be inserted, if it is not present       
    */
    @SuppressWarnings("unchecked")  // stops Java complaining about the call to compare 
    private int findIndexOf(E item) {
        int low = 0; //min index
        int high = count-1; //max index
        while(low<=high){
            int mid = (low + high)/2;//guess
            int comparison = item.compareTo(data[mid]);
            if (comparison == 0)
                return mid;
            if (comparison > 0)
                low = mid+1;
            else
                high = mid-1;
        }
        return low;
    }

    // --- Iterator and Comparator --------------------------------------
    /** ---------- The code below is already written for you ---------- **/

    /** 
     * @return an iterator over the items in this set. 
     * 
     */
    public Iterator <E> iterator() {
        return new SortedArraySetIterator(this);
    }

    private class SortedArraySetIterator implements Iterator <E> {
        // needs fields, constructor, hasNext(), next(), and remove()

        // fields
        private SortedArraySet<E> set;
        private int nextIndex = 0;
        private boolean canRemove = false;

        // constructor
        private SortedArraySetIterator(SortedArraySet<E> s) {
            set = s;
        }

        /**
         * @return true if iterator has at least one more item
         */
        public boolean hasNext() {
            return (nextIndex < set.count);
        }

        /** 
         * Returns the next element or throws a 
         * NoSuchElementException exception if none exists. 
         * 
         * @return next item in the set
         */
        public E next() {
            if (nextIndex >= set.count)
                throw new NoSuchElementException();

            canRemove = true;

            return set.data[nextIndex++];
        }

        /** 
         *  Removes the last item returned by the iterator from the set.
         *  Can only be called once per call to next.
         */
        public void remove() {
            if (! canRemove)
                throw new IllegalStateException();

            set.remove(set.data[nextIndex-1]);
            canRemove = false;
        }
    }
}

