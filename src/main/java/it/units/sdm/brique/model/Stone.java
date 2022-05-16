package it.units.sdm.brique.model;

public class Stone {
    private Color color;

    public Stone(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void switchColor() {
        color = color == Color.BLACK ? Color.WHITE : Color.BLACK;
    }

    @Override
    public String toString() {
        return "Stone{" +
                "color=" + color +
                '}';
    }
}
