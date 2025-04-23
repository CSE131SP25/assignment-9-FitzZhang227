package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {
    private double x, y, size;
    private Color color;
    
    /**
     * Constructor for BodySegment
     * @param x The x-coordinate of the segment
     * @param y The y-coordinate of the segment
     * @param size The size/diameter of the segment
     */
    public BodySegment(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.size = size;
        // Using a green color from ColorUtils or custom color
        this.color = ColorUtils.solidColor(); // or new Color(0, 128, 0)
    }
    
    /**
     * Draws the segment as a filled circle
     */
    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.filledCircle(this.x, this.y, this.size/2);
    }
    
    // Getter methods for position and size
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getSize() {
        return this.size;
    }
    
    // Setter methods for position
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
}