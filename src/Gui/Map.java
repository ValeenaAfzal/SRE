/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;
import Gui.Constante;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JPanel;
@SuppressWarnings("serial")


/**
 *
 * @author Valeena
 */
public class Map extends JPanel {
    private int[][] blocks;
	private int[][] gomes;
	
    public void drawMap(Graphics g, int size) {
		double scale = 0.3;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[i].length; j++) {
				//Blocks
				if(blocks[i][j] == 1) {
					g.setColor(Color.black);
					g.fill3DRect(j*size, i*size, size, size, true);
				}else if(blocks[i][j] == 3) {
					g.setColor(Color.red);
					g.drawLine(j*size, i*size, (j+1)*size, i*size);
				}
				//Pacgomes
				int v = gomes[i][j];
				if(v != 0) {
					scale = Constante.SCALES[v-1];
					g.setColor(Constante.GOMESCOLORS[v-1]);
					g.fillOval((int) ((j+0.25)*size-scale), (int) ((i+0.25)*size-scale), (int) (size*scale), (int) (size*scale));
				}
			}
		}
	}
	
	public void updateMaps(int[][] b, int[][] g) {
		blocks = b;
		gomes = g;
	}
	
    
}
