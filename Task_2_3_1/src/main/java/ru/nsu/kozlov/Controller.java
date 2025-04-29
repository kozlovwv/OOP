package ru.nsu.kozlov;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of controller.
 */
public class Controller {
    @FXML
    private Canvas gameCanvas;

    private Model model;
    private View view;
    private long lastUpdate = 0;
    private boolean moved = false;
    private boolean isOver;
    private int maxScore = 0;

    /**
     * initializing controller and making game loop with using timer.
     */
    @FXML
    public void initialize() {

        gameCanvas.setHeight(GameConfig.FIELD_HEIGHT * GameConfig.CELL_SIZE);
        gameCanvas.setWidth(GameConfig.FIELD_WIDTH * GameConfig.CELL_SIZE);

        model = new Model();
        isOver = false;
        view = new View(gameCanvas.getGraphicsContext2D());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= GameConfig.GAME_SPEED * 1_000_000) {
                    model.update();
                    view.update(
                            model.getSnake(),
                            model.getFoods(),
                            model.isWinner(),
                            model.isLoser(),
                            maxScore
                    );
                    lastUpdate = now;
                    moved = false;
                    isOver = model.isOver();
                    if (model.getSnake().getBody().size() > maxScore) {
                        maxScore = model.getSnake().getBody().size();
                    }
                }
            }
        };

        timer.start();
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        if (isOver) {
            if (keyCode == KeyCode.R) {
                model = new Model();
                isOver = false;
                return;
            } else if (keyCode == KeyCode.Q) {
                Platform.exit();
            }
        }

        if (!model.isOppositeDirection(keyCode) && !moved) {
            switch (event.getCode()) {
                case W, UP: model.setSnakeDirection(Direction.UP);
                break;
                case A, LEFT: model.setSnakeDirection(Direction.LEFT);
                break;
                case S, DOWN: model.setSnakeDirection(Direction.DOWN);
                break;
                case D, RIGHT: model.setSnakeDirection(Direction.RIGHT);
                break;
                default: break;
            }
            moved = true;
        }
    }
}
