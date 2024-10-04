package ru.nsu.kozlov;

public class Parser {
    private static String expression;
    private static int pos;

    static Expression parse(String exp) {
        expression = exp;
        pos = 0;

        return parseExpression();
    }

    private static Expression parseExpression() {
        //
    }

    private static Expression parseMonome() {
        //
    }

    private static Expression parseAtom() {
        //
    }

    private static void readToken() {
        //
    }

    private static void peekToken() {
        //
    }
}