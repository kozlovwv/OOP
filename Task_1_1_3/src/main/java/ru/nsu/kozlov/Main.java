package ru.nsu.kozlov;

public class Main {
    public static void main(String[] args) {
        Expression ex = new Sub(new Add(new Number(1), new Variable("x")),
                                new Add(new Number(1), new Variable("x")));
        ex.print();
        System.out.println();

        Expression simple = ex.simplify();
        simple.print();
    }
}