package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import javafx.scene.shape.Ellipse;

public class GraphicStone extends Ellipse {
    Stone stone;
    public GraphicStone(Stone stone) {
        this.stone = stone;
//        this.setFill(stone.getColor() == Color.WHITE ? GraphicColor.WHITE_STONE.getColor()
//                : GraphicColor.BLACK_STONE.getColor());
    }
}
