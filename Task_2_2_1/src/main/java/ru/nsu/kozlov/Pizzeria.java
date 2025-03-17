package ru.nsu.kozlov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Implementation of Pizzeria with bakers-threads and deliveryMen-threads.
 */
public class Pizzeria {
    private Baker[] bakers;
    private DeliveryMan[] deliveryMen;
    private QueueWithOrders queueWithOrders;
    private Warehouse warehouse;
    private boolean isOpened;

    private int numberOfReceivedPizza;
    private int numberOfCompletedPizza;

    /**
     * Pizzeria constructor.
     *
     * @param path path to json config file.
     */
    public Pizzeria(String path) {
        int warehouseCapacity;
        int[] bakersConfig;
        int[] deliveryMenConfig;

        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(path);
            Configuration config = mapper.readValue(file, Configuration.class);
            warehouseCapacity = config.getWarehouseCapacity();
            bakersConfig = config.getBakers();
            deliveryMenConfig = config.getDeliveryMen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        queueWithOrders = new QueueWithOrders();
        warehouse = new Warehouse(warehouseCapacity);

        bakers = new Baker[bakersConfig.length];
        for (int i = 0; i < bakersConfig.length; i++) {
            bakers[i] = new Baker(queueWithOrders, warehouse, bakersConfig[i]);
            bakers[i].start();
        }

        deliveryMen = new DeliveryMan[deliveryMenConfig.length];
        for (int i = 0; i < deliveryMenConfig.length; i++) {
            deliveryMen[i] = new DeliveryMan(warehouse, deliveryMenConfig[i], this);
            deliveryMen[i].start();
        }

        numberOfCompletedPizza = numberOfReceivedPizza = 0;
        isOpened = true;

        Order.setZeroId();
    }

    public void open() {
        isOpened = true;
        System.out.println("Pizzeria is opened: " + MyTimer.getTime());
    }

    public void close() {
        isOpened = false;
        System.out.println("Pizzeria is CLOSED: " + MyTimer.getTime());
    }

    /**
     * Adding order to the queue in the pizzeria.
     *
     * @param order new order.
     */
    public void addOrder(Order order) {
        if (isOpened) {
            numberOfReceivedPizza += order.getPizzaAmount();
            queueWithOrders.takeOrder(order);
        } else {
            System.out.println("Order " + order.getId() + " ignored." + " " + MyTimer.getTime());
        }
    }

    public synchronized void increaseCompletedPizza(int amount) {
        numberOfCompletedPizza += amount;
    }

    public synchronized int getNumberOfCompletedPizza() {
        return numberOfCompletedPizza;
    }

    public int getNumberOfReceivedPizza() {
        return numberOfReceivedPizza;
    }

    /**
     * Closing pizzeria with stopping bakers and deliveryMen after finishing all orders
     * which was in the queue.
     */
    public void shutdown() {
        while (numberOfReceivedPizza > this.getNumberOfCompletedPizza()) {
            continue;
        }

        for (Baker baker : bakers) {
            baker.interrupt();
        }

        for (DeliveryMan deliveryMan : deliveryMen) {
            deliveryMan.interrupt();
        }

        System.out.println("Мы обанкротились! :(");
    }
}
