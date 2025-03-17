package ru.nsu.kozlov;

public class Order {
    private int pizzaAmount;
    private final int id;
    private OrderState orderState;
    private static int currentMaxID = 0;

    public Order(int pizzaAmount) {
        this.pizzaAmount = pizzaAmount;
        this.orderState = OrderState.IN_QUEUE;
        this.id = currentMaxID++;
    }

    public Order(int pizzaAmount, int id, OrderState state) {
        this.pizzaAmount = pizzaAmount;
        this.orderState = state;
        this.id = id;
    }

    public int getPizzaAmount() {
        return pizzaAmount;
    }

    public void setPizzaAmount(int pizzaAmount) {
        this.pizzaAmount = pizzaAmount;
    }

    public int getId() {
        return id;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public static void setZeroID() {
        currentMaxID = 0;
    }
}
