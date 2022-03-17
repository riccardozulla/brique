package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: Test when a stone is in a corner

public class TestGame {
  private final Player player1 = new Player("Player1", Color.BLACK);
  private final Player player2 = new Player("Player2", Color.WHITE);
  private final Game game = new Game(player1, player2);

  @Test
  void testStatus() {
    assertEquals(Status.RUNNING, game.getStatus());
  }

  @Test
  void initializeActivePLayer() {
    assertEquals(player1, game.getActivePlayer());
  }

  @Test
  void switchActivePLayer() {
    game.switchActivePlayer();
    assertEquals(player2, game. getActivePlayer());
  }

  @Test
  void testAddStone() {
    game.addStone(0,0, Color.BLACK);
    assertEquals(Color.BLACK, game.getGameBoard().getSquare(0, 0).getStone().getColor());
  }

  @Test
  void testChangeStoneColor() {
    game.addStone(14,14, Color.BLACK);
    assertEquals(Color.BLACK, game.getGameBoard().getSquare(14, 14).getStone().getColor());
    game.getGameBoard().getSquare(14,14).getStone().setColor(Color.WHITE);
    assertEquals(Color.WHITE, game.getGameBoard().getSquare(14, 14).getStone().getColor());
  }

  @Test
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndSquareIsFree() {
    //Precondition: The square near the occupied escort is free
    game.addStone(1,0, Color.BLACK);
    game.addStoneAndUpdateBoard(0,1, Color.BLACK);
    assertEquals(true, game.getGameBoard().getSquare(1,1).getIsOccupied());
  }

  @Test
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndSquareIsFree() {
    //Precondition: The square near the occupied escort is free
    game.addStone(1,0, Color.BLACK);
    game.addStoneAndUpdateBoard(0,1, Color.WHITE);
    assertEquals(false, game.getGameBoard().getSquare(1,1).getIsOccupied());
  }

  @Test
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndSquareIsOccupied() {
    game.addStone(1, 0, Color.BLACK);
    game.addStone(1, 1, Color.WHITE);
    game.addStoneAndUpdateBoard(0,1, Color.BLACK);
    assertEquals(Color.WHITE, game.getGameBoard().getSquare(1, 1).getColor());
  }


  @ParameterizedTest
  @CsvSource({"8, 12"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free
    game.addStone(x+1, y-1, Color.BLACK);
    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
    System.out.println(game.getGameBoard().getSquare(x, y).getStone().getColor());
    assertEquals(true, game.getGameBoard().getSquare(x,y-1).getIsOccupied());
  }
}