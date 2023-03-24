import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //initialize the game
        GameBoard gameBoard = initializeGame();

        //Game loop
        while (true) {
            gameBoard.update();
            gameBoard.repaint();
            Thread.sleep(GameBoard.FRAME_DELAY);
        }
    }

    private static GameBoard initializeGame() {
        JFrame frame = new JFrame("Snake");
        GameBoard gameBoard = new GameBoard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameBoard);
        frame.pack();
        frame.setVisible(true);
        return gameBoard;
    }
}