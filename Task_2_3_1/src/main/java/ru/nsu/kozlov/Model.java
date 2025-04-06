package ru.nsu.kozlov;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;

/**
 * Implementation of model.
 */
public class Model {
    private Snake snake;
    private LinkedList<Food> foods;
    private boolean winner;
    private boolean loser;
    private Random random;

    /**
     * model's constructor.
     */
    public Model() {
        this.winner = false;
        this.loser = false;
        this.snake = new Snake();
        this.foods = new LinkedList<>();
        this.random = new Random();
        this.initFood();
    }

    /**
     * updating model.
     */
    public void update() {

        if (loser || winner) {
            return;
        }

        Point head = snake.getHead();
        Food movedHead;
        Food eatenFood = null;
        boolean foodCollision = false;
        LinkedList<Point> snakeBody = snake.getBody();

        movedHead = switch (snake.getDirection()) {
            case RIGHT -> new Food(head.getX() + 1, head.getY());
            case LEFT -> new Food(head.getX() - 1, head.getY());
            case UP -> new Food(head.getX(), head.getY() - 1);
            case DOWN -> new Food(head.getX(), head.getY() + 1);
        };

        if (movedHead.getX() < 0 || movedHead.getX() >= GameConfig.FIELD_WIDTH
                || movedHead.getY() < 0 || movedHead.getY() >= GameConfig.FIELD_HEIGHT) {
            loser = true;
            return;
        }

        for (Point point : snakeBody) {
            if (movedHead.getX() == point.getX() && movedHead.getY() == point.getY()) {
                loser = true;
                return;
            }
        }

        for (Food food : foods) {
            if (movedHead.getX() == food.getX() && movedHead.getY() == food.getY()) {
                eatenFood = food;
                foodCollision = true;
                break;
            }
        }

        if (foodCollision) {
            foods.remove(eatenFood);

            Food newFood = new Food(random.nextInt(GameConfig.FIELD_WIDTH),
                                    random.nextInt(GameConfig.FIELD_HEIGHT));

            while ((newFood.getX() == eatenFood.getX() && newFood.getY() == eatenFood.getY())
                    || (snakeBody.contains((Point) newFood))) {
                newFood = new Food(random.nextInt(GameConfig.FIELD_WIDTH),
                                    random.nextInt(GameConfig.FIELD_HEIGHT));
            }

            newFood.setColor(Color.RED);

            foods.addFirst(newFood);
        }

        snake.move(foodCollision);

        if (snake.getBody().size() == GameConfig.WIN_LENGTH) {
            winner = true;
        }
    }

    public LinkedList<Food> getFoods() {
        return foods;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean isLoser() {
        return loser;
    }

    public void setSnakeDirection(Direction direction) {
        this.snake.setDirection(direction);
    }

    public Direction getSnakeDirection() {
        return this.snake.getDirection();
    }

    /**
     * checking for opposite direction.
     * @param keyCode code of pressed key.
     * @return true if direction is opposite, false otherwise.
     */
    public boolean isOppositeDirection(KeyCode keyCode) {
        return switch (keyCode) {
            case W, UP -> this.getSnakeDirection() == Direction.DOWN;
            case A, LEFT -> this.getSnakeDirection() == Direction.RIGHT;
            case S, DOWN -> this.getSnakeDirection() == Direction.UP;
            case D, RIGHT -> this.getSnakeDirection() == Direction.LEFT;
            default -> false;
        };
    }

    private void initFood() {
        LinkedList<Point> snakeBody = snake.getBody();
        for (int i = 0; i < GameConfig.FOOD_COUNT; i++) {
            Food newFood = new Food(random.nextInt(GameConfig.FIELD_WIDTH),
                    random.nextInt(GameConfig.FIELD_HEIGHT));

            while ((foods.contains(newFood)) || (snakeBody.contains((Point) newFood))) {
                newFood = new Food(random.nextInt(GameConfig.FIELD_WIDTH),
                        random.nextInt(GameConfig.FIELD_HEIGHT));
            }

            newFood.setColor(Color.RED);

            foods.addFirst(newFood);
        }
    }

    public boolean isOver() {
        return (winner || loser);
    }
}
