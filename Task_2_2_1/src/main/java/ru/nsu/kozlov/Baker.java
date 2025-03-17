package ru.nsu.kozlov;

public class Baker extends Thread {
    private final int cookTime;
    private final QueueWithOrders queueWithOrders;
    private final Warehouse warehouse;
    private Order order;

    public Baker(QueueWithOrders queueWithOrders, Warehouse warehouse, int cookTime) {
        this.queueWithOrders = queueWithOrders;
        this.cookTime = cookTime;
        this.warehouse = warehouse;
        this.order = null;
    }

    @Override
    public void run() {
        while (true) {
            order = queueWithOrders.giveOrderAway();

            if (order == null) {
                break;
            }

            try {
                Thread.sleep((1000L * cookTime) * order.getPizzaAmount());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            warehouse.takeOrder(order);
            order = null;
        }
    }
}
