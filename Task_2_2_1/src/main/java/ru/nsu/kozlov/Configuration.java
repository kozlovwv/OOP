package ru.nsu.kozlov;

/**
 * JSON configuration.
 */
public class Configuration {
    private int warehouseCapacity;
    private int[] bakers;
    private int[] deliveryMen;

    public int getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public int[] getBakers() {
        return bakers;
    }

    public int[] getDeliveryMen() {
        return deliveryMen;
    }
}
