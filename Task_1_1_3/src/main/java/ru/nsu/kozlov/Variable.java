package ru.nsu.kozlov;

public class Variable extends Expression {
    final String varName;

    Variable(String variableName) {
        varName = variableName;
    }

    String getValue() {
        return varName;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable second) {
            return varName.equals(second.getValue());
        } else {
            return false;
        }
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
    double eval(String varsLine) {
        String[] vars = varsLine.split(";");
        double res = 0.0;
        boolean flag = false;

        for (String line : vars) {
            String[] partsOfLine = line.split("=");

            partsOfLine[0] = partsOfLine[0].trim();
            partsOfLine[1] = partsOfLine[1].trim();

            if (partsOfLine[0].equals(varName)) {
                res = Double.parseDouble(partsOfLine[1]);
                flag = true;
                break;
            }
        }

        if (flag) {
            return res;
        } else {
            throw new ArithmeticException();
        }
    }

    @Override
    Expression simplify() {
        return new Variable(varName);
    }
}
