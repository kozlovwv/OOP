package ru.nsu.kozlov;

public class Main {
    public static void main(String[] args) {
        MyTimer.startTime = System.currentTimeMillis();

        Pizzeria pizzeria = new Pizzeria(2, 2);
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(17));

        pizzeria.close();

        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
    }
}
