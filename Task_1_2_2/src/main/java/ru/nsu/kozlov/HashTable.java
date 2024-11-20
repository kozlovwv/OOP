package ru.nsu.kozlov;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * implementation of hashTable.
 *
 * @param <K> type of keys in table
 * @param <V> type of values in table
 */
public class HashTable<K, V> implements Iterable<Cell<K, V>> {

    private int modCount;
    private int numberOfCells;
    private int capacity;
    private LinkedList<Cell<K, V>>[] hashTable;

    /**
     * hashTable's constructor.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        modCount = 0;
        numberOfCells = 0;
        capacity = 4;
        hashTable = (LinkedList<Cell<K, V>>[]) new LinkedList<?>[capacity];
    }

    public int getModCount() {
        return modCount;
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public int getCapacity() {
        return capacity;
    }

    public LinkedList<Cell<K, V>>[] getHashTable() {
        return hashTable;
    }

    @SuppressWarnings("unchecked")
    private void extend() {
        LinkedList<Cell<K, V>>[] prevHashTable = hashTable;

        modCount -= numberOfCells;
        numberOfCells = 0;
        capacity = capacity * 2;
        hashTable = (LinkedList<Cell<K, V>>[]) new LinkedList<?>[capacity];

        for (LinkedList<Cell<K, V>> currList : prevHashTable) {
            if (currList != null) {
                for (Cell<K, V> kvCell : currList) {
                    this.add(kvCell.getKey(), kvCell.getValue());
                }
            }
        }
    }

    /**
     * adding pair key-value in table, if cell with such key already exists
     * value will be overwritten.
     *
     * @param key key
     * @param value value
     */
    public void add(K key, V value) {
        int hashIndex = key.hashCode() % capacity;

        if (hashTable[hashIndex] == null) {
            hashTable[hashIndex] = new LinkedList<Cell<K, V>>();
            hashTable[hashIndex].add(new Cell<>(key, value));

            modCount++;
            numberOfCells++;

            if (numberOfCells == capacity) {
                this.extend();
            }

            return;
        }

        LinkedList<Cell<K, V>> currList = hashTable[hashIndex];

        for (Cell<K, V> kvCell : currList) {
            if (kvCell.getKey().equals(key)) {
                kvCell.setValue(value);
                return;
            }
        }

        currList.add(new Cell<>(key, value));
        numberOfCells++;

        if (numberOfCells == capacity) {
            extend();
        }

        modCount++;
    }

    /**
     * removing pair key-value from table by using key.
     *
     * @param key key
     */
    public void remove(K key) {
        int hashIndex = key.hashCode() % capacity;

        if (hashTable[hashIndex] == null) {
            throw new NoSuchKeyException("No such key: " + key);
        }

        LinkedList<Cell<K, V>> currList = hashTable[hashIndex];
        Cell<K, V> currCell;
        int size = currList.size();

        for (int i = 0; i < size; i++) {
            currCell = currList.get(i);
            if (currCell.getKey().equals(key)) {
                currList.remove(i);
                modCount++;
                numberOfCells--;
                return;
            }
        }

        throw new NoSuchKeyException("No such key: " + key);
    }

    /**
     * getting value by key from table.
     *
     * @param key key
     * @return return value corresponding to this key
     */
    public V get(K key) {
        int hashIndex = key.hashCode() % capacity;

        if (hashTable[hashIndex] == null) {
            throw new NoSuchKeyException("No such key: " + key);
        }

        LinkedList<Cell<K, V>> currList = hashTable[hashIndex];

        for (Cell<K, V> kvCell : currList) {
            if (kvCell.getKey().equals(key)) {
                return kvCell.getValue();
            }
        }

        throw new NoSuchKeyException("No such key: " + key);
    }

    /**
     * updating value in pair key-value in table.
     *
     * @param key key
     * @param value new value
     */
    public void update(K key, V value) {
        int hashIndex = key.hashCode() % capacity;

        if (hashTable[hashIndex] == null) {
            throw new NoSuchKeyException("No such key: " + key);
        }

        LinkedList<Cell<K, V>> currList = hashTable[hashIndex];

        for (Cell<K, V> kvCell : currList) {
            if (kvCell.getKey().equals(key)) {
                kvCell.setValue(value);
                modCount++;
                return;
            }
        }

        throw new NoSuchKeyException("No such key: " + key);
    }

    /**
     * checking for value for given key
     *
     * @param key key
     * @return true if there's pair key-value for given key, false otherwise.
     */
    public boolean hasValue(K key) {
        int hashIndex = key.hashCode() % capacity;

        if (hashTable[hashIndex] == null) {
            return false;
        }

        LinkedList<Cell<K, V>> currList = hashTable[hashIndex];

        for (Cell<K, V> kvCell : currList) {
            if (kvCell.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<Cell<K, V>> iterator() {
        return new Iterator<Cell<K, V>>() {

            private final int expectedModCount = modCount;
            private int outerIndex = 0;
            private int innerIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                int copyOuterIndex = outerIndex;

                while (copyOuterIndex < capacity && hashTable[copyOuterIndex] == null) {
                    copyOuterIndex++;
                }
                if (copyOuterIndex == capacity) {
                    return false;
                }

                if (copyOuterIndex != outerIndex) {
                    return true;
                } else {
                    if (innerIndex < hashTable[copyOuterIndex].size()) {
                        return true;
                    } else {
                        do {
                            copyOuterIndex++;
                        } while (copyOuterIndex < capacity && hashTable[copyOuterIndex] == null);

                        return copyOuterIndex != capacity;
                    }
                }
            }

            @Override
            public Cell<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                while (hashTable[outerIndex] == null) {
                    outerIndex++;
                }
                if (innerIndex >= hashTable[outerIndex].size()) {
                    innerIndex = 0;
                    do {
                        outerIndex++;
                    } while (hashTable[outerIndex] == null);
                }
                return hashTable[outerIndex].get(innerIndex++);
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HashTable<K, V> hashTable1 = (HashTable<K, V>) object;

        if (numberOfCells == hashTable1.numberOfCells) {
            for (int i = 0; i < capacity; i++) {
                if (hashTable[i] == null) {
                    continue;
                }
                LinkedList<Cell<K, V>> currList = hashTable[i];
                for (Cell<K, V> kvCell : currList) {
                    if (hashTable1.hasValue(kvCell.getKey())) {
                        if (hashTable1.get(kvCell.getKey()) != kvCell.getValue()) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modCount, numberOfCells, capacity, Arrays.hashCode(hashTable));
    }

    @Override
    public String toString() {
        String res = "Number of cells = " + numberOfCells
                    + "\nCapacity = " + capacity
                    + "\nModCount = " + modCount
                    + "\nHash Table:\n";

        for (int i = 0; i < capacity; i++) {
            res = res + String.valueOf(i) + ". ";
            if (hashTable[i] == null) {
                res += "null\n";
            } else {
                LinkedList<Cell<K, V>> currList = hashTable[i];
                int size = currList.size();
                for (int j = 0; j < size - 1; j++) {
                    res = res + currList.get(j) + "; ";
                }
                res = res + currList.get(size - 1) + "\n";
            }
        }

        return res;
    }
}
