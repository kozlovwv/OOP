package ru.nsu.kozlov;

/** abstract class Expression with its functions.
 */
public abstract class Expression {
    abstract void print();

    abstract Expression derivative(String var);

    abstract double eval(String varsLine);

    abstract Expression simplify();

    @Override
    public abstract boolean equals(Object object);
}