package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertSame;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

class FoodTest {
    @Test
    void test() {
        Food food = new Food(1, 2);
        food.setColor(Color.RED);
        assertSame(food.getColor(), Color.RED);
    }
}