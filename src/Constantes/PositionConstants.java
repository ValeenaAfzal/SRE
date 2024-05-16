package Refactored_Constantes;

import java.awt.Point;

public class PositionConstants {

    // Wraparound points
    public static final Point[] STATIC_WRAPAROUND_POINTS = {
            new Point(-Constant.BLOCK_SIZE, 8 * Constant.BLOCK_SIZE),
            new Point(19 * Constant.BLOCK_SIZE + Constant.BLOCK_SIZE, 8 * Constant.BLOCK_SIZE)};
    public static final Point[] DYNAMIC_WRAPAROUND_POINTS = {
            new Point(0, 8 * Constant.BLOCK_SIZE),
            new Point(19 * Constant.BLOCK_SIZE, 8 * Constant.BLOCK_SIZE)};

    // Starting positions for Pacman and ghosts
    public static final Point PAC_START = new Point(16 * Constant.BLOCK_SIZE, 15 * Constant.BLOCK_SIZE);
    public static final Point[] GHOST_START = {
            new Point(9 * Constant.BLOCK_SIZE, 7 * Constant.BLOCK_SIZE),
            new Point(10 * Constant.BLOCK_SIZE, 7 * Constant.BLOCK_SIZE),
            new Point(9 * Constant.BLOCK_SIZE, 8 * Constant.BLOCK_SIZE),
            new Point(10 * Constant.BLOCK_SIZE, 8 * Constant.BLOCK_SIZE)};

}
