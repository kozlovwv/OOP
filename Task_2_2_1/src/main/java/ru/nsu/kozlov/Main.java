package ru.nsu.kozlov;

public class Main {
    public static void main(String[] args) {
        MyTimer.startTime = System.currentTimeMillis();

        Pizzeria pizzeria = new Pizzeria("src/main/resources/config.json");

        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));

        pizzeria.shutdown();
    }
}
