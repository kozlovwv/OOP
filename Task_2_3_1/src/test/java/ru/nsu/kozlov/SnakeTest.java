package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnakeTest {
    private Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake();
    }

    @Test
    void testInitialState() {
        assertEquals(Direction.RIGHT, snake.getDirection());
        assertEquals(1, snake.getBody().size());

        Point head = snake.getHead();
        assertEquals(GameConfig.FIELD_WIDTH / 2 - 3, head.getX());
        assertEquals(GameConfig.FIELD_HEIGHT / 2, head.getY());
    }

    @Test
    void testSetDirection() {
        snake.setDirection(Direction.UP);
        assertEquals(Direction.UP, snake.getDirection());

        snake.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, snake.getDirection());
    }

    @Test
    void testMoveWithoutEating() {
        Point initialHead = snake.getHead();
        snake.move(false);

        assertEquals(1, snake.getBody().size());
        Point newHead = snake.getHead();
        assertEquals(initialHead.getX() + 1, newHead.getX());
        assertEquals(initialHead.getY(), newHead.getY());
    }

    @Test
    void testMoveWithEating() {
        Point initialHead = snake.getHead();
        snake.move(true);

        assertEquals(2, snake.getBody().size());
        Point newHead = snake.getHead();
        assertEquals(initialHead.getX() + 1, newHead.getX());
        assertEquals(initialHead.getY(), newHead.getY());
    }

    @Test
    void testMultipleMoves() {
        snake.move(false);
        snake.move(false);
        snake.move(false);

        assertEquals(1, snake.getBody().size());
        assertEquals(snake.getHead().getX(), GameConfig.FIELD_WIDTH / 2 - 3 + 3);
    }

    @Test
    void testDirectionChanges() {
        snake.setDirection(Direction.DOWN);
        snake.move(false);

        Point head = snake.getHead();
        assertEquals(GameConfig.FIELD_WIDTH / 2 - 3, head.getX());
        assertEquals(GameConfig.FIELD_HEIGHT / 2 + 1, head.getY());
    }
}