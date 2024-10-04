package ru.nsu.kozlov;

public class Div extends Expression {
    Expression leftOp;
    Expression rightOp;

    Div(Expression L, Expression R) {
        leftOp = L;
        rightOp = R;
    }

    @Override
    void print() {
        System.out.print("(");
        leftOp.print();
        System.out.print("/");
        rightOp.print();
        System.out.print(")");
    }

    @Override
    Expression derivative(String var) {
        return new Div(new Sub(new Mul(rightOp.derivative(var), leftOp.deepCopy()),
                               new Mul(rightOp.deepCopy(), leftOp.derivative(var))),
                       new Mul(rightOp.deepCopy(), rightOp.deepCopy()));
    }

    @Override
    Expression deepCopy() {
        return new Sub(leftOp.deepCopy(), rightOp.deepCopy());
    }

    @Override
    int eval(String varsLine) {
        return leftOp.eval(varsLine) / rightOp.eval(varsLine);
    }
}
