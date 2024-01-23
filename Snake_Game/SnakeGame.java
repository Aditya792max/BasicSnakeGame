import java.util.Scanner;

public class SnakeGame {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final char EMPTY = ' ';
    private static final char SNAKE_BODY = 'O';
    private static final char FOOD = 'X';

    private static char[][] board;
    private static int[] snakeX, snakeY;
    private static int snakeLength;
    private static int foodX, foodY;
    private static boolean isGameOver;
    private static Direction direction;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static void main(String[] args) {
        initializeGame();
        while (!isGameOver) {
            printBoard();
            processInput();
            move();
            checkCollision();
            checkFood();
            clearScreen();
            sleep(100); // Adjust the sleep time to control the speed of the game
        }
        System.out.println("Game Over! Your score: " + (snakeLength - 1));
    }

    private static void initializeGame() {
        board = new char[HEIGHT][WIDTH];
        snakeX = new int[WIDTH * HEIGHT];
        snakeY = new int[WIDTH * HEIGHT];
        snakeLength = 1;
        isGameOver = false;

        // Initialize the board
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = EMPTY;
            }
        }

        // Initialize the snake at the center of the board
        snakeX[0] = WIDTH / 2;
        snakeY[0] = HEIGHT / 2;
        board[snakeY[0]][snakeX[0]] = SNAKE_BODY;

        // Place the initial food
        placeFood();

        // Set the initial direction
        direction = Direction.RIGHT;
    }

    private static void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void processInput() {
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);

        switch (input) {
            case 'w':
                direction = Direction.UP;
                break;
            case 's':
                direction = Direction.DOWN;
                break;
            case 'a':
                direction = Direction.LEFT;
                break;
            case 'd':
                direction = Direction.RIGHT;
                break;
        }
    }

    private static void move() {
        // Move the snake body
        for (int i = snakeLength - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        // Move the snake head based on the current direction
        switch (direction) {
            case UP:
                snakeY[0]--;
                break;
            case DOWN:
                snakeY[0]++;
                break;
            case LEFT:
                snakeX[0]--;
                break;
            case RIGHT:
                snakeX[0]++;
                break;
        }

        // Check if the snake goes out of bounds (wrap around)
        if (snakeX[0] >= WIDTH) snakeX[0] = 0;
        if (snakeX[0] < 0) snakeX[0] = WIDTH - 1;
        if (snakeY[0] >= HEIGHT) snakeY[0] = 0;
        if (snakeY[0] < 0) snakeY[0] = HEIGHT - 1;
    }

    private static void checkCollision() {
        // Check if the snake collides with itself
        for (int i = 1; i < snakeLength; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                isGameOver = true;
                return;
            }
        }
    }

    private static void checkFood() {
        // Check if the snake eats the food
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            // Increase the snake length
            snakeLength++;
            // Place a new food item
            placeFood();
        }

        // Update the board with the snake and food positions
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = EMPTY;
            }
        }

        for (int i = 0; i < snakeLength; i++) {
            board[snakeY[i]][snakeX[i]] = SNAKE_BODY;
        }

        board[foodY][foodX] = FOOD;
    }

    private static void placeFood() {
        // Place the food at a random location
        foodX = (int) (Math.random() * WIDTH);
        foodY = (int) (Math.random() * HEIGHT);

        // Ensure the food is not placed on the snake
        while (board[foodY][foodX] == SNAKE_BODY) {
            foodX = (int) (Math.random() * WIDTH);
            foodY = (int) (Math.random() * HEIGHT);
        }
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
