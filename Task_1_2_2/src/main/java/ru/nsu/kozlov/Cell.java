package ru.nsu.kozlov;

import java.util.Objects;

public class Cell<K, V> {
    private final K key;
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

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return '[' + "KEY = " + key + ", VALUE = " + value + ']';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cell<?, ?> cell = (Cell<?, ?>) object;
        return Objects.equals(key, cell.key) && Objects.equals(value, cell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
