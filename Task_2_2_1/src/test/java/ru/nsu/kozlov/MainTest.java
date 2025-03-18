package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class MainTest {
    @Test
    void test1() throws InterruptedException {
        MyTimer.startTime = System.currentTimeMillis();

        Configuration config;

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/test/resources/Config_For_Test_1.json");
            config = mapper.readValue(file, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pizzeria pizzeria = new Pizzeria(config);

        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));
        pizzeria.addOrder(new Order(1));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }

    @Test
    void test2() throws InterruptedException {
        MyTimer.startTime = System.currentTimeMillis();

        Configuration config;

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/test/resources/Config_For_Test_2.json");
            config = mapper.readValue(file, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pizzeria pizzeria = new Pizzeria(config);

        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));
        pizzeria.addOrder(new Order(3));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }

    @Test
    void test3() throws InterruptedException {
        MyTimer.startTime = System.currentTimeMillis();

        Configuration config;

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/test/resources/Config_For_Test_3.json");
            config = mapper.readValue(file, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Pizzeria pizzeria = new Pizzeria(config);

        pizzeria.addOrder(new Order(5));
        pizzeria.addOrder(new Order(10));
        pizzeria.addOrder(new Order(6));
        pizzeria.addOrder(new Order(13));
        pizzeria.addOrder(new Order(2));

        pizzeria.shutdown();

        assertEquals(pizzeria.getNumberOfCompletedPizza(), pizzeria.getNumberOfReceivedPizza());
    }
}