package ru.nsu.kozlov;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of queue with orders.
 */
public class QueueWithOrders {
    private final Queue<Order> queue;
    private final Pizzeria pizzeria;

    public QueueWithOrders(Pizzeria pizzeria) {
        this.queue = new LinkedList<>();
        this.pizzeria = pizzeria;
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
                pizzeria.increaseNumberOfWaitingBakers();
                wait();
                pizzeria.decreaseNumberOfWaitingBakers();
                if (pizzeria.couldFinishBakers()) {
                    return null;
                }
            } catch (InterruptedException e) {
                System.out.println("Пекарь прерван!");
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
