package ru.nsu.kozlov;

/**
 * Point of the game field.
 */
public class Point {
    private final int coordinateX;
    private final int coordinateY;

    public Point(int x, int y) {
        this.coordinateX = x;
        this.coordinateY = y;
    }

    public int getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }
}
