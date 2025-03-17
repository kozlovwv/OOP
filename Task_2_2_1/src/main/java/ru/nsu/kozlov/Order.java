package ru.nsu.kozlov;

/**
 * Class Order implements the order in pizzeria with state, id and amount of pizza.
 */
public class Order {
    private int pizzaAmount;
    private final int id;
    private OrderState orderState;
    private static int currentMaxID = 0;

    /**
     * Order's constructor.
     * @param pizzaAmount amount of pizza in the order.
     */
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

    public static void setZeroId() {
        currentMaxID = 0;
    }
}
