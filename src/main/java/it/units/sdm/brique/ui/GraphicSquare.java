package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
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
    NumberBinding squareSize = Bindings.min(pane.heightProperty(), pane.widthProperty()).divide(Board.BOARD_SIZE);
    this.heightProperty().bind(squareSize);
    this.widthProperty().bind(squareSize);
    this.xProperty().bind(squareSize.multiply(square.getX()));
    this.yProperty().bind(squareSize.multiply(square.getY()));
    this.setFill(square.getColor() == Color.BLACK ? GraphicColor.BLACK_SQUARE.getColor() : GraphicColor.WHITE_SQUARE.getColor());
    pane.getChildren().add(this);
  }
}
