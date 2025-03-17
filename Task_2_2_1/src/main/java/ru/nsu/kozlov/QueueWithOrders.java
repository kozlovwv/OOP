package ru.nsu.kozlov;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of queue with orders.
 */
public class QueueWithOrders {
    private final Queue<Order> queue;

    public QueueWithOrders() {
        this.queue = new LinkedList<>();
    }

    /**
     * Taking order to the queue.
     *
     * @param order new order.
     */
    public synchronized void takeOrder(Order order) {
        order.setOrderState(OrderState.IN_QUEUE);
        System.out.println("Order " + order.getId() + ". Amount = "
                + order.getPizzaAmount() + " "
                + order.getOrderState() + " " + MyTimer.getTime());

        queue.offer(order);
        notify();
    }

    /**
     * Giving order to baker.
     *
     * @return order.
     */
    public synchronized Order giveOrderAway() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Пекарь закончил работу!");
                return null;
            }
        }
        Order order = queue.poll();

        order.setOrderState(OrderState.BEING_PREPARED);
        System.out.println("Order " + order.getId() + ". Amount = "
                + order.getPizzaAmount() + " "
                + order.getOrderState() + " " + MyTimer.getTime());

        return order;
    }
}
