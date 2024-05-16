package Refactored_PacObject;

import java.awt.Color;
import java.awt.Point;
import Refactored_Constantes.Constantes;
import Refactored_Constantes.MapConstants;
import Refactored_Utilities.Direction;
import Refactored_Utilities.State;
import Refactored_Utilities.Cloner;

public abstract class AbstractCharacter {
    protected Point point; // position on the map
    protected Movement movement = new Movement(); // contains current and next position
    protected State state; // state of the object (normal, super, weak)
    protected int velocity = Constantes.STD_VELOCITY; // speed of the character
    protected Point START; // starting point on the map
    protected Color color; // current color of the character
    protected Color baseColor; // base color
    int unit = 0; // it defines the duration of an effect

    public abstract void move(int index);

    public abstract void manage();

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }

    public void checkToChangeDirection(int index) {
        if (movement.getNext() != Direction.NONE) {
            tryChangeDirection(index);
        }
    }

    private void tryChangeDirection(int index) {
        int x = point.x;
        int y = point.y;
        Direction nextDirection = movement.getNext();

        if (canMoveInDirection(index, x + nextDirection.getX(), y + nextDirection.getY())) {
            moveInDirection(nextDirection);
        }
    }

    public boolean canMoveInDirection(int index, int x, int y) {
        if (!isWithinBounds(x, y)) {
            return false;
        }

        int i = x / Constantes.BLOCK_SIZE;
        int j = y / Constantes.BLOCK_SIZE;

        return Constantes.BLOCKS_MAPS[index][j][i] != 1 || movement.getCurrent() != Direction.UP;
    }

    public boolean isWithinBounds(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < Constantes.DIMENSION.width) && (y < Constantes.DIMENSION.height);
    }

    private void moveInDirection(Direction direction) {
        movement.setCurrent(direction);
        movement.setNext(Direction.NONE);
    }

    public void askToChangeDirection(Direction d) {
        movement.setNext(d);
    }
    
    public boolean wraparound(int x, int y) {
		for(int i = 0; i < 2; i++) {
			if(x == Constantes.STATIC_WRAPAROUND_POINTS[i].x && y == Constantes.STATIC_WRAPAROUND_POINTS[i].y) {
				point = Cloner.clonePoint(Constantes.DYNAMIC_WRAPAROUND_POINTS[(i+1) % 2]);
				return true;
			}
		}
		return false;
	}
    
    

    public int[] getMatrixPosition() {
		return new int[]{point.x/Constantes.BLOCK_SIZE, point.y/Constantes.BLOCK_SIZE};
	}


    public Point getPoint() {
        return point;
    }

    public void back2Start() {
        point = Cloner.clonePoint(START);
    }

    public Movement getMovement() {
        return movement;
    }

    public State getState() {
        return state;
    }

    public void setPoint(Point p) {
        this.point = p;
    }

    public void setMovement(Movement m) {
        this.movement = m;
    }

    public void setState(State s) {
        this.state = s;
    }

    public class Movement {
        private Direction current;
        private Direction next;

        public Movement() {
            current = Direction.NONE;
            next = Direction.NONE;
        }

        public Direction getCurrent() {
            return current;
        }

        public Direction getNext() {
            return next;
        }

        public void setCurrent(Direction c) {
            this.current = c;
        }

        public void setNext(Direction n) {
            this.next = n;
        }
    }
}
