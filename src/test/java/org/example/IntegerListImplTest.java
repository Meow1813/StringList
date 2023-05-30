package org.example;

import org.example.Exception.ElementNotExistException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.InvalidItemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    IntegerListImpl list = new IntegerListImpl(10);

    @BeforeEach
    public void fillList() {
        list.add(3);
        list.add(23);
        list.add(543);
        list.add(321);
        list.add(546);
        list.add(21);
        list.add(33333);
        list.add(743);
        list.add(32131);
        list.add(23);
    }

    @AfterEach
    public void clearList() {
        list.clear();
    }

    @Test
    void add() {
        Integer num = 555;
        list.add(num);
        assertEquals(num, list.get(10));
    }

    @Test
    void addNull() {
        assertThrows(InvalidItemException.class, () -> list.add(null));
    }

    @Test
    void indexAdd() {
        Integer num = 555;
        list.add(1, num);
        assertEquals(num, list.get(1));
        assertEquals(Integer.valueOf(23), list.get(2));

    }

    @Test
    void InvalidIndexAdd() {
        assertThrows(InvalidIndexException.class, () -> list.add(23, 28));
    }

    @Test
    void set() {
        list.set(1, 555);
        assertEquals(Integer.valueOf(555), list.get(1));
    }

    @Test
    void InvalidIndexSet() {
        assertThrows(InvalidIndexException.class, () -> list.set(44, 555));
    }

    @Test
    void removeIndex() {

        list.remove(0);
        assertEquals(Integer.valueOf(23), list.get(0));
    }

    @Test
    void InvalidIndexRemove() {
        assertThrows(InvalidIndexException.class, () -> list.remove(11));
    }

    @Test
    void removeItem() {
        list.remove(Integer.valueOf(3));
        assertEquals(Integer.valueOf(23), list.get(0));
    }

    @Test
    void removeNullItem() {
        assertThrows(InvalidItemException.class, () -> list.remove(null));
    }

    @Test
    void removeNotExistItem() {
        assertThrows(ElementNotExistException.class, () -> list.remove(Integer.valueOf(555)));
    }

    @Test
    void contains() {
        assertTrue(list.contains(23));
        assertFalse(list.contains(555));
    }

    @Test
    void indexOf() {
        assertEquals(1, list.indexOf(23));
    }

    @Test
    void indexOfNotExist() {
        assertEquals(-1, list.indexOf(555));
    }

    @Test
    void lastIndexOf() {
        assertEquals(9, list.lastIndexOf(23));
    }

    @Test
    void lastIndexOfNotExist() {
        assertEquals(-1, list.lastIndexOf(555));
    }

    @Test
    void get() {
        assertEquals(Integer.valueOf(23), list.get(1));
    }

    @Test
    void invalidIndexGet() {
        assertThrows(InvalidIndexException.class, () -> list.get(23));
    }

    @Test
    void equals() {
        IntegerList newList = new IntegerListImpl();
        newList.add(3);
        newList.add(23);
        newList.add(543);
        newList.add(321);
        newList.add(546);
        newList.add(21);
        newList.add(33333);
        newList.add(743);
        newList.add(32131);
        newList.add(23);
        assertTrue(list.equals(newList));
    }

    @Test
    void size() {
        assertEquals(10, list.size());
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
        Integer[] array = new Integer[]{3, 23, 543, 321, 546, 21, 33333, 743, 32131, 23};
        assertArrayEquals(array, list.toArray());
    }
}