package ru.nsu.kozlov;

/** implementation of parsing algorithm.
 */
public class Parser {
    private static String expression;
    private static String token;
    private static int pos;
    private static int len;

    static Expression parse(String exp) {
        expression = exp;
        pos = 0;
        len = exp.length();

        return parseExpression();
    }

    private static Expression parseExpression() {
        Expression exL = parseMonome();
        String oper;
        Expression exR;
        while (peekToken().equals("+") || peekToken().equals("-")) {
            oper = readToken();
            exR = parseMonome();
            if (oper.equals("+")) {
                exL = new Add(exL, exR);
            } else {
                exL = new Sub(exL, exR);
            }
        }
        return exL;
    }

    private static Expression parseMonome() {
        Expression exL = parseAtom();
        String oper;
        Expression exR;
        while (peekToken().equals("*") || peekToken().equals("/")) {
            oper = readToken();
            exR = parseAtom();
            if (oper.equals("*")) {
                exL = new Mul(exL, exR);
            } else {
                exL = new Div(exL, exR);
            }
        }
        return exL;
    }

    private static Expression parseAtom() {
        if (peekToken().equals("(")) {
            readToken();
            Expression ex = parseExpression();
            readToken();
            return ex;
        } else {
            readToken();
            if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
                return new Number(Double.parseDouble(token));
            } else {
                return new Variable(token);
            }
        }
    }

    private static String readToken() {
        while (pos < len && (expression.charAt(pos) == ' ' || expression.charAt(pos) == '\t')) {
            pos++;
        }

        if (pos == len) {
            return token = "";
        }

        if ("+-*/()".contains(String.valueOf(expression.charAt(pos)))) {
            return token = String.valueOf(expression.charAt(pos++));
        }

        int left = pos;
        while (pos < len && isDigitOrLetter()) {
            pos++;
        }
        return token = expression.substring(left, pos);
    }

    private static String peekToken() {
        int oldPos = pos;
        token = readToken();
        pos = oldPos;
        return token;
    }

    private static boolean isDigitOrLetter() {
        char ch = expression.charAt(pos);
        boolean cond1 = ch >= '0' && ch <= '9';
        boolean cond2 = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
        return cond1 || cond2 || (ch == '.');
    }
}