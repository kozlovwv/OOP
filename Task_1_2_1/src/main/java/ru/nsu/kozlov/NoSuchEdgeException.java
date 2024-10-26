package ru.nsu.kozlov;

/**
 * thrown in class AdjacencyMatrix while trying to remove edge which doesn't exist.
 */
public class NoSuchEdgeException extends RuntimeException {
    public NoSuchEdgeException(String message) {
        super(message);
    }
}
