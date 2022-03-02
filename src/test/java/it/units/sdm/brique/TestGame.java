package it.units.sdm.brique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
  private final Player player1 = new Player("Player1", Color.BLACK);
  private final Player player2 = new Player("Player2", Color.WHITE);
  private final Game game = new Game(player1, player2);

  @Test
  void testStatus() {
    assertEquals(Status.RUNNING, game.getStatus());
  }

  @Test
  void testAddStone() {
    game.addStone(0,0, Color.BLACK);
    assertEquals(Color.BLACK, game.getGameBoard().getSquare(0, 0).getStone().getColor());
  }
}
