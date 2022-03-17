package it.units.sdm.brique;

public class Board {

  private static final int BOARD_SIZE = 15;
  private final Square[][] squares;

  public Board() {
    this.squares = new Square[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        squares[i][j] = new Square(((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK));
      }
    }
  }

  public int getBoardSize() {
    return BOARD_SIZE;
  }

  public Square[][] getSquares() {
    return squares;
  }

  public Square getSquare(int x, int y) {
    return squares[x][y];
  }

  public void printDebug(){
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if(squares[i][j].getIsOccupied()){
          System.out.print(" X ");
        } else {
          System.out.print(" _ ");
        }
      }
      System.out.println();
    }
  }
}
