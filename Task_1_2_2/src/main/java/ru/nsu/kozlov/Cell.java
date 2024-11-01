package ru.nsu.kozlov;

public class Cell<K, V> {
    private K key;
    private V value;

    public Cell(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
