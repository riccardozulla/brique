package it.units.sdm.brique;

public class Game {
  private static final int BOARD_SIZE = 15;
  private Status status = Status.RUNNING;
  private final Player[] players;
  private final Board gameBoard;
  private Player activePlayer;

  public Game(Player player1, Player player2) {
    this.players = new Player[]{player1, player2};
    this.gameBoard = new Board(BOARD_SIZE);
  }

  public Status getStatus() {
    return status;
  }

  public Player getActivePlayer() {
    return activePlayer;
  }

  public Player[] getPlayers() {
    return players;
  }

  public Board getGameBoard() {
    return gameBoard;
  }

  public void addStone(int x, int y, Color color) {
    Square tmp = gameBoard.getSquare(x, y);
    if (tmp.getIsOccupied()) {
      //todo: add exception
      return;
    }
    tmp.setStone(new Stone(color));
    tmp.toggleSquareOccupied();
  }

  private void updateStoneColor(int x, int y, Color color) {
    Square tmp = gameBoard.getSquare(x, y);
    if (!tmp.getIsOccupied()) {
      addStone(x, y, color);
    } else if (tmp.getStone().getColor() != color) {
      tmp.getStone().setColor(color);
    }
  }

  public void addStoneAndUpdateBoard(int x, int y, Color color) {
    if (isCoordinatesOutOfBounds(x, y)) {
      System.out.println("CoordinatesOutOfBounds: " + x + "," + y);
      return;
      //todo: raise exception
    }
    addStone(x, y, color);
    updateBoard(x, y, color);
  }

  private void updateBoard(int x, int y, Color color) {
    if (checkDiagonalStone(x + 1, y - 1, color)) {
      if (getGameBoard().getSquare(x, y).getColor() == Color.BLACK) {
        updateStoneColor(x + 1, y, color);
      } else {
        updateStoneColor(x, y - 1, color);
      }
    }
    if (checkDiagonalStone(x - 1, y + 1, color)) {
      if (getGameBoard().getSquare(x, y).getColor() == Color.BLACK) {
        updateStoneColor(x, y + 1, color);
      } else {
        updateStoneColor(x - 1, y, color);
      }
    }
  }

  private boolean checkDiagonalStone(int i, int j, Color color) {
    if (isCoordinatesOutOfBounds(i, j)) return false;
    Square square = getGameBoard().getSquare(i, j);
    if (!square.getIsOccupied()) return false;
    return square.getStone().getColor() == color;
  }

  private boolean isCoordinatesOutOfBounds(int x, int y) {
    return x < 0 || y < 0;
  }
}
