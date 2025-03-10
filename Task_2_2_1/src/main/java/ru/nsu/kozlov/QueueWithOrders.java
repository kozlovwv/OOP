package ru.nsu.kozlov;

import java.util.LinkedList;
import java.util.Queue;

public class QueueWithOrders {
    private Queue<Order> queue;

    public QueueWithOrders() {
        this.queue = new LinkedList<>();
    }

    public synchronized void takeOrder(Order order) {
        order.setOrderState(OrderState.IN_QUEUE);
        System.out.println("Order " + order.getId() + ". Amount = " + order.getPizzaAmount() + " " +
                order.getOrderState() + " " + MyTimer.getTime());

        queue.offer(order);
        notify();
    }

    public synchronized Order giveOrderAway() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток прерван");
            }
        }
        Order order = queue.poll();

        order.setOrderState(OrderState.BEING_PREPARED);
        System.out.println("Order " + order.getId() + ". Amount = " + order.getPizzaAmount() + " " +
                order.getOrderState() + " " + MyTimer.getTime());

        return order;
    }
}
