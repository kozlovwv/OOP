package ru.nsu.kozlov;

public class Pizzeria {
    private Baker[] bakers;
    private DeliveryMan[] deliveryMen;
    private QueueWithOrders queueWithOrders;
    private Warehouse warehouse;
    private boolean isOpened;

    public Pizzeria(int bakersNumber, int deliveryMenNumber) {
        queueWithOrders = new QueueWithOrders();
        warehouse = new Warehouse(30);

        bakers = new Baker[bakersNumber];
        for (Baker baker: bakers) {
            baker = new Baker(queueWithOrders, warehouse, 3);
            baker.start();
        }

        deliveryMen = new DeliveryMan[deliveryMenNumber];
        for (DeliveryMan deliveryMan: deliveryMen) {
            deliveryMan = new DeliveryMan(warehouse, 10, 1);
            deliveryMan.start();
        }

        isOpened = true;
    }

    public void open() {
        isOpened = true;
        System.out.println("Pizzeria is opened: " + MyTimer.getTime());
    }

    public void close() {
        isOpened = false;
        System.out.println("Pizzeria is CLOSED: " + MyTimer.getTime());
    }

    public void addOrder(Order order) {
        if (isOpened) {
            queueWithOrders.takeOrder(order);
        } else {
            System.out.println("Order " + order.getId() + " ignored." + " " + MyTimer.getTime());
        }
    }
}
