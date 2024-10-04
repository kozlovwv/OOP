package ru.nsu.kozlov;

public class Number extends Expression {
    int value;

    Number(int num) {
        value = num;
    }

    @Override
    void print() {
        System.out.print(value);
    }

    @Override
    Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    Expression deepCopy() {
        return new Number(value);
    }

    @Override
    int eval(String varsLine) {
        return value;
    }
}
