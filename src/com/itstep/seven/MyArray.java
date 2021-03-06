package com.itstep.seven;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * My Array that handle save Objects in fields.
 * Indexing from 0, like normal arrays.
 * @param <T> =  must be declared when call
 */
public class MyArray <T> {
    private int size = 0;
    private int capacity;
    private Object[] dataField;

    public MyArray () {
        this.capacity = 10;
        dataField = new Object[this.capacity];
    }

    public MyArray (int capacity) {
        if (capacity < 0) {
            System.out.println ("Zadal jsi kapacitu pod 0");
            capacity = 0;
        }
        this.capacity = capacity;
        dataField = new Object[capacity];
    }
    /**
     * Add object to end of my Array. Must be called with same data types as arrays.
     * @param item item what will add.
     * @return true if method is successfully of false.
     */
    public boolean add (T item) {
        if(ensureSecurity(item)){
            dataField[size] = item;
            size++;
            trimToSize();
            return true;
        }
        return false;
    }
    /**
     * Get value of item from array on specified index.
     * @param index = Integer value.
     * @return return value of same data types like field
     */
    public T get (int index) {
        if ((index >= capacity || index < 0)) {
            System.out.println ("Index out of bound Array!");
            return null;
        }
        return (T) dataField[index];
    }
    /**
     * insert value on specified index
     * @param index value of Integer where add item.
     * @param item item what will add.
     * @return true if method is successfully of false.
     */
    public boolean insert (int index, T item) {
       if ((index >= capacity || index < 0)) {
            System.out.println ("Index out of bound Array!");
            return false;
        }
       if(ensureSecurity (item)){
           fieldSlider(index, true);
           dataField[index] = item;
           size++;
           return true;
       }
        return false;
    }
    /**
     * add item on first place in array (index 0).
     * All object in array move to index + 1, than place value on place,
     * so no Item will be lost.
     * @param item item what will add.
     * @return true if method is successfully of false.
     */
    public boolean pushFront (T item) {
        if(ensureSecurity (item)) {
            fieldSlider(0, true);
            dataField[0] = item;
            size++;
            trimToSize ();
            return true;
        }
        return false;
    }
    /**
     * remove first value in array, on index 0,
     * all value move on index - 1.
     */
    public void popFront () {
        fieldSlider(0, false);
        size--;
        capacity--;
    }
    /**
     * remove last element in array.
     */
    public void popBack () {
        fieldSlider(--size, false);
        capacity--;
    }
    /**
     * Remove value of array in specified endex
     * @param index Integer value of index.
     * @return true if method is successfully of false.
     */
    public boolean removeAt (int index) {
        if ((index >= getSize () || index < 0)) {
            System.out.println ("Index out of bound Array!");
            return false;
        }
        fieldSlider(index, false);
        size--;
        capacity--;
        return true;
    }
    /**
     * Find and remove specified item from array.
     * Remove only first copy of item, that will be find.
     * @param item item what will be removed from array.
     * @return true if method is successfully of false
     */
    public boolean remove (T item) {
        if (ensureSecurity (item)) {
            int index = getIndex (item, true);
            if (index>=0) {
                size--;
                capacity--;
                fieldSlider (index,false);
                return true;
                }
        }
        return false;
    }

