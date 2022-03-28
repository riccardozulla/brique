package it.units.sdm.brique;

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

  public Square[][] getSquares() {
    return squares;
  }

  public Square getSquare(int x, int y) {
    return squares[x][y];
  }

  private Optional<Square> getOptionalSquare(int x, int y) {
    try {
      return Optional.of(getSquare(x, y));
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
