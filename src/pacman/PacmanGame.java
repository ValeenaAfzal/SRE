package Refactored_pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Refactored_Constantes.Constantes;
import Refactored_Constantes.MapConstants;
import Refactored_Gui.Maze;
import Refactored_PacObject.PacGhost;
import Refactored_PacObject.PacMan;
import Refactored_Utilities.State;
import Refactored_Utilities.Utils;
import Refactored_Utilities.MatrixUtils;
import Refactored_Utilities.Cloner;

public class PacmanGame {
    private static final int GOME_VALUE_1 = 1;
    private static final int GOME_VALUE_2 = 2;
    private static final int GOME_VALUE_3 = 3;
    private static final int GOME_VALUE_4 = 4;
    private static final int SCORE_THRESHOLD = 5000;
    private static final int INVISIBLE_SCORE = 300;
    private static final int SUPER_SCORE = 500;
    private static final int WINNING_SCORE = 1000;
    private static final int GHOST_CAPTURE_DELAY = 300;

    private int score;
    private int mapIndex;
    private PacMan pacman = new PacMan();
    private PacGhost[] ghosts = new PacGhost[Constantes.NUMBER_OF_GHOST];
    private int[][] blocksMatrix;
    private int[][] gomesMatrix;
    private Maze maze;
    private KeyListener listener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            pacman.getKey(key, mapIndex);
        }
    };

    public PacmanGame() {
        initializeGhosts();
        totalReset();
        maze = new Maze(Cloner.clone2DMatrix(blocksMatrix), Cloner.clone2DMatrix(gomesMatrix), listener, pacman, ghosts);
    }

    public void play() {
        while (!noMoreGomes() && !noMoreLife()) {
            updateGame();
            moveGhosts();
            updateGame();
        }
        displayGameResult();
        ask2Play("");
    }

    private void updateGame() {
        maze.setGhostsPoint(ghosts);
        maze.updateMaps(Cloner.clone2DMatrix(blocksMatrix), Cloner.clone2DMatrix(gomesMatrix));
        maze.show(pacman, score, pacman.getLife());
        pacman.manage();
        pacman.move(mapIndex);
        updateAll();
    }

    private void displayGameResult() {
        if (noMoreGomes()) {
            congrats("Congrats !!! You've won the game !!!!");
        } else {
            congrats("You're a loserrrrr !");
        }
    }

    private void initializeGhosts() {
        for (int i = 0; i < Constantes.NUMBER_OF_GHOST; i++) {
            ghosts[i] = new PacGhost(Utils.clonePoint(Constantes.GHOST_START_POSITIONS[i]), Constantes.GHOST_COLORS[i]);
        }
    }

    public void ask2Play(String msg) {
        String response = JOptionPane.showInputDialog(null, msg + "Play again ?\n y: yes  n: no");
        System.out.println("Response : " + response);
        if (response.equals("y")) {
            totalReset();
            play();
        } else if (response.equals("n")) {
            quit();
        } else {
            ask2Play("Didn't get your response.\n");
        }
    }

    public void congrats(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Infos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void totalReset() {
        mapIndex = 0;
        score = 0;
        pacman.setLife(Constantes.PAC_START_LIFE);
        partialReset();
        resetMaps();
    }

    public void partialReset() {
        pacman.back2Start();
        for (int i = 0; i < Constantes.NUMBER_OF_GHOST; i++) {
            ghosts[i].back2Start();
        }
    }

    private void resetMaps() {
        blocksMatrix = Cloner.clone2DMatrix(Constantes.blocksMaps[mapIndex]);
        gomesMatrix = Constantes.buildGomeMap(mapIndex);
    }

    private void resetMaps(int cnt, int index) {
        blocksMatrix = Cloner.clone2DMatrix(Constantes.blocksMaps[mapIndex]);
        gomesMatrix = MatrixUtils.makeGomesForNewMap(blocksMatrix, gomesMatrix, cnt, mapIndex, index);
    }

    public boolean noMoreGomes() {
        for (int[] row : gomesMatrix) {
            for (int value : row) {
                if (value == GOME_VALUE_1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean noMoreLife() {
        return pacman.getLife() <= 0;
    }
    
    public void updateGoneMatrix(int i, int j) {
		setGoneMatrixCell(i, j, 0);
	}

    public void updateAll() {
        int[] position = pacman.getMatrixPosition();
        int i = position[0];
        int j = position[1];
        int v = getGomeMatrixCell(i, j);
        if (v != 0) {
            computeScore(v);
            updateGomeMatrix(i, j);
        }
        applyRules(i, j);
    }

    public void applyRules(int i, int j) {
        if (pacman.getState() == State.INVISIBLE) {
            return;
        }

        for (int n = 0; n < Constantes.NUMBER_OF_GHOST; n++) {
            int k = ghosts[n].getMatrixPosition()[0];
            int m = ghosts[n].getMatrixPosition()[1];
            if (i == k && j == m) {
                System.out.println("Same pos for Pac and Ghost " + n);
                if (pacman.getState() == State.SUPER) {
                    ghosts[n].startJailTimeContDown();
                    System.out.println("Ghost " + n + " is in Jail");
                } else if (pacman.getState() == State.NORMAL) {
                    System.out.println("Ghost " + n + " caught Pacman");
                    pacman.loseLife();
                    sleep(GHOST_CAPTURE_DELAY);
                    partialReset();
                }
            }
        }
    }

    public void computeScore(int v) {
        if (v == GOME_VALUE_1) {
            score += 100;
        } else if (v == GOME_VALUE_2) {
            score += INVISIBLE_SCORE;
            pacman.specialState(State.INVISIBLE);
            System.out.println("Invisible Pacman");
        } else if (v == GOME_VALUE_3) {
            score += SUPER_SCORE;
            pacman.specialState(State.SUPER);
            slowdownGhosts();
            System.out.println("Super Pacman");
        } else if (v == GOME_VALUE_4) {
            score += WINNING_SCORE;
            int index = mapIndex;
            mapIndex++;
            mapIndex %= MapConstants.BLOCKS_MAP.length;
            sleep(100);
            partialReset();
            int cnt = MatrixUtils.howMuchBaseGomes(gomesMatrix);
            resetMaps(cnt, index);
        }

        if (score >= SCORE_THRESHOLD) {
            pacman.addLife();
        }
    }

    private int getGomeMatrixCell(int i, int j) {
        return gomesMatrix[j][i];
    }
    
    private void setGoneMatrixCell(int i, int j, int v) {
		gomesMatrix[j][i] = v;
	}

    private void updateGomeMatrix(int i, int j) {
       setGoneMatrixCell(i, j, 0);
    }

    public void moveGhosts() {
        for (PacGhost ghost : ghosts) {
            ghost.move(mapIndex);
        }
    }

    public void slowdownGhosts() {
        for (PacGhost ghost : ghosts) {
            ghost.slowdown();
        }
    }
    
    public void manageGhosts() {
		for(int i = 0; i < Constantes.NUMBER_OF_GHOST; i++) {
			ghosts[i].manage();
		}
	}

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printScore() {
        System.out.println("Your score : " + score);
    }

    public void quit() {
        maze.close();
    }
}
