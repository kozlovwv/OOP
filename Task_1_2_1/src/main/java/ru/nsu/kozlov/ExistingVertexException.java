package ru.nsu.kozlov;

/**
 * thrown when trying to add an already existing vertex.
 */
public class ExistingVertexException extends RuntimeException {
    public ExistingVertexException(String message) {
        super(message);
    }
}
