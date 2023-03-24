import java.awt.Point;
import java.util.Random;

public class Food {
    private Point position;

    public Food() {
        position = new Point(0, 0);
    }

    public void spawn(int boardWidth, int boardHeight) {
        Random random = new Random();
        int x = random.nextInt(boardWidth);
        int y = random.nextInt(boardHeight);
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
