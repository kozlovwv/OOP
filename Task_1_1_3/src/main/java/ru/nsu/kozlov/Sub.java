package ru.nsu.kozlov;

public class Sub extends Expression {
    Expression leftOp;
    Expression rightOp;

    Sub(Expression L, Expression R) {
        leftOp = L;
        rightOp = R;
    }

    @Override
    void print() {
        System.out.print("(");
        leftOp.print();
        System.out.print("-");
        rightOp.print();
        System.out.print(")");
    }

    @Override
    Expression derivative(String var) {
        return new Sub(leftOp.derivative(var), rightOp.derivative(var));
    }

    @Override
    Expression deepCopy() {
        return new Sub(leftOp.deepCopy(), rightOp.deepCopy());
    }
}
