package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import javafx.scene.shape.Ellipse;

public class GraphicStone extends Ellipse {
    private final Stone stone;

    public GraphicStone(Stone stone) {
        super(GraphicSquare.SQUARE_SIZE / 2.0, GraphicSquare.SQUARE_SIZE / 2.0,
                GraphicSquare.SQUARE_SIZE * 0.31, GraphicSquare.SQUARE_SIZE * 0.26);
        this.stone = stone;
        this.setFill(stone.getColor() == Color.WHITE ? GraphicColor.WHITE_STONE.getColor()
                : GraphicColor.BLACK_STONE.getColor());
    }
}
