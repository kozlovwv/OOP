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

    @Override
    int eval(String varsLine) {
        String[] vars = varsLine.split(";");
        int res = 0;

        for (String line : vars) {
            String[] partsOfLine = line.split("=");

            partsOfLine[0] = partsOfLine[0].trim();
            partsOfLine[1] = partsOfLine[1].trim();

            if (partsOfLine[0].equals(varName)) {
                res = Integer.parseInt(partsOfLine[1]);
                break;
            }
        }

        return res;
    }
}
