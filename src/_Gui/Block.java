package Refactored_Gui;

import java.awt.Color;
import java.awt.Point;

public class Block {
    protected Point point;
    protected Color color;
    protected static final int BLOCK_SIZE = 24;

    // Constructors
    public Block() {
        point = new Point();
        color = Color.blue;
    }

    public Block(Point p, Color c) {
        point = new Point(p);
        color = c;
    }

    // Getters
    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return BLOCK_SIZE;
    }

    // Setters
    public void setColor(Color c) {
        color = c;
    }
}
