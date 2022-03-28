package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;

public class Game {
  private Status status = Status.RUNNING;
  private final Player player1;
  private final Player player2;
  private final Board gameBoard;
  private Player activePlayer;

  public Game(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.gameBoard = Board.getBoard();
    if (player1.getStoneColor() == Color.BLACK) {
      activePlayer = player1;
    } else {
      activePlayer = player2;
    }
  }

  public Status getStatus() {
    return status;
  }

  public Player getActivePlayer() {
    return activePlayer;
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public Board getGameBoard() {
    return gameBoard;
  }

  public void switchActivePlayer() {
    activePlayer = activePlayer.equals(player1) ? player2 : player1;
  }

  public void addStone(Square square) {
    square.getStone().ifPresentOrElse(s -> {
      throw new StoneAlreadyPresentException("Stone already present in the given square!");
    }, () -> square.setStone(new Stone(activePlayer.getStoneColor())));
  }

  private void addOrReplaceStone(Square square) {
    square.getStone().ifPresentOrElse(
            stone -> stone.setColor(activePlayer.getStoneColor()),
            () -> addStone(square));
  }

  public void addStoneAndCheckEscortRule(Square square) {
    addStone(square);
    checkEscortRule(square);
  }

  private void checkEscortRule(Square square) {
    gameBoard.getDownLeft(square).flatMap(Square::getStone).ifPresent(stone -> {
      if (stoneBelongsToActivePlayer(stone)) {
        if (square.getColor() == Color.WHITE) {
          addOrReplaceStone(gameBoard.getLeft(square).get()); //left square always exits
        } else {
          addOrReplaceStone(gameBoard.getDown(square).get());
        }
      }
    });
    gameBoard.getUpRight(square).flatMap(Square::getStone).ifPresent(stone -> {
      if (stoneBelongsToActivePlayer(stone)) {
        if (square.getColor() == Color.WHITE) {
          addOrReplaceStone(gameBoard.getUp(square).get());
        } else {
          addOrReplaceStone(gameBoard.getRight(square).get());
        }
      }
    });
  }

  private boolean stoneBelongsToActivePlayer(Stone stone) {
    return stone.getColor() == activePlayer.getStoneColor();
  }
}