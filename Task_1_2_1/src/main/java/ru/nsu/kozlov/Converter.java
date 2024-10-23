package ru.nsu.kozlov;

public interface Converter<T>{
    T convert(String line);
}
