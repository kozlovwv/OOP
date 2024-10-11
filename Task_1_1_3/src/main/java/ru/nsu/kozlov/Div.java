package ru.nsu.kozlov;

public class Div extends Expression {
    final Expression leftOp;
    final Expression rightOp;

    Div(Expression l, Expression r) {
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
        if (object instanceof Div second) {
            return (leftOp.equals(second.getLeftOp()) && rightOp.equals(second.getRightOp()));
        } else {
            return false;
        }
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
        return new Div(new Sub(new Mul(rightOp.derivative(var), leftOp),
                               new Mul(rightOp, leftOp.derivative(var))),
                       new Mul(rightOp, rightOp));
    }

    @Override
    double eval(String varsLine) {
        double res = leftOp.eval(varsLine) / rightOp.eval(varsLine);

        if (Double.isInfinite(res)) {
            throw new IllegalStateException();
        }
        return res;
    }

    @Override
    Expression simplify() {
        Expression l = leftOp.simplify();
        Expression r = rightOp.simplify();

        if ((l instanceof Number) && (r instanceof Number)) {
            double res = l.eval(null) / r.eval(null);
            if (Double.isInfinite(res)) {
                throw new IllegalStateException();
            }
            return new Number(res);
        } else if ((l instanceof Number) && (l.eval(null) == 0.0)) {
            return new Number(0.0);
        } else if ((r instanceof Number) && (r.eval(null) == 1.0)) {
            return l;
        } else if ((r instanceof Number) && (r.eval(null) == 0.0)) {
            throw new IllegalStateException();
        } else {
            return new Div(l, r);
        }
    }
}