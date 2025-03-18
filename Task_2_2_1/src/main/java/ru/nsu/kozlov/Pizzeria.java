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
    private final QueueWithOrders queueWithOrders;
    private final Warehouse warehouse;
    private boolean isOpened;

    private int numberOfReceivedPizza;
    private int numberOfCompletedPizza;

    private int numberOfWaitingBakers;
    private int numberOfWaitingDeliveryMen;

    private boolean couldFinishBakers;
    private boolean couldFinishDeliveryMen;

    /**
     * Pizzeria constructor.
     *
     * @param config config file.
     */
    public Pizzeria(Configuration config) {
        int warehouseCapacity;
        int[] bakersConfig;
        int[] deliveryMenConfig;

        warehouseCapacity = config.getWarehouseCapacity();
        bakersConfig = config.getBakers();
        deliveryMenConfig = config.getDeliveryMen();

        queueWithOrders = new QueueWithOrders(this);
        warehouse = new Warehouse(warehouseCapacity, this);

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

        couldFinishBakers = false;
        couldFinishDeliveryMen = false;

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

    public synchronized void increaseNumberOfWaitingBakers() {
        numberOfWaitingBakers++;
    }

    public synchronized void decreaseNumberOfWaitingBakers() {
        numberOfWaitingBakers--;
    }

    public int getNumberOfWaitingBakers() {
        return numberOfWaitingBakers;
    }

    public synchronized void increaseNumberOfWaitingDeliveryMen() {
        numberOfWaitingDeliveryMen++;
    }

    public synchronized void decreaseNumberOfWaitingDeliveryMen() {
        numberOfWaitingDeliveryMen--;
    }

    public int getNumberOfWaitingDeliveryMen() {
        return numberOfWaitingDeliveryMen;
    }

    public boolean couldFinishBakers() {
        return couldFinishBakers;
    }

    public boolean couldFinishDeliveryMen() {
        return couldFinishDeliveryMen;
    }

    /**
     * Closing pizzeria with stopping bakers and deliveryMen after finishing all orders
     * which was in the queue.
     */
    public void shutdown() throws InterruptedException {

        isOpened = false;

        while (numberOfReceivedPizza > this.getNumberOfCompletedPizza()) {
            continue;
        }

        while (bakers.length > this.getNumberOfWaitingBakers()) {
            continue;
        }
        couldFinishBakers = true;
        synchronized (queueWithOrders) {
            queueWithOrders.notifyAll();
        }
        for (Baker baker : bakers) {
            baker.join();
            System.out.println("baker done");
        }

        while (deliveryMen.length > this.getNumberOfWaitingDeliveryMen()) {
            continue;
        }
        couldFinishDeliveryMen = true;
        synchronized (warehouse) {
            warehouse.notifyAll();
        }
        for (DeliveryMan deliveryMan : deliveryMen) {
            deliveryMan.join();
            System.out.println("delivery man done");
        }

        System.out.println("Мы обанкротились! :(");
    }
}
