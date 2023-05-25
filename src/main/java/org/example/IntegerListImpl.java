package org.example;

import org.example.Exception.ElementNotExistException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.InvalidItemException;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;
    private int size = 0;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int size) {
        storage = new Integer[size];
    }

    @Override
    public Integer add(Integer item) {

        checkItem(item);
        checkSize();
        storage[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkSize();
        if (index == size) {
            add(item);
        }
        checkIndex(index);
        size++;
        for (int i = size - 1; i > index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndex(index);
        checkItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        if (contains(item)) {
            return remove(indexOf(item));
        }
        throw new ElementNotExistException();
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer result = storage[index];
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);
        Integer[] arr = toArray();
        sort(arr);
        return binarySearch(arr, item);
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void checkItem(Integer item) {
        if (Objects.isNull(item)) {
            throw new InvalidItemException();
        }
    }

    private void checkSize() {
        if (size == storage.length) {
            storage = Arrays.copyOf(storage, (int) (size * 1.5 + 1));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;

        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int minIndex = 0;
        int maxIndex = arr.length - 1;

        while (minIndex <= maxIndex) {
            int midIndex = (minIndex + maxIndex) / 2;
            if (arr[midIndex] == item) {
                return true;
            }
            if (arr[midIndex] > item) {
                maxIndex = midIndex - 1;
            } else {
                minIndex = midIndex + 1;
            }
        }
        return false;
    }
}
