import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel implements KeyListener {
    public static final int CELL_SIZE = 20;
    public static final int BOARD_WIDTH = 50;
    public static final int BOARD_HEIGHT = 50;
    public static final int FRAME_DELAY = 100;

    private static final int NUMBER_OF_APPLES = 2;

    private final Random random;

    private final Snake snake;
    private ArrayList<Point> foodPositions; //for multiple food positions
    private boolean gameOver;
    private final Drawer drawer;

    public GameBoard() {

        setPreferredSize(new java.awt.Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        drawer = new Drawer();
        random = new Random();
        foodPositions = new ArrayList<Point>();
        snake = new Snake(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));
        for (int i = 0; i < NUMBER_OF_APPLES; i++) {
            spawnFood();
        }
        gameOver = false;


    }

    private void spawnFood() {
        ArrayList<Point> newFoodPositions = new ArrayList<>();
        for (Point food : foodPositions) {
            if (!snake.checkFoodCollision(food)) {
                newFoodPositions.add(food);
            }
        }
        foodPositions = newFoodPositions;

        while (foodPositions.size() < NUMBER_OF_APPLES) {
            int x = random.nextInt(BOARD_WIDTH-10);
            int y = random.nextInt(BOARD_HEIGHT-10);
            Point food = new Point(x, y);
            if (!snake.checkFoodCollision(food) && !foodPositions.contains(food)) {
                foodPositions.add(food);
            }
        }
        System.out.println("new food positions");
        for (Point food : foodPositions)
        {
            System.out.println(food.getLocation());
        }
    }

    protected void update() {

        if (!gameOver) {
            snake.move();
            Point collidedFood = snake.checkFoodCollisions(foodPositions);
            if (collidedFood != null) {
                snake.grow();
                foodPositions.remove(collidedFood);
                spawnFood();
            } else if (snake.checkCollision()) {
                gameOver = true;
            }
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer.drawSnake(g, snake);
        drawer.drawFood(g, foodPositions);
        if (gameOver) {
            drawer.drawGameOver(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (keyCode == KeyEvent.VK_UP) {
            snake.setDirection(Direction.UP);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
