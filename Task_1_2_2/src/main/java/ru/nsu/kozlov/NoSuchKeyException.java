package ru.nsu.kozlov;

/**
 * exception thrown when hashTable doesn't contain cell with such key.
 */
public class NoSuchKeyException extends RuntimeException {
    public NoSuchKeyException(String message) {
        super(message);
    }
}