    /**
     * Search and remove all copy of item from field.
     * @param item item what will be removed from array.
     */
    public void removeAll (T item) {
        while (remove (item));
    }
    /**
     * reset size of Array to 0 and remove all contents (NULL).
     */
    public void clear() {
       size = 0;
       Arrays.fill (dataField, null);
       trimToSize();
    }
    /**
     * Chack if array is empty.
     * @return return true if is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * return Integer value index of specified item.
     * Search only for one entry.
     * @param item what search.
     * @return Integer of first entry of item in array.
     */
    public int indexOf(T item) {
        return getIndex (item, true);
    }
    /**
     * return Integer value index of specified item.
     * Search only for one entry.
     * @param item what search.
     * @return Integer of last entry of item in array.
     */
    public int lastIndexOf (T item) {
        return getIndex ( item, false );
    }
    /**
     * Adjust array to his capacity.
     * set Capacity to size.
     */
    public void trimToSize() {
        dataField = Arrays.copyOf(dataField,size);
        capacity = size;
    }
    /**
     * Reverse order all value in Array.
     * (first will be last etc.)
     */
    public void reverse() {
        trimToSize ();
        Object[] temporalField = new Object[dataField.length];
        for (int i = 0; i < dataField.length; i++) {
            temporalField[i] = dataField[(dataField.length -1) - i];
        }
        dataField = Arrays.copyOf (temporalField, temporalField.length);
    }
    /**
     * Make position of elements in field randomly
     */
    public void shuffle() {
        trimToSize ();
        Random random = new Random ();
        int randomIndex;
        int bound = dataField.length;
        Object[] temporalField = new Object[bound];
        for (int i = 0; i < bound; i++) {
            randomIndex = random.nextInt(dataField.length);
            temporalField[i] = dataField[randomIndex];
            removeAt (randomIndex);
        }
        dataField = Arrays.copyOf (temporalField, temporalField.length);
    }

    /**
     * Helper method in class, what find index of item.
     * @param item what item i want to search
     * @param naturalOrder if true, search from left to right
     *                     if false search from right to left
     * @return Integer (index) of item.
     */
    private int getIndex(T item, boolean naturalOrder) {
        int index = -1;
        if (naturalOrder) {
            for (int i = 0; i < dataField.length; i++) {
                String temporalString = dataField[i] != null ? dataField[i].toString (): "null";
                if(temporalString.equalsIgnoreCase (item.toString ())) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = dataField.length - 1; i > 0; i--) {
                String temporalString = dataField[i] != null ? dataField[i].toString ( ) : "null";
                if ( temporalString.equalsIgnoreCase ( item.toString ( ) ) ) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    /**
     * Helper Method that manipulate with array.
     * Moving value to left side or right side.
     * It helps other methode to manipulate with array,
     * like: @remove, @insert etc.
     * @param index beginning indexed of manipulating
     * @param right if true move all value on array to +1 (left side)
     *              if false, that will remove value of array in specified index,
     *              all others value are moved.
     */
    private void fieldSlider(int index, boolean right) {
        if (right) {
            Object[] temporalField = Arrays.copyOfRange ( dataField, index, dataField.length - 1);
            System.arraycopy(temporalField, 0, dataField, index + 1, temporalField.length);
        } else {
            index++;
            Object[] temporalFieldII = Arrays.copyOfRange ( dataField, index, dataField.length);
            Object[] temporalFieldI = Arrays.copyOfRange ( dataField, 0, index - 1);
            dataField = Stream.concat (Arrays.stream (temporalFieldI), Arrays.stream (temporalFieldII))
                    .toArray ();
        }
        setCapacity (dataField.length);
    }

    /**
     * Helper save method that check, if value is NULL or capacity of adding index is out of bound.
     * If is, than adding capacity to array.
     * @param item what will be checked
     * @return true if is OK, or false if there are some problems.
     */
    private boolean ensureSecurity(T item) {
        if (size >= (capacity-1)) {
            capacity += 5;
            dataField = Arrays.copyOf (dataField, capacity);
            return true;
        }
        if (item == null) {
            System.err.println ("Nepracuji s NULL");
            return false;
        }
        return true;
    }

    public int getSize() {
        return size;
    }

    /**
     * Setting capacity to another value.
     * Secure then new capacity is bigger then old one.
     * @param capacity Integer value of new capacity
     */
    public void setCapacity(int capacity) {
        if (capacity <= this.capacity) {
            System.out.println ("New Capacity is smaller then older. Cannot change capacity");
        } else {
            dataField = Arrays.copyOf (dataField, capacity);
            this.capacity = capacity;
        }
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (Object item: dataField) {
            if (item == null) {
                continue;
            }
            msg.append ("'").append (item).append ("'").append (" ");
        }
        return "[" + msg.toString().trim() + "]";
    }
}
