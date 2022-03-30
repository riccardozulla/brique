package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

public class GraphicStone extends Ellipse implements Drawable {
    Stone stone;

    public GraphicStone(Stone stone) {
        this.stone = stone;
    }

    @Override
    public void draw(Pane pane) {
        NumberBinding squareSize = Bindings.min(pane.heightProperty(), pane.widthProperty()).divide(Board.BOARD_SIZE);
        this.radiusXProperty().bind(squareSize.multiply(0.31));
        this.radiusYProperty().bind(squareSize.multiply(0.26));
        this.setFill(stone.getColor() == Color.WHITE ? GraphicColor.WHITE_STONE.getColor()
                : GraphicColor.BLACK_STONE.getColor());
        pane.getChildren().add(this);
    }
}
