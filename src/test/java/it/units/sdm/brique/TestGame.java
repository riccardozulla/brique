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
  void testAddStone() {
    Square square = game.getGameBoard().getSquare(0,0);
    game.addStone(square);
    assert(square.getStone().isPresent());
  }
}