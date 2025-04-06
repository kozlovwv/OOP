package ru.nsu.kozlov;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of rendering view.
 */
public class View {
    GraphicsContext gc;

    public View(GraphicsContext gc) {
        this.gc = gc;
    }

    void update(Snake snake, LinkedList<Food> foods, boolean winner, boolean loser, int maxScore) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0,
                0,
                GameConfig.FIELD_WIDTH * GameConfig.CELL_SIZE,
                GameConfig.FIELD_HEIGHT * GameConfig.CELL_SIZE);

        if (winner || loser) {
            if (winner) {
                gc.setFill(Color.GREEN);
                gc.fillText("WINNER!",
                        (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 10,
                        (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE);
            } else {
                gc.setFill(Color.RED);
                gc.fillText("LOSER!",
                        (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 10,
                        (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE);
            }

            gc.setFill(Color.WHITE);
            gc.fillText("Press R to play again!",
                    (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 60,
                    (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE + 25);
            gc.fillText("Press Q to quit the game..",
                    (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 70,
                    (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE + 50);
            gc.fillText("Your score " + String.valueOf(snake.getBody().size()),
                    (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 30,
                    (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE + 75);
            gc.fillText("Best score " + String.valueOf(maxScore),
                    (GameConfig.FIELD_WIDTH / 2) * GameConfig.CELL_SIZE - 30,
                    (GameConfig.FIELD_HEIGHT / 2) * GameConfig.CELL_SIZE + 100);

            return;
        }

        gc.setFill(Color.GREEN);

        LinkedList<Point> body = snake.getBody();
        for (Point point : body) {
            gc.fillRect(point.getX() * GameConfig.CELL_SIZE,
                        point.getY() * GameConfig.CELL_SIZE,
                        GameConfig.CELL_SIZE,
                        GameConfig.CELL_SIZE);
        }

        for (Food food : foods) {
            gc.setFill(food.getColor());
            gc.fillRect(food.getX() * GameConfig.CELL_SIZE,
                        food.getY() * GameConfig.CELL_SIZE,
                        GameConfig.CELL_SIZE,
                        GameConfig.CELL_SIZE);
        }
    }
}
