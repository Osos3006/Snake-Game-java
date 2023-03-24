import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private static final int INITIAL_LENGTH = 3;
    private final ArrayList<Point> body;
    private Direction direction;

    public Snake(Point head) {
        body = new ArrayList<>();
        for (int i = 0; i < INITIAL_LENGTH; i++) {
            body.add(new Point(head.x - i, head.y));
        }
        direction = Direction.RIGHT;
    }

    public void move() {
        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        if (newHead.x < 0) {
            newHead.x = GameBoard.BOARD_WIDTH - 1;
        } else if (newHead.x >= GameBoard.BOARD_WIDTH) {
            newHead.x = 0;
        }
        if (newHead.y < 0) {
            newHead.y = GameBoard.BOARD_HEIGHT - 1;
        } else if (newHead.y >= GameBoard.BOARD_HEIGHT) {
            newHead.y = 0;
        }
        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public boolean checkCollision() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            Point bodyPart = body.get(i);
            if (head.equals(bodyPart)) {
                return true;
            }
        }
        return false;
    }

    public Point checkFoodCollisions(ArrayList<Point> foodPositions) {
        Point head = body.get(0);
        for (Point food : foodPositions) {
            if (head.equals(food)) {
                return food;
            }
        }
        return null;
    }
    public boolean checkFoodCollision(Point foodPosition) {
        Point head = body.get(0);
        return head.getLocation().equals(foodPosition);
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail));
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }

    private Point getHead() {
        return body.get(0);
    }
    public ArrayList<Point> getBody() {
        return body;
    }
}

