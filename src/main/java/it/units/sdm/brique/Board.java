package it.units.sdm.brique;

public class Board {

  private final int boardSize;
  private final Square[][] squares;

  public Board(int boardSize) {
    this.boardSize = boardSize;
    this.squares = new Square[boardSize][boardSize];
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        squares[i][j] = new Square(((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK));
      }
    }
  }

  public int getBoardSize() {
    return boardSize;
  }

  public Square[][] getSquares() {
    return squares;
  }

  public Square getSquare(int x, int y) {
    return squares[x][y];
  }

  public void printDebug(){
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if(squares[i][j].isOccupied()){
          System.out.print(" X ");
        } else {
          System.out.print(" _ ");
        }
      }
      System.out.println();
    }
  }
}
