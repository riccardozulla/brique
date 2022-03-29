package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Square;
import javafx.scene.shape.Rectangle;

public class GraphicSquare extends Rectangle {

    private Square square;

    public GraphicSquare(Square square) {
        this.square = square;
    }
}
