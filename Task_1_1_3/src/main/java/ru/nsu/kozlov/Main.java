package ru.nsu.kozlov;

public class Main {
    public static void main(String[] args) {
        Expression ex = new Sub(new Add(new Number(1), new Variable("x")),
                                new Add(new Number(1), new Variable("x")));
        ex.print();
        System.out.println();

        Expression simple = ex.simplify();
        simple.print();

//        System.out.println();
//        Expression ex2 = Parser.parse("1 + 1 + 1 + (((((2 * 8)/2))))");
//        ex2.print();
//        System.out.println();
//        System.out.println((ex2.eval("x = 2")));
    }
}