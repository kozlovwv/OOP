package ru.nsu.kozlov;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implementation of warehouse.
 */
public class Warehouse {
    private final Deque<Order> queue;
    private int currentAmountOfPizzas;
    private final int totalAmountOfPizzas;

    /**
     * Warehouse's constructor
     *
     * @param totalAmountOfPizzas capacity of warehouse.
     */
    public Warehouse(int totalAmountOfPizzas) {
        this.queue = new LinkedList<>();
        this.currentAmountOfPizzas = 0;
        this.totalAmountOfPizzas = totalAmountOfPizzas;
    }

    /**
     * Taking order in warehouse if it has enough space.
     *
     * @param order order.
     */
    public synchronized void takeOrder(Order order) {
        while (currentAmountOfPizzas + order.getPizzaAmount() > totalAmountOfPizzas) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток прерван");
            }
        }

        order.setOrderState(OrderState.IN_WAREHOUSE);
        System.out.println("Order " + order.getId() + ". Amount = "
                + order.getPizzaAmount() + " "
                + order.getOrderState() + " " + MyTimer.getTime());

        queue.offer(order);
        currentAmountOfPizzas += order.getPizzaAmount();
        notify();
    }

    /**
     * Taking order (or part of it) from warehouse.
     *
     * @param capacity capacity of delivery man's bag.
     * @return order.
     */
    public synchronized Order giveOrderAway(int capacity) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Курьер закончил работу! ");
                return null;
            }
        }

        Order order = queue.pollFirst();

        if (capacity < order.getPizzaAmount()) {
            queue.addFirst(new Order(order.getPizzaAmount() - capacity,
                    order.getId(), order.getOrderState()));
            order.setPizzaAmount(capacity);
        }

        order.setOrderState(OrderState.BEING_DELIVERED);
        System.out.println("Order " + order.getId() + ". Amount = "
                + order.getPizzaAmount() + " "
                + order.getOrderState() + " " + MyTimer.getTime());

        currentAmountOfPizzas -= order.getPizzaAmount();
        notify();

        return order;
    }
}
