package org.example;


import org.example.Exception.ElementNotExistException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.InvalidItemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    StringListImpl list = new StringListImpl(5);

    @BeforeEach
    public void fillList() {
        list.add("test");
        list.add("string");
        list.add("list");
        list.add("word");
        list.add("string");
    }

    @AfterEach
    public void clearList() {
        list.clear();
    }

    @Test
    void add() {
        String string = "test string";
        list.add(string);
        assertEquals(string, list.get(5));
    }

    @Test
    void addNull() {
        assertThrows(InvalidItemException.class, () -> list.add(null));
    }

    @Test
    void indexAdd() {
        String string = "test string";
        list.add(1, string);
        assertEquals(string, list.get(1));
        assertEquals("string", list.get(2));

    }

    @Test
    void InvalidIndexAdd() {
        assertThrows(InvalidIndexException.class, () -> list.add(8, "string"));
    }

    @Test
    void set() {
        list.set(1, "more");
        assertEquals("more", list.get(1));
    }

    @Test
    void InvalidIndexSet() {
        assertThrows(InvalidIndexException.class, () -> list.set(8, "string"));
    }

    @Test
    void removeIndex() {

        list.remove(0);
        assertEquals("string", list.get(0));
    }

    @Test
    void InvalidIndexRemove() {
        assertThrows(InvalidIndexException.class, () -> list.remove(8));
    }

    @Test
    void removeItem() {
        list.remove("test");
        assertEquals("string", list.get(0));
    }

    @Test
    void removeNullItem() {
        assertThrows(InvalidItemException.class, () -> list.remove(null));
    }

    @Test
    void removeNotExistItem() {
        assertThrows(ElementNotExistException.class, () -> list.remove("null"));
    }

    @Test
    void contains() {
        assertTrue(list.contains("string"));
        assertFalse(list.contains("contains"));
    }

    @Test
    void indexOf() {
        assertEquals(1, list.indexOf("string"));
    }

    @Test
    void indexOfNotExist() {
        assertEquals(-1, list.indexOf("string1"));
    }

    @Test
    void lastIndexOf() {
        assertEquals(4, list.lastIndexOf("string"));
    }

    @Test
    void lastIndexOfNotExist() {
        assertEquals(-1, list.lastIndexOf("string1"));
    }

    @Test
    void get() {
        assertEquals("string", list.get(1));
    }

    @Test
    void invalidIndexGet() {
        assertThrows(InvalidIndexException.class, () -> list.get(8));
    }

    @Test
    void equals() {
        StringList newList = new StringListImpl();
        newList.add("test");
        newList.add("string");
        newList.add("list");
        newList.add("word");
        newList.add("string");
        assertTrue(list.equals(newList));
    }

    @Test
    void size() {
        assertEquals(5, list.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
    }

    @Test
    void clear() {
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void toArray() {
        String[] array = new String[]{"test", "string", "list", "word", "string"};
        assertArrayEquals(array, list.toArray());
    }
}