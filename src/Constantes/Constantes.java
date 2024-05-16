package Refactored_Constantes;

import static Refactored_Constantes.Constantes.vs;
import static Refactored_Constantes.Constantes.xs;
import static Refactored_Constantes.Constantes.ys;
import Refactored_Utilities.Cloner;
import Refactored_Constantes.MapConstants;
import Refactored_Constantes.MapConstants0;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Constantes {

    // Block size
    public static final int BLOCK_SIZE = GameConstants.BLOCK_SIZE;

    // Game dimensions
    public static final Dimension DIMENSION = DimensionConstants.DIMENSION;

    // Time units
    public static final int UNIT = Constant.UNIT; // duration of a special state
    public static final int JAIL_TIME_UNIT = Constant.JAIL_TIME_UNIT;

    // Velocities
    public static final int STD_VELOCITY = Constant.STD_VELOCITY;
    public static final int SLOW_VELOCITY = Constant.SLOW_VELOCITY;

    // Number of ghosts and starting life for Pacman
    public static final int NUMBER_OF_GHOST = GameConstants.NUMBER_OF_GHOSTS;
    public static final int PAC_START_LIFE = GameConstants.PAC_START_LIFE;

    // Colors for ghosts and gomes
    public static final Color[] GHOST_COLORS = ColorConstants.GHOST_COLORS;
    public static final Color[] GOMES_COLORS = ColorConstants.GOMES_COLORS;

    // Scaling factors
    public static final double[] SCALES = {0.3, 0.5, 0.5, 0.5};

    // Wraparound points
    public static final Point[] STATIC_WRAPAROUND_POINTS = PositionConstants.STATIC_WRAPAROUND_POINTS;
    public static final Point[] DYNAMIC_WRAPAROUND_POINTS = PositionConstants.DYNAMIC_WRAPAROUND_POINTS;

    // Starting positions for Pacman and ghosts
    public static final Point PAC_START_POSITION = PositionConstants.PAC_START;
    public static final Point[] GHOST_START_POSITIONS = PositionConstants.GHOST_START;

    // Gomes information
    public static final int[][] GOMES_X_POSITIONS = MapConstants.BLOCKS_MAP;
    public static final int[][] GOMES_Y_POSITIONS = MapConstants.BLOCKS_MAP;
    public static final int[][] GOMES_VALUES = MapConstants.BLOCKS_MAP;

    // Map data
    public static final int[][][] BLOCKS_MAPS = {MapConstants.BLOCKS_MAP, MapConstants0.BLOCKS_MAP_0};

    // Constructor and other methods
    public static int[][] xs = {
			{16, 16, 8, 0, 0, 2, 4, 10},
			{15, 15, 8, 1, 1, 5, 6, 9}
	};
	public static int[][] ys = {
			{5, 7, 4, 1, 19, 6, 17, 18},
			{5, 7, 2, 1, 18, 9, 14, 18}
	};
	public static int[][] vs = {
			{3, 4,  3,  3, 2,  3, 2, 3},
			{3, 4,  3,  3, 2,  3, 2, 3}
	};
        
        public static void addSpecialGomes(int index, int[][] map) {
		for(int i = 0; i < vs[0].length; i++) {
			map[xs[index][i]][ys[index][i]] = vs[index][i];
		}
	}
        
        public static int[][][] blocksMaps =  {
			MapConstants.BLOCKS_MAP,
			MapConstants0.BLOCKS_MAP_0
		};
        
        public static int[][] buildGomeMap(int index) {
		int[][] map = Cloner.clone2DMatrix(blocksMaps[index]);
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				map[i][j] = (map[i][j] + 1) % 2;
			}
		}
		//Add specials gomes
		addSpecialGomes(index, map);
		
		return map;
	}

}
