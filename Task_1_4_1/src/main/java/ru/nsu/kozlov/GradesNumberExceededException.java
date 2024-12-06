package ru.nsu.kozlov;

public class GradesNumberExceededException extends RuntimeException {
    public GradesNumberExceededException(String message) {
        super(message);
    }
}
