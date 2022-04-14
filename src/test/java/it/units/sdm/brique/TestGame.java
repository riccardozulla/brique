package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void resetBoard() {
    board.reset();
  }

  @Test
  void testStatus() {
    assertEquals(Status.RUNNING, game.getStatus());
  }

  @Test
  void addStone() {
    Square square = game.getGameBoard().getSquare(0,0);
    game.addStone(square);
    assert(square.getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void StonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeftSquare(square).get().getStone().isPresent());
    assertEquals(board.getLeftSquare(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void stonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDownSquare(square).get().getStone().isPresent());
    assertEquals(board.getDownSquare(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void stonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assertFalse(board.getLeftSquare(square).get().getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void stonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assertFalse(board.getDownSquare(square).get().getStone().isPresent());
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void stonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStone(board.getLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeftSquare(square).get().getStone().isPresent());
    assertEquals(board.getLeftSquare(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void stonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.addStone(board.getDownLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStone(board.getDownSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDownSquare(square).get().getStone().isPresent());
    assertEquals(board.getDownSquare(square).get().getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void stonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
    Square square = board.getSquare(x, y);
    game.switchActivePlayer();
    game.addStone(board.getDownLeftSquare(square).get());
    game.addStone(board.getLeftSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getLeftSquare(square).get().getStone().isPresent());
    assertEquals(board.getLeftSquare(square).get().getStone().get().getColor(), Color.WHITE);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void stonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
    Square square = board.getSquare(x, y);
    game.switchActivePlayer();
    game.addStone(board.getDownLeftSquare(square).get());
    game.addStone(board.getDownSquare(square).get());
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(square);
    assert(board.getDownSquare(square).get().getStone().isPresent());
    assertEquals(board.getDownSquare(square).get().getStone().get().getColor(), Color.WHITE);
  }
}