package ru.nsu.kozlov;

public class Number extends Expression {
    final double value;

    Number(double num) {
        value = num;
    }

    double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Number second) {
            return value == second.getValue();
        } else {
            return false;
        }
    }

    @Override
    void print() {
        if (value % 1 == 0)
            System.out.print((int)value);
        else
            System.out.print(value);
    }

    @Override
    Expression derivative(String var) {
        return new Number(0.0);
    }

    @Override
    double eval(String varsLine) {
        return value;
    }

    @Override
    Expression simplify() {
        return new Number(value);
    }
}
