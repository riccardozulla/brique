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
    game.addStone(board.getSquare(x+1, y-1));
    game.addStoneAndCheckEscortRule(board.getSquare(x, y));
    assert(board.getSquare(x, y-1).getStone().isPresent());
    assertEquals(board.getSquare(x, y-1).getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"5, 6", "6, 9", "10, 11"})
  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
    game.addStone(board.getSquare(x+1, y-1));
    game.addStoneAndCheckEscortRule(board.getSquare(x, y));
    assert(board.getSquare(x+1, y).getStone().isPresent());
    assertEquals(board.getSquare(x+1, y).getStone().get().getColor(), Color.BLACK);
  }

  @ParameterizedTest
  @CsvSource({"8, 12", "1, 1", "5, 7"})
  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
    //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
    game.addStone(board.getSquare(x+1, y-1));
    game.switchActivePlayer();
    game.addStoneAndCheckEscortRule(board.getSquare(x, y));
    assertFalse(board.getSquare(x, y-1).getStone().isPresent());

//    game.addStone(x+1, y-1, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assertFalse(game.getPlacedStones().contains(new Stone(x,y-1,Color.BLACK)));
  }
//
//  @ParameterizedTest
//  @CsvSource({"5, 6", "6, 9", "10, 11"})
//  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
//    //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
//    game.addStone(x+1, y-1, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assertFalse(game.getPlacedStones().contains(new Stone(x+1,y,Color.BLACK)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"8, 12", "1, 1", "5, 7"})
//  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
//    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
//    game.addStone(x+1, y-1, Color.BLACK);;
//    game.addStone(x, y-1, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assert(game.getPlacedStones().contains(new Stone(x,y-1,Color.BLACK)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"5, 6", "6, 9", "10, 11"})
//  void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
//    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
//    game.addStone(x+1, y-1, Color.WHITE);
//    game.addStone(x+1, y, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assertFalse(game.getPlacedStones().contains(new Stone(x+1,y,Color.BLACK)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"8, 12", "1, 1", "5, 7"})
//  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
//    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
//    game.addStone(x+1, y-1, Color.WHITE);
//    game.addStone(x, y-1, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assertFalse(game.getPlacedStones().contains(new Stone(x,y-1,Color.BLACK)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"5, 6", "6, 9", "10, 11"})
//  void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
//    //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
//    game.addStone(x+1, y-1, Color.WHITE);
//    game.addStone(x+1,y, Color.WHITE);
//    game.addStoneAndUpdateBoard(x,y, Color.BLACK);
//    assertFalse(game.getPlacedStones().contains(new Stone(x+1,y,Color.BLACK)));
//  }
}