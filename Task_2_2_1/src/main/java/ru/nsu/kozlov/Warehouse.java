package ru.nsu.kozlov;

import java.util.Deque;
import java.util.LinkedList;

public class Warehouse {
    private Deque<Order> queue;
    private int currentAmountOfPizzas;
    private int totalAmountOfPizzas;

    public Warehouse(int totalAmountOfPizzas) {
        this.queue = new LinkedList<>();
        this.currentAmountOfPizzas = 0;
        this.totalAmountOfPizzas = totalAmountOfPizzas;
    }

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
        System.out.println("Order " + order.getId() + ". Amount = " + order.getPizzaAmount() + " " +
                order.getOrderState() + " " + MyTimer.getTime());

        queue.offer(order);
        currentAmountOfPizzas += order.getPizzaAmount();
        notify();
    }

    public synchronized Order giveOrderAway(int capacity) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток прерван");
            }
        }

        Order order = queue.pollFirst();

        if (capacity < order.getPizzaAmount()) {
            queue.addFirst(new Order(order.getPizzaAmount() - capacity, order.getId(), order.getOrderState()));
            order.setPizzaAmount(capacity);
        }

        order.setOrderState(OrderState.BEING_DELIVERED);
        System.out.println("Order " + order.getId() + ". Amount = " + order.getPizzaAmount() + " " +
                order.getOrderState() + " " + MyTimer.getTime());

        currentAmountOfPizzas -= order.getPizzaAmount();
        notify();

        return order;
    }
}
