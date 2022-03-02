package it.units.sdm.brique;

public class Board {

  private final int boardSize;
  private final Square[][] squares;

  public Board(int boardSize) {
    this.boardSize = boardSize;
    this.squares = new Square[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        squares[i][j] = new Square(new Coordinate(i, j), ((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK));
      }
    }
  }

  public int getBoardSize() {
    return boardSize;
  }

  public Square[][] getSquares() {
    return squares;
  }

  public Square getSquare(Coordinate coordinate) {
    return squares[coordinate.getX()][coordinate.getY()];
  }
}
