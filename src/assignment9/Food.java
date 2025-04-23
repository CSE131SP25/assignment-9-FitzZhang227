package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class Food {
    public static final double FOOD_SIZE = 0.02;
    private double x, y;
    
    /**
     * Creates a new Food at a random location within the game boundaries
     * (assuming game area is 0-1 in both x and y coordinates)
     */
    public Food() {
        // Generate random position between 0.1 and 0.9 to avoid spawning too close to edges
        this.x = 0.1 + Math.random() * 0.8;
        this.y = 0.1 + Math.random() * 0.8;
    }
    
    /**
     * Draws the Food as a red circle
     */
    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(this.x, this.y, FOOD_SIZE/2);
    }
    
    // Getter methods for position
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    // Method to respawn food at new random location
    public void respawn() {
        this.x = 0.1 + Math.random() * 0.8;
        this.y = 0.1 + Math.random() * 0.8;
    }
}