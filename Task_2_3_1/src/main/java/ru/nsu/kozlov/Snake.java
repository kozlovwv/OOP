package ru.nsu.kozlov;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body;
    private Direction direction;

    public Snake() {
        this.direction = Direction.RIGHT;
        this.body = new LinkedList<>();
        this.body.addFirst(new Point(GameConfig.FIELD_WIDTH / 2 - 3,
                                     GameConfig.FIELD_HEIGHT / 2));
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void move(boolean eatFood) {
        Point head = this.getHead();
        Point newHead = switch (this.direction) {
            case RIGHT -> new Point(head.getX() + 1, head.getY());
            case LEFT -> new Point(head.getX() - 1, head.getY());
            case UP -> new Point(head.getX(), head.getY() - 1);
            case DOWN -> new Point(head.getX(), head.getY() + 1);
        };

        body.addFirst(newHead);

        if (!eatFood) {
            body.removeLast();
        }
    }

    public Point getHead() {
        return body.getFirst();
    }
}
