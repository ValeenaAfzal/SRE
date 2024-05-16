package Refactored_Gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Refactored_Constantes.Constantes;
import Refactored_Constantes.MapConstants;
import Refactored_Gui.Transformers.Mapper;

public class Structure {
    private Point START = new Point(3, 4);
    private Point VSTART = new Point(15, 10);
    private List<String> design = new ArrayList<>(List.of("A"));
    private Transformers transformer = new Transformers();
    List<Mapper> customMap = new ArrayList<>();
    private DesignDrawer designDrawer = new DesignDrawer();

    public Structure() {
        updateDesign(design);
    }

    public Structure(List<String> d) {
        design = d;
        updateDesign(d);
    }

    public void updateDesign(List<String> d) {
        for (String l : d) {
            for (Mapper map : transformer.getMapping()) {
                if (l.equals(map.letter))
                    customMap.add(map);
            }
        }
    }

    public void drawStrucure(Graphics g, int size) {
        drawMap(g, size);
        designDrawer.drawDesigns(g, START, customMap, size);
        designDrawer.drawDesigns(g, VSTART, transformer.getVersion(), size);
    }

    public void drawMap(Graphics g, int size) {
        double scale = 0.3;
        for (int i = 0; i < MapConstants.BLOCKS_MAP.length; i++) {
            for (int j = 0; j < MapConstants.BLOCKS_MAP[i].length; j++) {
                if (MapConstants.BLOCKS_MAP[i][j] == 1) {
                    g.setColor(Color.black);
                    g.fillRect(j * size, i * size, size, size);
                } else if (MapConstants.BLOCKS_MAP[i][j] == 3) {
                    g.setColor(Color.red);
                    g.drawLine(j * size, i * size, (j + 1) * size, i * size);
                }
            }
        }
    }
}
