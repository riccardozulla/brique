package it.units.sdm.brique.ui;

import it.units.sdm.brique.model.Board;
import javafx.scene.shape.Box;

public class GraphicBoard extends Box {

  Board board;

  public GraphicBoard() {
    this.board = Board.getBoard();
  }
}