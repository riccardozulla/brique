package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GraphicSquare extends StackPane implements Drawable {

    private final Square square;

    public GraphicSquare(Square square) {
        super();
        this.square = square;
    }

    @Override
    public void draw(Pane pane) {
        NumberBinding squareSize = Bindings.min(pane.heightProperty(), pane.widthProperty()).divide(Board.BOARD_SIZE);
        this.prefHeightProperty().bind(squareSize);
        this.prefWidthProperty().bind(squareSize);
        this.layoutXProperty().bind(squareSize.multiply(square.getX()));
        this.layoutYProperty().bind(squareSize.multiply(square.getY()));
        this.setStyle("-fx-background-color:" + (square.getColor() == Color.BLACK ? GraphicColor.BLACK_SQUARE.getHexColor() : GraphicColor.WHITE_SQUARE.getHexColor()));
    }

    private void eraseStone() {
        this.getChildren().clear();
    }

    private void drawStone() {
        square.getStone().ifPresent(stone -> {
            GraphicStone graphicStone = new GraphicStone(stone);
            graphicStone.draw(this);
            this.getChildren().add(graphicStone);
        });
    }

    public void update() {
        eraseStone();
        drawStone();
    }
}
