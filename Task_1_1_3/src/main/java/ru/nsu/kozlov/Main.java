package ru.nsu.kozlov;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String expression = scan.nextLine();

        Expression ex = Parser.parse(expression);
        ex.print();
    }
}