package Refactored_Utilities;

import java.util.Arrays;

public class Cloner extends Utils {
    public static int[][] clone2DMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] clone = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(mat[i], 0, clone[i], 0, cols);
        }
        return clone;
    }
}
