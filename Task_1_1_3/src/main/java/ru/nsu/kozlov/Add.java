package ru.nsu.kozlov;

public class Add extends Expression {
    final Expression leftOp;
    final Expression rightOp;

    Add(Expression L, Expression R) {
        leftOp = L;
        rightOp = R;
    }

    Expression getLeftOp() {
        return leftOp;
    }

    Expression getRightOp() {
        return rightOp;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Add second) {
            return (leftOp.equals(second.getLeftOp()) && rightOp.equals(second.getRightOp())) ||
                    (leftOp.equals(second.getRightOp()) && rightOp.equals(second.getLeftOp()));
        } else {
            return false;
        }
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
    double eval(String varsLine) {
        return leftOp.eval(varsLine) + rightOp.eval(varsLine);
    }

    @Override
    Expression simplify() {
        Expression L = leftOp.simplify();
        Expression R = rightOp.simplify();

        if ((L instanceof Number) && (R instanceof Number)) {
            return new Number(L.eval(null) + R.eval(null));
        } else if ((L instanceof Number) && (L.eval(null) == 0.0)) {
            return R;
        } else if ((R instanceof Number) && (R.eval(null) == 0.0)) {
            return L;
        } else {
            return new Add(L, R);
        }
    }
}
