package ru.nsu.kozlov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.LinkedList;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelTest {
    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
    }

    @Test
    void testInitialState() {
        assertFalse(model.isWinner());
        assertFalse(model.isLoser());
        assertFalse(model.isOver());
        assertEquals(GameConfig.FOOD_COUNT, model.getFoods().size());
        assertEquals(1, model.getSnake().getBody().size());
    }

    @Test
    void testUpdateWithoutCollision() {
        Point initialHead = model.getSnake().getHead();
        model.update();

        assertEquals(1, model.getSnake().getBody().size());
        Point newHead = model.getSnake().getHead();
        assertEquals(initialHead.getX() + 1, newHead.getX());
        assertEquals(initialHead.getY(), newHead.getY());
        assertFalse(model.isWinner());
        assertFalse(model.isLoser());
    }

    @Test
    void testWallCollision() {
        for (int i = 0; i < GameConfig.FIELD_WIDTH; i++) {
            model.update();
        }

        assertTrue(model.isLoser());
        assertTrue(model.isOver());
    }

    @Test
    void testSelfCollision() {
        model.getSnake().move(true);
        model.getSnake().move(true);
        model.setSnakeDirection(Direction.LEFT);
        model.update();
        model.setSnakeDirection(Direction.DOWN);
        model.update();
        model.setSnakeDirection(Direction.RIGHT);
        model.update();

        assertTrue(model.isLoser());
    }

    @Test
    void testFoodCollision() {
        Food food = new Food(model.getSnake().getHead().getX() + 1,
                model.getSnake().getHead().getY());
        food.setColor(Color.RED);
        model.getFoods().clear();
        model.getFoods().add(food);

        int initialFoodCount = model.getFoods().size();
        int initialSnakeSize = model.getSnake().getBody().size();
        model.update();

        assertEquals(initialFoodCount, model.getFoods().size());
        assertEquals(initialSnakeSize + 1, model.getSnake().getBody().size());
    }

    @Test
    void testDirectionChanges() {
        model.setSnakeDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, model.getSnakeDirection());

        model.setSnakeDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, model.getSnakeDirection());
    }

    @Test
    void testOppositeDirectionCheck() {
        assertTrue(model.isOppositeDirection(KeyCode.LEFT));
        assertFalse(model.isOppositeDirection(KeyCode.RIGHT));

        model.setSnakeDirection(Direction.UP);
        assertTrue(model.isOppositeDirection(KeyCode.DOWN));
        assertFalse(model.isOppositeDirection(KeyCode.UP));
    }

    @Test
    void testFoodInitialization() {
        LinkedList<Food> foods = model.getFoods();
        LinkedList<Point> snakeBody = model.getSnake().getBody();

        for (Food food : foods) {
            assertFalse(snakeBody.contains(food));
        }

        for (int i = 0; i < foods.size(); i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                assertNotEquals(foods.get(i).getX(), foods.get(j).getX());
                assertNotEquals(foods.get(i).getY(), foods.get(j).getY());
            }
        }
    }
}