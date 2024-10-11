package ru.nsu.kozlov;

public class Mul extends Expression {
    final Expression leftOp;
    final Expression rightOp;

    Mul(Expression l, Expression r) {
        leftOp = l;
        rightOp = r;
    }

    Expression getLeftOp() {
        return leftOp;
    }

    Expression getRightOp() {
        return rightOp;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Mul second) {
            return (leftOp.equals(second.getLeftOp()) && rightOp.equals(second.getRightOp()))
                    || (leftOp.equals(second.getRightOp()) && rightOp.equals(second.getLeftOp()));
        } else {
            return false;
        }
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
        return new Add(new Mul(leftOp.derivative(var), rightOp),
                       new Mul(leftOp, rightOp.derivative(var)));
    }

    @Override
    double eval(String varsLine) {
        return leftOp.eval(varsLine) * rightOp.eval(varsLine);
    }

    @Override
    Expression simplify() {
        Expression l = leftOp.simplify();
        Expression r = rightOp.simplify();

        if ((l instanceof Number) && (r instanceof Number)) {
            return new Number(l.eval(null) * r.eval(null));
        } else if ((l instanceof Number) && (l.eval(null) == 0.0)) {
            return new Number(0.0);
        } else if ((r instanceof Number) && (r.eval(null) == 0.0)) {
            return new Number(0.0);
        } else if ((l instanceof Number) && (l.eval(null) == 1.0)) {
            return r;
        } else if ((r instanceof Number) && (r.eval(null) == 1.0)) {
            return l;
        } else {
            return new Mul(l, r);
        }
    }
}
