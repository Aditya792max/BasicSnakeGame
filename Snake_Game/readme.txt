Snake Game in Java

Overview:
This is a simple console-based implementation of the classic Snake Game in Java. The game allows the player to control a snake moving around a two-dimensional grid, eating food to grow longer. The game ends if the snake collides with itself or with the boundaries of the grid.

Features:
Game Board: The game is played on a grid, displayed in the console.
Snake: The player controls a snake that moves in the specified direction (up, down, left, or right).
Food: Food items appear randomly on the grid. Eating food increases the snake's length and the player's score.
Scoring: The player's score is based on the length of the snake.
Game Over: The game ends if the snake collides with itself or the grid boundaries.
How to Play
Run the Game:

Compile and run the SnakeGame class in a Java environment.
Controls:

Use the following keys to control the snake:
w: Move the snake up
s: Move the snake down
a: Move the snake left
d: Move the snake right
Game Loop:

The game runs in a loop until the snake collides, displaying the current state of the game board in the console.
Game Over:

When the game ends, a "Game Over" message is displayed along with the player's final score.
Customizations.
Speed Adjustment:
Adjust the sleep time in the sleep method to control the speed of the game (sleep(100)).
Technical Details
Java Version:

This game is implemented in Java, using standard Java features and console-based input/output.
Multidimensional Arrays:

Game state and coordinates are managed using Java arrays.
Enums:

An enumeration is used to represent the possible directions in which the snake can move.
Author
Aditya Vikram Kirtania