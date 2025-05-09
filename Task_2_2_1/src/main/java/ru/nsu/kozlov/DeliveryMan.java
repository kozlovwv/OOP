package ru.nsu.kozlov;

/**
 * Implementation of delivery man.
 */
public class DeliveryMan extends Thread {
    private final int deliveryTime;
    private final int capacity;
    private final Warehouse warehouse;
    private final Pizzeria pizzeria;
    private Order order;

    /**
     * Delivery man constructor.
     *
     * @param warehouse warehouse where delivery man will take cooked pizzas.
     * @param capacity capacity of the delivery man's bag.
     * @param pizzeria pizzeria where he works.
     */
    public DeliveryMan(Warehouse warehouse, int capacity, Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
        this.warehouse = warehouse;
        this.capacity = capacity;
        this.deliveryTime = 1;
        this.order = null;
    }

    @Override
    public void run() {
        while (true) {
            order = warehouse.giveOrderAway(capacity);

            if (order == null) {
                break;
            }

            try {
                Thread.sleep(1000L * deliveryTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            order.setOrderState(OrderState.DELIVERED);
            System.out.println("Order " + order.getId() + ". Amount = "
                    + order.getPizzaAmount() + " "
                    + order.getOrderState() + " " + MyTimer.getTime());
            pizzeria.increaseCompletedPizza(order.getPizzaAmount());
            order = null;
        }
    }
}
