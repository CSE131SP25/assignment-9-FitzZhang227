package assignment9;

import java.util.LinkedList;

public class Snake {
    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;
    
    public Snake() {
        // Initialize the segments list
        segments = new LinkedList<BodySegment>();
        // Create initial head segment at center of screen
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = MOVEMENT_SIZE; // Start moving right
        deltaY = 0;
    }
    
    public void changeDirection(int direction) {
        // Prevent 180-degree turns that would make snake collide with itself
        BodySegment head = segments.getFirst();
        BodySegment next = segments.size() > 1 ? segments.get(1) : null;
        
        if (direction == 1 && !(next != null && next.getY() > head.getY())) { // up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2 && !(next != null && next.getY() < head.getY())) { // down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3 && !(next != null && next.getX() < head.getX())) { // left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4 && !(next != null && next.getX() > head.getX())) { // right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }
    
    /**
     * Moves the snake by updating the position of each segment
     */
    public void move() {
        // Store previous positions for body segments
        LinkedList<Double> prevX = new LinkedList<>();
        LinkedList<Double> prevY = new LinkedList<>();
        for (BodySegment segment : segments) {
            prevX.add(segment.getX());
            prevY.add(segment.getY());
        }
        
        // Move head first
        BodySegment head = segments.getFirst();
        head.setX(head.getX() + deltaX);
        head.setY(head.getY() + deltaY);
        
        // Move body segments to follow previous segment's position
        for (int i = 1; i < segments.size(); i++) {
            BodySegment current = segments.get(i);
            current.setX(prevX.get(i-1));
            current.setY(prevY.get(i-1));
        }
    }
    
    
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }
    
    /**
     * The snake attempts to eat the given food, growing if successful
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        // Calculate distance between head and food
        double distance = Math.sqrt(
            Math.pow(head.getX() - f.getX(), 2) + 
            Math.pow(head.getY() - f.getY(), 2));
        
        // If distance is less than sum of radii, they're colliding
        if (distance < (SEGMENT_SIZE/2 + Food.FOOD_SIZE/2)) {
            // Add new segment at tail position
            BodySegment tail = segments.getLast();
            segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if the head of the snake is in bounds
     * @return whether the head is in the bounds of the window (0-1 coordinate system)
     */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double radius = SEGMENT_SIZE/2;
        return head.getX() >= radius && head.getX() <= 1 - radius &&
               head.getY() >= radius && head.getY() <= 1 - radius;
    }
    
    // Additional method for self-collision detection (bonus feature)
    public boolean checkSelfCollision() {
        if (segments.size() < 4) return false;
        
        BodySegment head = segments.getFirst();
        for (int i = 3; i < segments.size(); i++) {
            BodySegment segment = segments.get(i);
            double distance = Math.sqrt(
                Math.pow(head.getX() - segment.getX(), 2) + 
                Math.pow(head.getY() - segment.getY(), 2));
            if (distance < SEGMENT_SIZE) {
                return true;
            }
        }
        return false;
    }
}