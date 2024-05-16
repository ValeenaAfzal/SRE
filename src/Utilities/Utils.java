package Refactored_Utilities;

import java.awt.Dimension;
import java.awt.Point;

public class Utils {
    public static Point clonePoint(Point p) {
        return new Point(p);
    }

    public static Dimension cloneDimension(Dimension d, int widthOffset, int heightOffset) {
        return new Dimension(d.width + widthOffset, d.height + heightOffset);
    }
}
