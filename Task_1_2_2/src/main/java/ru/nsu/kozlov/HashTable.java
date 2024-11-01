package ru.nsu.kozlov;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable<K, V> {

    private int total;
    private int capacity;
    ArrayList<LinkedList<Cell<K, V>>> hashTable;

    public HashTable() {
        total = 0;
        capacity = 1000;
        //hashTable = new ArrayList<>(1000);
    }

    public void add(K key, V value) {

    }

    public void remove(K key, V value) {

    }

    public V get(K key) {
        return null;
    }

    public void update(K key, V value) {

    }

    public boolean hasValue(K key) {
        return false;
    }

    //7 - iteration?

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
