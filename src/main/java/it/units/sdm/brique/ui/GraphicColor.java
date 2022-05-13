package it.units.sdm.brique.ui;

import javafx.scene.paint.Paint;

public enum GraphicColor {
    WHITE_SQUARE("#e0be98"), BLACK_SQUARE("#8a4929"), WHITE_STONE("#ede6e6"), BLACK_STONE("#272b28");

    private final String hexColor;

    GraphicColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getHexColor() {
        return hexColor;
    }

    public Paint getColor() {
        return Paint.valueOf(hexColor);
    }
}
