package ru.nsu.kozlov;

/**
 * interface that allows user show how to convert string into required object.
 *
 * @param <T> type of returned object.
 */
public interface Converter<T> {
    T convert(String line);
}
