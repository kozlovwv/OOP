package ru.nsu.kozlov;

public abstract class Expression {
    abstract void print();
    abstract Expression derivative(String var);
    // 3. abstract int eval(String vars);
    abstract Expression deepCopy();
}