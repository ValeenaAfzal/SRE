package Refactored_Utilities;

public enum Direction {
	
	NONE(0,0),
	UP(0, -Refactored_Constantes.Constantes.BLOCK_SIZE),
	DOWN(0, Refactored_Constantes.Constantes.BLOCK_SIZE),
	LEFT(-Refactored_Constantes.Constantes.BLOCK_SIZE, 0),
	RIGHT(Refactored_Constantes.Constantes.BLOCK_SIZE, 0);

	
	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}	

	@Override
	public String toString() {
		return "(" + x + ", " + y +")";
	}
}
		
