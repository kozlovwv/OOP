package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void test1() {
        MyTimer.startTime = System.currentTimeMillis();

        Pizzeria pizzeria = new Pizzeria("src/test/resources/Config_For_Test_1.json");

        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }

    @Test
    void test2() {
        MyTimer.startTime = System.currentTimeMillis();

        Pizzeria pizzeria = new Pizzeria("src/test/resources/Config_For_Test_2.json");

        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }

    @Test
    void test3() {
        MyTimer.startTime = System.currentTimeMillis();

        Pizzeria pizzeria = new Pizzeria("src/test/resources/Config_For_Test_3.json");

        pizzeria.addOrder(new Order(5));
        pizzeria.addOrder(new Order(10));
        pizzeria.addOrder(new Order(6));
        pizzeria.addOrder(new Order(13));
        pizzeria.addOrder(new Order(2));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }
}