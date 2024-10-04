package ru.nsu.kozlov;

public abstract class Expression {
    abstract void print();
    abstract Expression derivative(String var);
    abstract int eval(String varsLine);
    abstract Expression deepCopy();
}