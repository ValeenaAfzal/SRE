package Refactored_PacObject;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import Refactored_Constantes.Constantes;
import Refactored_Utilities.Direction;
import Refactored_Utilities.State;
import Refactored_Utilities.Cloner;

public class PacGhost extends AbstractCharacter {
    int jailTime = 0;

    public PacGhost(Point p, Color bc) {
        START = Cloner.clonePoint(p);
        point = Cloner.clonePoint(p);
        movement.setCurrent(Direction.UP);
        state = State.NORMAL;
        baseColor = bc;
        color = bc;
    }

    public void setStart(Point p) {
        START = p;
    }

    public void startJailTimeContDown() {
        jailTime = Constantes.JAIL_TIME_UNIT;
    }

    public void SendSignalToReleaseFromJail() {
        //TODO
    }
    
    public int getVelocity() {
		return velocity;
	}

    @Override
    public void manage() {
        manageUnit();
        manageJailTime();
    }

    private void manageUnit() {
        if (unit >= 0) unit--;
        else toNormal();
    }

    private void manageJailTime() {
        if (jailTime > 0) {
            jailTime--;
            back2Start();
        }
    }

    @Override
    public void move(int index) {
        int x = point.x;
        int y = point.y;

        if (movement.getCurrent() == Direction.UP) y -= velocity;
        else if (movement.getCurrent() == Direction.DOWN) y += velocity;
        else if (movement.getCurrent() == Direction.LEFT) x -= velocity;
        else if (movement.getCurrent() == Direction.RIGHT) x += velocity;

        handleCollision(index, x, y);
    }

    private void handleCollision(int index, int x, int y) {
        if (canMoveInDirection(index, x, y)) {
            movement.setCurrent(getRandomDirection());
            move(index);
            return;
        }
        point.x = x;
        point.y = y;
    }

    public void slowdown() {
        velocity = Constantes.SLOW_VELOCITY;
        color = Color.blue;
        unit = Constantes.UNIT;
    }

    public void toNormal() {
        velocity = Constantes.STD_VELOCITY;
        color = baseColor;
    }

    public void correctBadMove() {
        if (getVelocity() == Constantes.STD_VELOCITY) {
            int x = point.x, y = point.y;
			
            if((x+y) % Constantes.STD_VELOCITY == 0) return;
            setPoint(correctPoint(0, point.x, point.y));
        }
    }

    private Point correctPoint(int index, int x, int y) {

        if (x % Constantes.STD_VELOCITY != 0) {
            if (isWithinBounds(x + Constantes.SLOW_VELOCITY, y) && !canMoveInDirection(index, x + Constantes.SLOW_VELOCITY, y)) {
                x += Constantes.SLOW_VELOCITY;
            } else {
                x -= Constantes.SLOW_VELOCITY;
            }
        }

        if (y % Constantes.STD_VELOCITY != 0) {
            if (isWithinBounds(x, y + Constantes.SLOW_VELOCITY) && !canMoveInDirection(index, x, y + Constantes.SLOW_VELOCITY)) {
                y += Constantes.SLOW_VELOCITY;
            } else {
                y -= Constantes.SLOW_VELOCITY;
            }
        }

        return new Point(x, y);
    }

    @Override
    public void back2Start() {
        super.back2Start();
        movement.setCurrent(Direction.UP);
        toNormal();
    }

    public Direction getRandomDirection() {
        Direction[] choice = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
        Random rand = new Random();
        int n = rand.nextInt(1000) % 4;
        return choice[n];
    }

}
