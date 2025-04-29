package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PointTest {
    @Test
    void test() {
        Point point = new Point(1, 2);
        int coordinateX = point.getX();
        int coordinateY = point.getY();
        assertEquals(1, coordinateX);
        assertEquals(2, coordinateY);
    }
}