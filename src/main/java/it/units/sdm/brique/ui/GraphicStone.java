package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import javafx.scene.shape.Ellipse;

public class GraphicStone extends Ellipse {
    public static final double STONE_RADIUS_X = GraphicSquare.SQUARE_SIZE * 0.31;
    public static final double STONE_RADIUS_Y = GraphicSquare.SQUARE_SIZE * 0.26;
    private final Stone stone;

    public GraphicStone(Stone stone) {
        super(GraphicSquare.SQUARE_SIZE / 2.0, GraphicSquare.SQUARE_SIZE / 2.0, STONE_RADIUS_X, STONE_RADIUS_Y);
        this.stone = stone;
        this.setFill(stone.getColor().equals(Color.WHITE) ? GraphicColor.WHITE_STONE.getColor() : GraphicColor.BLACK_STONE.getColor());
    }
}
