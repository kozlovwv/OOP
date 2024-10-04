package ru.nsu.kozlov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
//
//        System.out.println();
//        e.print();
//        System.out.println();
//
//        Expression dex = e.derivative("x");
//        dex.print();
//
//        System.out.println();
//        System.out.println(e.eval("y = 10; x = 5"));

        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();

        System.out.println(expression);

        Expression ex2 = Parser.parse(expression);
        ex2.print();
    }
}