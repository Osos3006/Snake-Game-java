import java.awt.*;
import java.util.ArrayList;

public class Drawer {

    public void drawSnake(Graphics g, Snake snake) {
        ArrayList<Point> body = snake.getBody();
        g.setColor(Color.BLACK);
        for (Point part : body) {
            g.fillRect(part.x * GameBoard.CELL_SIZE, part.y * GameBoard.CELL_SIZE, GameBoard.CELL_SIZE,
                    GameBoard.CELL_SIZE);
        }
    }

    public void drawFood(Graphics g, ArrayList<Point> foodPositions) {
        g.setColor(Color.RED);
        for (Point foodPosition : foodPositions) {
            g.fillRect(foodPosition.x * GameBoard.CELL_SIZE, foodPosition.y * GameBoard.CELL_SIZE, GameBoard.CELL_SIZE, GameBoard.CELL_SIZE);
        }
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Game Over", GameBoard.BOARD_WIDTH * GameBoard.CELL_SIZE / 2 - 30,
                GameBoard.BOARD_HEIGHT * GameBoard.CELL_SIZE / 2);
    }
}
