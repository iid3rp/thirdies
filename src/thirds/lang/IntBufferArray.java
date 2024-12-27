package thirds.lang;

import java.io.Serializable;
import java.util.Arrays;

public class IntBufferArray implements Serializable {
    private int[] arr;

    public IntBufferArray() {
        arr = new int[0];
    }

    // Constructor to initialize with an existing array
    public IntBufferArray(int[] initialArray) {
        arr = Arrays.copyOf(initialArray, initialArray.length);
    }

    /**
     * Adds an element to the end of the array.
     *
     * @param element The integer element to add.
     */
    public void add(int element) {
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = element;
        arr = newArr;
    }

    /**
     * Adds an element at the specified index, shifting existing elements to the right.
     *
     * @param index   The index at which to insert the element.
     * @param element The integer element to add.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index > size()).
     */
    public void add(int index, int element) {
        if (index < 0 || index > arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
        }
        int[] newArr = new int[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        newArr[index] = element;
        System.arraycopy(arr, index, newArr, index + 1, arr.length - index);
        arr = newArr;
    }

    /**
     * Removes the element at the specified index, shifting subsequent elements to the left.
     *
     * @param index The index of the element to remove.
     * @return The removed element.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public int remove(int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
        }
        int removedElement = arr[index];
        int[] newArr = new int[arr.length - 1];
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        arr = newArr;
        return removedElement;
    }

    /**
     * Removes the first occurrence of the specified element from the array, if it is present.
     *
     * @param element The element to remove.
     * @return true if the element was found and removed, false otherwise.
     */
    public boolean removeElement(int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces the element at the specified index with the new element.
     *
     * @param index   The index of the element to replace.
     * @param element The new element to set.
     * @return The old element that was replaced.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public int set(int index, int element) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
        }
        int oldElement = arr[index];
        arr[index] = element;
        return oldElement;
    }

    /**
     * Replaces the entire internal array with a new array.
     *
     * @param newArray The new array to use.
     */
    public void replaceArray(int[] newArray) {
        arr = Arrays.copyOf(newArray, newArray.length);
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index The index of the element to get.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public int get(int index) {
        if (index < 0 || index >= arr.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + arr.length);
        }
        return arr[index];
    }

    /**
     * Returns the size (number of elements) of the array.
     *
     * @return The size of the array.
     */
    public int size() {
        return arr.length;
    }

    /**
     * Returns true if the array contains no elements.
     *
     * @return true if the array is empty, false otherwise.
     */
    public boolean isEmpty() {
        return arr.length == 0;
    }

    /**
     * Returns true if the array contains the specified element.
     *
     * @param element The element to search for.
     * @return true if the array contains the element, false otherwise.
     */
    public boolean contains(int element) {
        for (int value : arr) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the array, or -1 if the element is not found.
     *
     * @param element The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    public int indexOf(int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in the array, or -1 if the element is not found.
     *
     * @param element The element to search for.
     * @return The index of the element, or -1 if not found.
     */
    public int lastIndexOf(int element) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clears the array, removing all elements.
     */
    public void clear() {
        arr = new int[0];
    }

    /**
     * Returns a copy of the internal array.
     *
     * @return A copy of the internal array.
     */
    public int[] toArray() {
        return Arrays.copyOf(arr, arr.length);
    }

    /**
     * Returns a string representation of the array.
     *
     * @return A string representation of the array.
     */
    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}