package Refactored_Gui;

enum LineType {
    HORIZONTAL,
    VERTICAL,
    TWO_HORIZONTAL,
    TWO_VERTICAL;
}

public class Shapes {
    private final LineType line;
    private final int minThickness;
    private final int maxThickness;
    private final int xPlus;
    private final int yPlus;
    private final int inBetweenDistance;
    private final int dilation;
    private final boolean normalDilation;

    public static class Builder {
        private final LineType line;
        private int minThickness = 2;
        private int maxThickness = 5;
        private int xPlus = 0;
        private int yPlus = 0;
        private int inBetweenDistance = 0;
        private int dilation = 1;
        private boolean normalDilation = true;

        public Builder(LineType line) {
            this.line = line;
        }

        public Builder minThickness(int minThickness) {
            this.minThickness = minThickness;
            return this;
        }

        public Builder maxThickness(int maxThickness) {
            this.maxThickness = maxThickness;
            return this;
        }

        public Builder xPlus(int xPlus) {
            this.xPlus = xPlus;
            return this;
        }

        public Builder yPlus(int yPlus) {
            this.yPlus = yPlus;
            return this;
        }

        public Builder inBetweenDistance(int inBetweenDistance) {
            this.inBetweenDistance = inBetweenDistance;
            return this;
        }

        public Builder dilation(int dilation) {
            this.dilation = dilation;
            return this;
        }

        public Builder normalDilation(boolean normalDilation) {
            this.normalDilation = normalDilation;
            return this;
        }

        public Shapes build() {
            return new Shapes(this);
        }
    }

    private Shapes(Builder builder) {
        this.line = builder.line;
        this.minThickness = builder.minThickness;
        this.maxThickness = builder.maxThickness;
        this.xPlus = builder.xPlus;
        this.yPlus = builder.yPlus;
        this.inBetweenDistance = builder.inBetweenDistance;
        this.dilation = builder.dilation;
        this.normalDilation = builder.normalDilation;
    }

    // Getters for the fields
    public LineType getLine() {
        return line;
    }

    public int getMinThickness() {
        return minThickness;
    }

    public int getMaxThickness() {
        return maxThickness;
    }

    public int getXPlus() {
        return xPlus;
    }

    public int getYPlus() {
        return yPlus;
    }

    public int getInBetweenDistance() {
        return inBetweenDistance;
    }

    public int getDilation() {
        return dilation;
    }

    public boolean isNormalDilation() {
        return normalDilation;
    }
}
