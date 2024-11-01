package ru.nsu.kozlov;

/**
 * thrown out when vertex doesn't exist.
 */
public class NoSuchVertexException extends RuntimeException {
    public NoSuchVertexException(String message) {
        super(message);
    }
}
