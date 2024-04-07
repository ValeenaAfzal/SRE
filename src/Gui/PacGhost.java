package Gui;

import Gui.AbstractCharacter;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import Gui.Constante;


public class PacGhost extends NewClass{
	int jailTime = 0;
	
	public PacGhost(Point p, Color bc){
		START = Utils.clonePoint(p);
		point = Utils.clonePoint(p);
		movement.setCurrent(Direction.UP);
		state = State.NORMAL;
		baseColor = bc;
		color = bc;
	}
	
	public void setStart(Point p) {
		START = p;		
	}
	
	public void startJailTimeContDown() {
		jailTime = Constante.JAIL_TIME_UNIT;
	}
	public void SendSignalToReleaseFromJail() {
		//TODO
	}
	public int getVelocity() {
		return velocity;
	}
	
	public void slowdown() {
		velocity = Constante.SLOW_VELOCITY;
		color = Color.blue;
		unit = Constante.UNIT;
	}
	
	public void toNormal() {
		velocity = Constante.STD_VELOCITY;
		color = baseColor;
	}
	
	@Override
	public void manage() {
		if(unit >= 0) unit--;
		else toNormal();
		
		if(jailTime > 0) {
			jailTime--;
			back2Start();
		}
	}

	
	public void correctBadMove() {
		if(getVelocity() == Constante.STD_VELOCITY) {
			int x = point.x, y = point.y;
			
			if((x+y) % Constante.STD_VELOCITY == 0) return;
			
			setPoint(correctPoint(0, x, y));
			
		}
	}

	private Point correctPoint(int index, int x, int y) {
		
		if(x % Constante.STD_VELOCITY != 0) {
			if( checkBounds(x + Constante.SLOW_VELOCITY, y) && !collision(index, x + Constante.SLOW_VELOCITY,y) ) {
				x += Constante.SLOW_VELOCITY;
			}else{
				x -= Constante.SLOW_VELOCITY;
			}
		}
		
		if(y % Constante.STD_VELOCITY != 0) {
			if( checkBounds(x, y + Constante.SLOW_VELOCITY) && !collision(index,x,y + Constante.SLOW_VELOCITY) ) {
				y += Constante.SLOW_VELOCITY;
			}else{
				y -= Constante.SLOW_VELOCITY;
			}
		}
		
		return new Point(x, y);
	}
	
	@Override
	public void back2Start() {
		super.back2Start();
		movement.setCurrent(Direction.UP);
		toNormal(); //*************************
	}
	
	public Direction getRandomDirection() {
		Direction[] choice = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
		Random rand = new Random();
		int n = rand.nextInt(1000) % 4;
		//System.out.println("Random : "+ n);
		
		return choice[n];
	}	
	
}
