package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;
    private boolean gameOver;
    private int score;
    
    public Game() {
        StdDraw.enableDoubleBuffering();
        this.snake = new Snake();
        this.food = new Food();
        this.gameOver = false;
        this.score = 0;
        
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.clear(Color.BLACK);
    }
    
    private void updateGame() {
        int speed = 100 - (score / 50) * 10; 
        speed = Math.max(speed, 30); 
        StdDraw.pause(speed);
    }
    public void play() {
        while (!gameOver) {
            int dir = getKeypress();
            
            if (dir != -1) {
                snake.changeDirection(dir);
            }
            
            snake.move();
            
            if (snake.eatFood(food)) {
                score += 10;
                food.respawn();
            }
            
            if (!snake.isInbounds()) {
                gameOver = true;
            }
            
            updateDrawing();
            
            StdDraw.pause(100);
        }
        
        displayGameOver();
    }
    
    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }
    
    private void updateDrawing() {
        StdDraw.clear(Color.BLACK);
        
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.1, 0.95, "Score: " + score);
        
        snake.draw();
        food.draw();
        
        StdDraw.show();
    }
    
    private void displayGameOver() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(0.5, 0.6, "GAME OVER");
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.5, 0.5, "Final Score: " + score);
        StdDraw.text(0.5, 0.4, "Press any key to exit");
        StdDraw.show();
        
        // Wait for key press before exiting
        while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            StdDraw.pause(100);
        }
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}