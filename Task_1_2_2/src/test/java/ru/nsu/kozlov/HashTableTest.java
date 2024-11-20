package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class HashTableTest {
    @Test
    void testExtend() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        boolean cond1 = hashTable.getCapacity() == 4
                        && hashTable.getHashTable().length == 4
                        && hashTable.getNumberOfCells() == 3;

        hashTable.add("r", 4);

        boolean cond2 = hashTable.getCapacity() == 8
                        && hashTable.getHashTable().length == 8
                        && hashTable.getNumberOfCells() == 4;

        assertTrue(cond1 && cond2);
    }

    @Test
    void testAdd() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        int indexOfQ = "q".hashCode() % 4;
        int indexOfW = "w".hashCode() % 4;
        int indexOfE = "e".hashCode() % 4;

        LinkedList<Cell<String, Integer>>[] hashTab = hashTable.getHashTable();
        
        assertTrue(hashTab[indexOfQ].get(0).getKey().equals("q")
                    && hashTab[indexOfQ].get(0).getValue() == 1
                    && hashTab[indexOfE].get(1).getKey().equals("e")
                    && hashTab[indexOfE].get(1).getValue() == 3
                    && hashTab[indexOfW].get(0).getKey().equals("w")
                    && hashTab[indexOfW].get(0).getValue() == 2
                    && hashTable.getNumberOfCells() == 3
                    && hashTable.getCapacity() == 4
                    && hashTable.getModCount() == 3);
    }

    @Test
    void testAddWithReset() {
        HashTable<String, Integer> hashTable = new HashTable<>();

        int indexOfQ = "q".hashCode() % 4;

        hashTable.add("q", 1);
        LinkedList<Cell<String, Integer>>[] hashTab = hashTable.getHashTable();
        boolean cond1 = hashTab[indexOfQ].getFirst().getKey().equals("q")
                        && hashTab[indexOfQ].getFirst().getValue() == 1;

        hashTable.add("q", 2);
        hashTab = hashTable.getHashTable();
        boolean cond2 = hashTab[indexOfQ].getFirst().getKey().equals("q")
                && hashTab[indexOfQ].getFirst().getValue() == 2;

        assertTrue(cond1 && cond2);
    }

    @Test
    void testRemove() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        int indexOfE = "e".hashCode() % 4;

        LinkedList<Cell<String, Integer>>[] hashTab = hashTable.getHashTable();
        boolean cond1 = hashTable.getNumberOfCells() == 3
                        && hashTab[indexOfE].size() == 2
                        && hashTab[indexOfE].getLast().getKey().equals("e")
                        && hashTab[indexOfE].getLast().getValue() == 3;

        hashTable.remove("e");

        hashTab = hashTable.getHashTable();
        boolean cond2 = hashTable.getNumberOfCells() == 2
                        && hashTab[indexOfE].size() == 1
                        && hashTab[indexOfE].getLast().getKey().equals("q")
                        && hashTab[indexOfE].getLast().getValue() == 1;

        assertTrue(cond1 && cond2);
    }

    @Test
    void testRemoveException() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.remove("r");
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testRemoveException1() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.remove("a");
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testGet() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertEquals(3, (int) hashTable.get("e"));
    }

    @Test
    void testGetWithException() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.get("r");
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testGetWithException2() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.get("a");
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testUpdate() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        boolean cond1 = hashTable.get("q") == 1;

        hashTable.update("q", 100);

        boolean cond2 = hashTable.get("q") == 100;

        assertTrue(cond1 && cond2);
    }

    @Test
    void testUpdateWithException() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.update("r", 100);
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testUpdateWithException1() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        try {
            hashTable.update("a", 100);
        } catch (NoSuchKeyException exception) {
            return;
        }

        fail();
    }

    @Test
    void testHasValue() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertTrue(hashTable.hasValue("e"));
    }

    @Test
    void testHasValue1() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertFalse(hashTable.hasValue("r"));
    }

    @Test
    void testHasValue2() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertFalse(hashTable.hasValue("a"));
    }

    @Test
    void testEquals() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertEquals(hashTable, hashTable);
    }

    @Test
    void testEquals1() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertNotEquals(hashTable, null);
    }

    @Test
    void testEquals2() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        assertNotEquals(hashTable, "null");
    }

    @Test
    void testEquals3() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        HashTable<String, Integer> hashTable2 = new HashTable<>();
        hashTable2.add("q", 1);
        hashTable2.add("w", 2);
        hashTable2.add("e", 3);

        assertEquals(hashTable, hashTable2);
    }

    @Test
    void testEquals4() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        HashTable<String, Integer> hashTable2 = new HashTable<>();
        hashTable2.add("r", 1);
        hashTable2.add("w", 2);
        hashTable2.add("e", 3);

        assertNotEquals(hashTable, hashTable2);
    }

    @Test
    void testEquals5() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        HashTable<String, Integer> hashTable2 = new HashTable<>();
        hashTable2.add("q", 10);
        hashTable2.add("w", 2);
        hashTable2.add("e", 3);

        assertNotEquals(hashTable, hashTable2);
    }

    @Test
    void testHashCode() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        HashTable<String, Integer> hashTable2 = new HashTable<>();
        hashTable2.add("q", 1);
        hashTable2.add("w", 2);
        hashTable2.add("e", 3);

        assertEquals(hashTable.hashCode(), hashTable2.hashCode());
    }

    @Test
    void testToString() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        String res = hashTable.toString();

        assertEquals(res, "Number of cells = 3\n"
            + "Capacity = 4\n"
            + "ModCount = 3\n"
            + "Hash Table:\n"
            + "0. null\n"
            + "1. [KEY = q, VALUE = 1]; [KEY = e, VALUE = 3]\n"
            + "2. null\n"
            + "3. [KEY = w, VALUE = 2]\n");
    }

    @Test
    void testIterator() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        Iterator<Cell<String, Integer>> iterator = hashTable.iterator();
        Cell<String, Integer> cell;

        while (iterator.hasNext()) {
            cell = iterator.next();
        }
    }

    @Test
    void testIteratorWithException() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        Iterator<Cell<String, Integer>> iterator = hashTable.iterator();
        Cell<String, Integer> cell;

        try {
            while (iterator.hasNext()) {
                cell = iterator.next();
                if (cell.getKey().equals("q")) {
                    hashTable.update("q", 100);
                }
            }
        } catch (ConcurrentModificationException exception) {
            return;
        }

        fail();
    }

    @Test
    void testIteratorWithException1() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        Iterator<Cell<String, Integer>> iterator = hashTable.iterator();
        Cell<String, Integer> cell;

        try {
            while (iterator.hasNext()) {
                cell = iterator.next();
            }
            cell = iterator.next();
        } catch (NoSuchElementException exception) {
            return;
        }

        fail();
    }

    @Test
    void testIteratorWithException2() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.add("q", 1);
        hashTable.add("w", 2);
        hashTable.add("e", 3);

        Iterator<Cell<String, Integer>> iterator = hashTable.iterator();
        Cell<String, Integer> cell = null;

        try {
            while (iterator.hasNext()) {
                hashTable.add("r", 100);
                cell = iterator.next();
            }
        } catch (ConcurrentModificationException exception) {
            return;
        }

        fail();
    }
}