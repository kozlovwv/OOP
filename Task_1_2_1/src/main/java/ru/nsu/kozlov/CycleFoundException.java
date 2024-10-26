package ru.nsu.kozlov;

/**
 * thrown in class Toposort when graph contain cycle.
 */
public class CycleFoundException extends RuntimeException {
    public CycleFoundException(String message) {
        super(message);
    }
}
