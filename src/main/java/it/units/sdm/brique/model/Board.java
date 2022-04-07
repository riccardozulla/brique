package it.units.sdm.brique.model;

import java.util.Arrays;
import java.util.Optional;

public class Board {

  private static Board boardInstance;

  public static final int BOARD_SIZE = 15;
  public static final int FIRST_INDEX = 0;
  public static final int LAST_INDEX = BOARD_SIZE -1;
  private final Square[][] squares;

  private Board() {
    this.squares = new Square[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        squares[i][j] = new Square(i, j);
      }
    }
  }

  public static Board getBoard() {
    if (boardInstance == null) boardInstance = new Board();
    return boardInstance;
  }

  public void reset(){
    Arrays.stream(squares).flatMap(Arrays::stream).forEach(square -> square.setStone(null));
  }

  public Square[][] getSquares() {
    return squares;
  }

  public Square getSquare(int i, int j) {
    return squares[i][j];
  }

  private Optional<Square> getOptionalSquare(int i, int j) {
    try {
      return Optional.of(getSquare(i, j));
    } catch (ArrayIndexOutOfBoundsException exception) {
      return Optional.empty();
    }
  }

  public Optional<Square> getUp(Square square) {
    return getOptionalSquare(square.getX() - 1, square.getY());

  }

  public Optional<Square> getDown(Square square) {
    return getOptionalSquare(square.getX() + 1, square.getY());

  }

  public Optional<Square> getLeft(Square square) {
    return getOptionalSquare(square.getX(), square.getY() - 1);

  }

  public Optional<Square> getRight(Square square) {
    return getOptionalSquare(square.getX(), square.getY() + 1);

  }

  public Optional<Square> getUpRight(Square square) {
    return getOptionalSquare(square.getX() - 1, square.getY() + 1);

  }

  public Optional<Square> getDownLeft(Square square) {
    return getOptionalSquare(square.getX() + 1, square.getY() - 1);

  }

  public static boolean isCoordinatesOutOfBounds(int x, int y) {
    return x < FIRST_INDEX || y < FIRST_INDEX || x > LAST_INDEX || y > LAST_INDEX;
  }

}
