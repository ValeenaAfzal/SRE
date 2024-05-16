package Refactored_Gui;
import Refactored_Gui.Transformers.Mapper;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

public class DesignDrawer {
    public void drawDesigns(Graphics g, Point start, List<Mapper> maps, int size) {
        Point first = new Point(start.x, start.y);
        for (Mapper map : maps) {
            Point p = new Point(first.x, first.y);
            System.out.println("Drawing letter: '" + map.letter + "'");
            for (Shapes sh : map.getShapes()) {
                p = new Point(p.x + sh.getXPlus(), p.y + sh.getYPlus());
                drawLines(g, sh, p, size);
            }
            first = new Point(p.x + map.getAdd(), first.y);
        }
    }

    private void drawLines(Graphics g, Shapes sh, Point p, int size) {
        if (sh.getLine() == LineType.VERTICAL) {
            drawBlocks(g, p, sh.getMinThickness(), sh.getMaxThickness(), size);
        } else if (sh.getLine() == LineType.HORIZONTAL) {
            drawBlocks(g, p, sh.getMaxThickness(), sh.getMinThickness(), size);
        } else if (sh.getLine() == LineType.TWO_VERTICAL) {
            drawBlocks(g, p, sh.getMinThickness(), sh.getMaxThickness(), size);
            Point x = new Point(p.x + sh.getMaxThickness() + sh.getInBetweenDistance(), p.y);
            drawBlocks(g, x, sh.getMinThickness(), sh.getMaxThickness(), size);
        } else if (sh.getLine() == LineType.TWO_HORIZONTAL) {
            drawBlocks(g, p, sh.getMaxThickness(), sh.getMinThickness(), size);
            Point x = new Point(p.x, p.y + sh.getMinThickness() + sh.getInBetweenDistance());
            drawBlocks(g, x, sh.getMaxThickness(), sh.getMinThickness(), size);
        }
    }

    private void drawBlocks(Graphics g, Point p, int w, int h, int size) {
        g.fillRect(p.x * size, p.y * size, w * size, h * size);
    }
}
