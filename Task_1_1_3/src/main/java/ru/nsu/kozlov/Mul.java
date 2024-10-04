package ru.nsu.kozlov;

public class Mul extends Expression {
    Expression leftOp;
    Expression rightOp;

    Mul(Expression L, Expression R) {
        leftOp = L;
        rightOp = R;
    }

    @Override
    void print() {
        System.out.print("(");
        leftOp.print();
        System.out.print("*");
        rightOp.print();
        System.out.print(")");
    }

    @Override
    Expression derivative(String var) {
        return new Add(new Mul(leftOp.derivative(var), rightOp.deepCopy()),
                       new Mul(leftOp.deepCopy(), rightOp.derivative(var)));
    }

    @Override
    Expression deepCopy() {
        return new Mul(leftOp.deepCopy(), rightOp.deepCopy());
    }
}
