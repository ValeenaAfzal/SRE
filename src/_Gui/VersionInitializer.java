package Refactored_Gui;

import java.util.ArrayList;
import java.util.List;

public class VersionInitializer {
    public void initialize(List<Transformers.Mapper> v1, List<Transformers.Mapper> v2) {
        // Define mappings for v1
        v1.add(new Transformers.Mapper("V1_A", createShapesForV1_A(), 9));
        v1.add(new Transformers.Mapper("V1_B", createShapesForV1_B(), 8));

        // Define mappings for v2
        v2.add(new Transformers.Mapper("V2_A", createShapesForV2_A(), 7));
        v2.add(new Transformers.Mapper("V2_B", createShapesForV2_B(), 6));
    }

    private List<Shapes> createShapesForV1_A() {
        List<Shapes> shapes = new ArrayList<>();
        // Define shapes for V1_A
        // Example:
        shapes.add(new Shapes(LineType.TWO_VERTICAL, 1, 5, 0, 0));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 1, 2, 1, 0));
        return shapes;
    }

    private List<Shapes> createShapesForV1_B() {
        List<Shapes> shapes = new ArrayList<>();
        // Define shapes for V1_B
        // Example:
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 0, 0));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 2, 0, 6));
        return shapes;
    }

    private List<Shapes> createShapesForV2_A() {
        List<Shapes> shapes = new ArrayList<>();
        // Define shapes for V2_A
        // Example:
        shapes.add(new Shapes(LineType.VERTICAL, 2, 10, 0, 0));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 2, 0, 6));
        return shapes;
    }

    private List<Shapes> createShapesForV2_B() {
        List<Shapes> shapes = new ArrayList<>();
        // Define shapes for V2_B
        // Example:
        shapes.add(new Shapes(LineType.TWO_VERTICAL, 1, 5, 0, 0));
        shapes.add(new Shapes(LineType.TWO_HORIZONTAL, 1, 2, 1, 0));
        return shapes;
    }
}
