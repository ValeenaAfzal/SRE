package Refactored_Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Refactored_Constantes.Constantes;
import Refactored_PacObject.PacGhost;
import Refactored_PacObject.PacMan;
import Refactored_Constantes.DimensionConstants;
import Refactored_Constantes.GameConstants;

public class Maze {
    private JFrame frame = new JFrame();
    private JLabel label;
    private Drawer drawer;

    public Maze(int[][] blocks, int[][] gomes, KeyListener listener, PacMan pac, PacGhost[] ghosts) {
        initializeFrame(listener);
        initializeComponents(blocks, gomes);
        setPacmanAndGhosts(pac, ghosts);
    }

    private void initializeFrame(KeyListener listener) {
        JPanel container = new JPanel();
        label = new JLabel("Score : 0");
        drawer = new Drawer();
        label.setVisible(true);
        container.setLayout(new BorderLayout(0, 2));
        container.add(label, BorderLayout.NORTH);
        container.add(drawer, BorderLayout.CENTER);
        frame.setTitle("Pacman");
        frame.setSize(DimensionConstants.DIMENSION);
        frame.setLocation(300, 200);
        frame.setContentPane(container);
        frame.addKeyListener(listener);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    private void initializeComponents(int[][] blocks, int[][] gomes) {
        drawer.updateMaps(blocks, gomes);
    }

    private void setPacmanAndGhosts(PacMan pac, PacGhost[] ghosts) {
        drawer.setPacmanFeatures(pac);
        for (int i = 0; i < GameConstants.NUMBER_OF_GHOSTS; i++) {
            drawer.setPacghostPoint(i, ghosts[i].getPoint(), ghosts[i].getColor());
        }
    }
    
    public void updateMaps(int[][] b, int[][] g) {
		drawer.updateMaps(b, g);
	}
	
	public void setGhostsPoint(PacGhost[] ghosts) {
		for(int i = 0; i < Constantes.NUMBER_OF_GHOST; i++) {
			drawer.setPacghostPoint(i, ghosts[i].getPoint(), ghosts[i].getColor());
		}
	}


    public void show(PacMan pac, int score, int life) {
        drawer.setPacmanFeatures(pac);
        label.setText("Score : " + score + "  Life: " + life);
        sleep(200);
        drawer.repaint();
    }

    public void close() {
        frame.dispose();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
