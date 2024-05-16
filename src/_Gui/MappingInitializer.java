package Refactored_Gui;

import java.util.ArrayList;
import java.util.List;

public class MappingInitializer {
    public void initialize(List<Transformers.Mapper> mapping) {
        mapping.add(new Transformers.Mapper("A", createShapesForA(), 9));
        mapping.add(new Transformers.Mapper("C", createShapesForC(), 8));
        mapping.add(new Transformers.Mapper("M", createShapesForM(), 5));
        mapping.add(new Transformers.Mapper("N", createShapesForN(), 5));
        mapping.add(new Transformers.Mapper("P", createShapesForP(), 5));
        // Add other mappings here...
    }

    private List<Shapes> createShapesForA() {
        List<Shapes> shapes = new ArrayList<>();
        shapes.add(new Shapes(LineType.TWO_VERTICAL, 1, 5, 0, 0, 2));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 1, 2, 1, 0, 1));
        return shapes;
    }

    private List<Shapes> createShapesForC() {
        List<Shapes> shapes = new ArrayList<>();
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 0, 0));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 2, 0, 6));
        return shapes;
    }

    private List<Shapes> createShapesForM() {
        List<Shapes> shapes = new ArrayList<>();
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 0, 0));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 2, 0));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, -2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, -2));
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 1, 0));
        // Add other shapes for M here...
        return shapes;
    }

    private List<Shapes> createShapesForN() {
        List<Shapes> shapes = new ArrayList<>();
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 0, 0));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 2, 0));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 1, 2, 1, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 1, -8));
        // Add other shapes for N here...
        return shapes;
    }

    private List<Shapes> createShapesForP() {
        List<Shapes> shapes = new ArrayList<>();
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 2, 4, 2, 0, 2));
        shapes.add(new Shapes(LineType.VERTICAL, 2, 6, 4, 0));
        // Add other shapes for P here...
        return shapes;
    }
}

