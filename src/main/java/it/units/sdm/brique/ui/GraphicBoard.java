package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public class GraphicBoard extends Pane implements Drawable {

  Board board;
  GraphicSquare[][] graphicSquares;

  public GraphicBoard() {
    this.board = Board.getBoard();
    graphicSquares = new GraphicSquare[Board.BOARD_SIZE][Board.BOARD_SIZE];
    for (int i = 0; i < Board.BOARD_SIZE; i++) {
      for (int j = 0; j < Board.BOARD_SIZE; j++) {
        this.getChildren().add(new GraphicSquare(board.getSquare(i, j)));
      }
    }
  }

  public void draw(Pane pane) {
    this.getChildren().forEach(graphicSquare -> ((GraphicSquare) graphicSquare).draw(pane));
    pane.getChildren().add(this);
  }
}