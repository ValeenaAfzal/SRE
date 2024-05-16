package Refactored_Gui;
import java.util.ArrayList;
import java.util.List;

public class Transformers {
    private final List<Mapper> mapping = new ArrayList<>();
    private final List<Mapper> v1 = new ArrayList<>();
    private final List<Mapper> v2 = new ArrayList<>();
    private int version = 1;

    public Transformers() {
        initializeMappings();
        initializeVersions();
    }

    public void switchVersion() {
        version = (version % 2) + 1;
    }

    public List<Mapper> getMapping() {
        return mapping;
    }

    public List<Mapper> getVersion() {
        return (version == 2) ? v2 : v1;
    }

    private void initializeMappings() {
        MappingInitializer initializer = new MappingInitializer();
        initializer.initialize(mapping);
    }

    private void initializeVersions() {
        VersionInitializer versionInitializer;
        versionInitializer = new VersionInitializer();
        versionInitializer.initialize(v1, v2);
    }

    static class Mapper {
        public String letter;
        public List<Shapes> shapes = new ArrayList<>();
        int add = 2;

        public Mapper(String l, List<Shapes> s, int a) {
            letter = l;
            shapes = s;
            add = a;
        }

        public List<Shapes> getShapes() {
            return shapes;
        }

        public String getLetter() {
            return letter;
        }

        public int getAdd() {
            return add;
        }
    }
}
