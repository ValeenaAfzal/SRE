package Refactored_Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

import Refactored_Constantes.Constantes;
import Refactored_PacObject.PacMan;
import Refactored_Constantes.ColorConstants;
import Refactored_Utilities.Direction;

@SuppressWarnings("serial")
public class Drawer extends JPanel {
    private final int blockSize = Constantes.BLOCK_SIZE;
    private Point pacmanPoint = new Point();
    private Point[] ghostPoints = new Point[Constantes.NUMBER_OF_GHOST];
    private Color pacColor = Color.yellow;
    private Direction pacmanDirection;
    private int[][] blocks;
    private int[][] gomes;

    public Drawer(int[][] blocks, int[][] gomes) {
        this.setBackground(Color.white);
        updateMaps(blocks, gomes);
    }

    public Drawer() {
        // Initialize default values
        this.setBackground(Color.white);
        this.blocks = new int[0][0];
        this.gomes = new int[0][0];
        this.pacmanDirection = Direction.RIGHT; // Default direction
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g);
        drawPacman(g);
        for (int i = 0; i < Constantes.NUMBER_OF_GHOST; i++) {
            drawPacghost(g, i);
        }
    }

    private void drawPacman(Graphics g) {
        g.setColor(pacColor);
        int startAngle = getStartAngle();
        g.fillArc(pacmanPoint.x, pacmanPoint.y, blockSize, blockSize, startAngle, 290);
    }

    private int getStartAngle() {
        switch (pacmanDirection) {
            case UP:
                return 125;
            case DOWN:
                return 305;
            case LEFT:
                return 215;
            default:
                return 35;
        }
    }

    private void drawPacghost(Graphics g, int index) {
        g.setColor(ColorConstants.GHOST_COLORS[index]);
        int x = ghostPoints[index].x, y = ghostPoints[index].y;
        int ex = (x + 6 * blockSize / 16), ey = (y + blockSize / 8);
        int pas = blockSize / 4;
        int[] xpoints = getXPoints(x);
        int[] ypoints = getYPoints(y);
        Polygon poly = new Polygon(xpoints, ypoints, xpoints.length);
        g.fillPolygon(poly);
        g.setColor(Color.black);
        g.fillOval(ex, ey, pas, pas);
    }

    private int[] getXPoints(int x) {
        return new int[]{x + 4 * (blockSize / 8), 2 * (blockSize / 8) + x, blockSize / 8 + x, blockSize / 8 + x,
                2 * (blockSize / 8) + x, 3 * (blockSize / 8) + x, 4 * blockSize / 8 + x, 5 * blockSize / 8 + x,
                6 * blockSize / 8 + x, 7 * blockSize / 8 + x, 7 * blockSize / 8 + x, 6 * blockSize / 8 + x};
    }

    private int[] getYPoints(int y) {
        return new int[]{y, y + blockSize / 4, y + blockSize / 2, y + 3 * blockSize / 4, y + blockSize, y + 3 * blockSize / 4,
                y + blockSize, y + 3 * blockSize / 4, y + blockSize, y + 3 * blockSize / 4, y + blockSize / 2,
                y + blockSize / 4};
    }

    private void drawMap(Graphics g) {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] == 1) {
                    g.setColor(Color.black);
                    g.fill3DRect(j * blockSize, i * blockSize, blockSize, blockSize, true);
                } else if (blocks[i][j] == 3) {
                    g.setColor(Color.red);
                    g.drawLine(j * blockSize, i * blockSize, (j + 1) * blockSize, i * blockSize);
                }
                int value = gomes[i][j];
                if (value != 0) {
                    double scale = Constantes.SCALES[value - 1];
                    g.setColor(ColorConstants.GOMES_COLORS[value - 1]);
                    int ovalX = (int) ((j + 0.25) * blockSize - scale * blockSize / 2);
                    int ovalY = (int) ((i + 0.25) * blockSize - scale * blockSize / 2);
                    int ovalSize = (int) (scale * blockSize);
                    g.fillOval(ovalX, ovalY, ovalSize, ovalSize);
                }
            }
        }
    }

    public void updateMaps(int[][] blocks, int[][] gomes) {
        this.blocks = blocks;
        this.gomes = gomes;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setPacmanFeatures(PacMan pac) {
        pacmanPoint = pac.getPoint();
        pacmanDirection = (Direction) pac.getMovement().getCurrent();
        pacColor = pac.getColor();
    }

    public void setPacghostPoint(int index, Point point, Color color) {
        ghostPoints[index] = point;
        ColorConstants.GHOST_COLORS[index] = color;
    }
}
