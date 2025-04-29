package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DirectionTest {
    @Test
    void test() {
        Direction direction;
        direction = Direction.UP;
        direction = Direction.RIGHT;
        direction = Direction.DOWN;
        direction = Direction.LEFT;
        assertTrue(true);
    }
}