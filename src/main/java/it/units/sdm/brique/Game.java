package it.units.sdm.brique;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Game {
  private static final int BOARD_SIZE = 15;
  private Status status = Status.RUNNING;
  private final Player[] players;
  private final Board gameBoard;
  private List<Stone> placedStone = new ArrayList<>();
  private Player nextMove;

  public Game(Player player1, Player player2) {
    this.players = new Player[]{player1, player2};
    this.gameBoard = new Board(BOARD_SIZE);
  }

  public Status getStatus() {
    return status;
  }

  public Player getNextMove() {
    return nextMove;
  }

  public Player[] getPlayers() {
    return players;
  }

  public Board getGameBoard() {
    return gameBoard;
  }

  public List<Stone> getPlacedStone() {
    return placedStone;
  }

  public void addStone(int x, int y, Color color) {
    Stone tmp = new Stone(x,y, color);
    if(placedStone.stream().anyMatch(s->s.equalsCoordinates(tmp))){
      //todo:: raise exception
      return;
    }
    placedStone.add(tmp);
  }

  private void updateStoneColor(int x, int y, Color color) {
    Stone tmp = new Stone(x,y,color);
    if(placedStone.contains(tmp)) return;
    placedStone.remove(tmp);
    placedStone.add(tmp);
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
    return placedStone.stream().anyMatch(Predicate.isEqual(new Stone(i, j, color)));
  }

  private boolean isCoordinatesOutOfBounds(int x, int y) {
    return x < 0 || y < 0;
  }
}
