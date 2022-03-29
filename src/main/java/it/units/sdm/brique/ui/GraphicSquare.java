package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class GraphicSquare extends Rectangle implements Drawable {

    private Square square;

    public GraphicSquare(Square square) {
        super();
        this.square = square;
    }

    @Override
    public void draw(Pane pane) {
        double squareSize = pane.getHeight() / Board.getBoard().BOARD_SIZE;
        this.setHeight(squareSize);
        this.setWidth(squareSize);
        this.setX(square.getX() * squareSize);
        this.setY(square.getY() * squareSize);
        this.setFill(square.getColor() == Color.BLACK ? GraphicColor.BLACK_SQUARE.getColor() : GraphicColor.WHITE_SQUARE.getColor());
    }
}
