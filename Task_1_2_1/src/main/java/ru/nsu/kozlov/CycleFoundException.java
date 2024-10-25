package ru.nsu.kozlov;

public class CycleFoundException extends RuntimeException {
    public CycleFoundException(String message) {
        super(message);
    }
}
