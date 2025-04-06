package ru.nsu.kozlov;

/**
 * Point of the game field.
 */
public class Point {
    private final int xCoor;
    private final int yCoor;

    public Point(int x, int y) {
        this.xCoor = x;
        this.yCoor = y;
    }

    public int getX() {
        return xCoor;
    }

    public int getY() {
        return yCoor;
    }
}
