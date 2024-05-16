package Refactored_Utilities;

import Refactored_Constantes.Constantes;
import java.util.Arrays;

public class MatrixUtils extends Utils {
    public static int howMuchBaseGomes(int[][] mat) {
		int x = mat.length;
		int y = mat[0].length;
		int cnt = 0;
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(mat[i][j] == 1) cnt++;
			}
		}
		
		return cnt;
	}

    public static int[][] makeGomesForNewMap(int[][] mat, int[][] gom, int cnt, int index, int index1){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		
		for(int i = 0; i < x & cnt > 0; i++) {
			for(int j = 0; j < y & cnt > 0; j++) {
				
				if(mat[i][j] == 0) {
					clone[i][j] = 1;
					cnt--;
				}
			}
		}
		
		int[][]xs = Constantes.xs, ys = Constantes.ys, vs = Constantes.vs;
		
		//Constante.addSpecialGomes(index, map);
		for(int i = 0; i < vs[0].length; i++) {
			clone[xs[index][i]][ys[index][i]] = gom[xs[index1][i]][ys[index1][i]];
			//Constante.addSpecialGomes(index, clone);
		}
		
		return clone;
	}
}
