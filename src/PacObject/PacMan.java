package Refactored_PacObject;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

import Refactored_Constantes.Constantes;
import Refactored_Utilities.Direction;
import Refactored_Utilities.State;
import Refactored_Utilities.Cloner;

public class PacMan extends AbstractCharacter {
    private int life;
    boolean bonus;

    public PacMan() {
        START = new Point(Constantes.UNIT, Constantes.PAC_START_LIFE);
        point = Cloner.clonePoint(START);
        movement = new Movement();
        state = State.NORMAL;
        life = Constantes.PAC_START_LIFE;
        baseColor = Color.yellow.darker();
        color = baseColor;
        bonus = false;
    }

    @Override
    public void move(int index) {
        checkToChangeDirection(index);
        updatePosition(index);
        checkWraparound();
    }

    private void updatePosition(int index) {
        int x = point.x;
        int y = point.y;
        Direction currentDirection = movement.getCurrent();

        if (currentDirection == Direction.UP) y -= velocity;
        else if (currentDirection == Direction.DOWN) y += velocity;
        else if (currentDirection == Direction.LEFT) x -= velocity;
        else if (currentDirection == Direction.RIGHT) x += velocity;

        if (isWithinBounds(x, y) && !canMoveInDirection(index, x, y)) {
            point.x = x;
            point.y = y;
        }
    }

    private void checkWraparound() {
        if (wraparound(point.x, point.y)) return;
    }

    public void manage() {
        decrementUnit();
        setColorBasedOnUnit();
    }

    private void decrementUnit() {
        if (unit > 0) unit--;
        else changeStateAndColor(State.NORMAL, baseColor);
    }

    private void setColorBasedOnUnit() {
        if (unit > 0) setColor(baseColor.brighter());
        else setColor(baseColor);
    }
    private void changeState(State s) {
		this.state = s;
	}

    public void specialState(State s) {
        this.changeState(s);
	this.unit = Constantes.UNIT;
    }

    @Override
    public void back2Start() {
        super.back2Start();
        movement.setCurrent(Direction.NONE);
    }

    public void addLife() {
        if (bonus) return;
        life++;
        bonus = true;
    }

    public void loseLife() {
        life--;
    }

    private void changeStateAndColor(State s, Color c) {
        this.state = s;
        setColor(c);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void getKey(int key, int index) {
        if (key == KeyEvent.VK_UP) movement.setNext(Direction.UP);
        else if (key == KeyEvent.VK_DOWN) movement.setNext(Direction.DOWN);
        else if (key == KeyEvent.VK_LEFT) movement.setNext(Direction.LEFT);
        else if (key == KeyEvent.VK_RIGHT) movement.setNext(Direction.RIGHT);

        checkToChangeDirection(index);
    }

    public void resetBonus() {
        bonus = false;
    }
}
