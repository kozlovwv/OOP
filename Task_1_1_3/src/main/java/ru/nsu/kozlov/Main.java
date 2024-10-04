package ru.nsu.kozlov;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("qwerty")));

        System.out.println();
        e.print();
        System.out.println();

        Expression dex = e.derivative("x");
        dex.print();

        System.out.println();
    }
}