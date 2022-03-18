package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//TODO: Test when a stone is in a corner

public class TestGame {
  private final Player player1 = new Player("Player1", Color.BLACK);
  private final Player player2 = new Player("Player2", Color.WHITE);
  private final Game game = new Game(player1, player2);
  private final Board board = game.getGameBoard();

  @Test
  void testStatus() {
    assertEquals(Status.RUNNING, game.getStatus());
  }

  @Test
  void testAddStone() {
    Square square = game.getGameBoard().getSquare(0,0);
    game.addStone(square);
    assert(square.getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeft(square).get().getStone().isPresent());
    assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDown(square).get().getStone().isPresent());
    assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assertFalse(board.getLeft(square).get().getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assertFalse(board.getDown(square).get().getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.switchActivePlayer();
    game.addStone(board.getLeft(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeft(square).get().getStone().isPresent());
    assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeft(square).get());
    game.switchActivePlayer();
    game.addStone(board.getDown(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDown(square).get().getStone().isPresent());
    assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.switchActivePlayer();
    game.addStone(board.getDownLeft(square).get());
    game.addStone(board.getLeft(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeft(square).get().getStone().isPresent());
    assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.WHITE);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.switchActivePlayer();
    game.addStone(board.getDownLeft(square).get());
    game.addStone(board.getDown(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDown(square).get().getStone().isPresent());
    assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.WHITE);
  }
}