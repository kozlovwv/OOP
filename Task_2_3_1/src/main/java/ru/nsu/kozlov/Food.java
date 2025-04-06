package ru.nsu.kozlov;

import javafx.scene.paint.Color;

public class Food extends Point {

    private Color color;
    private int points;

    public Food(int x, int y) {
        super(x, y);
    }

    public Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }
}
