package ru.nsu.kozlov;

public class DeliveryMan extends Thread {
    private int deliveryTime;
    private int capacity;
    private Warehouse warehouse;
    private Order order;

    public DeliveryMan(Warehouse warehouse, int capacity, int deliveryTime) {
        this.warehouse = warehouse;
        this.capacity = capacity;
        this.deliveryTime = deliveryTime;
        this.order = null;
    }

    @Override
    public void run() {
        while (true) {
            order = warehouse.giveOrderAway(capacity);

            try {
                Thread.sleep(1000L * deliveryTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            order.setOrderState(OrderState.DELIVERED);
            System.out.println("Order " + order.getId() + ". Amount = " + order.getPizzaAmount() + " " +
                    order.getOrderState() + " " + MyTimer.getTime());
            order = null;
        }
    }
}
