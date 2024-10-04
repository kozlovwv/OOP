package ru.nsu.kozlov;

public class Variable extends Expression {
    String varName;

    Variable(String variableName) {
        varName = variableName;
    }

    @Override
    void print() {
        System.out.print(varName);
    }

    @Override
    Expression derivative(String var) {
        return (varName.equals(var) ? new Number(1) : new Number(0));
    }

    @Override
    Expression deepCopy() {
        return new Variable(varName);
    }
}
