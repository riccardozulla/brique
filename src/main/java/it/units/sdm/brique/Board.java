package it.units.sdm.brique;

public class Board {

  private static final int BOARD_SIZE = 15;
  private final Square[][] squares;

  public Board() {
    this.squares = new Square[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        squares[i][j] = new Square(new Coordinate(i,j), (i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
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

  public Square getUp(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos-1][yPos];
  }
  //TODO: The following methods should check if a stone is on the edge

  public Square getDown(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos+1][yPos];
  }

  public Square getLeft(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos][yPos-1];
  }

  public Square getRight(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos][yPos+1];
  }

  public Square getUpRight(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos-1][yPos+1];
  }

  public Square getDownLeft(Square square) {
    int xPos = square.getCoordinate().getX();
    int yPos = square.getCoordinate().getY();
    return squares[xPos+1][yPos-1];
  }

  public static boolean isCoordinatesOutOfBounds(Coordinate coordinate) {
    int x = coordinate.getX();
    int y = coordinate.getY();
    return x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE;
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
