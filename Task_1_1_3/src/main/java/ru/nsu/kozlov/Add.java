package ru.nsu.kozlov;

public class Add extends Expression {
    Expression leftOp;
    Expression rightOp;

    Add(Expression L, Expression R) {
        leftOp = L;
        rightOp = R;
    }

    @Override
    void print() {
        System.out.print("(");
        leftOp.print();
        System.out.print("+");
        rightOp.print();
        System.out.print(")");
    }

    @Override
    Expression derivative(String var) {
        return new Add(leftOp.derivative(var), rightOp.derivative(var));
    }

    @Override
    Expression deepCopy() {
        return new Add(leftOp.deepCopy(), rightOp.deepCopy());
    }
}
