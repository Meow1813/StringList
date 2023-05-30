package org.example;

import org.example.Exception.ElementNotExistException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.InvalidItemException;

import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {

    private String[] storage;
    private int size = 0;

    public StringListImpl() {
        storage = new String[10];
    }

    public StringListImpl(int size) {
        storage = new String[size];
    }

    @Override
    public String add(String item) {

        checkItem(item);
        checkSize();
        storage[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkSize();
        checkItem(item);
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
    public String set(int index, String item) {
        checkIndex(index);
        checkItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkItem(item);
        if (contains(item)) {
            return remove(indexOf(item));
        }
        throw new ElementNotExistException();
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String result = storage[index];
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        checkItem(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkItem(item);
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void checkItem(String item) {
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


}
