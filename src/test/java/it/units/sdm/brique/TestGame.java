package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void statusIsRunningAtBoardReset() {
    assertEquals(Status.RUNNING, game.getStatus());
  }

  @Test
  void addStoneMakesSquareOccupied() {
    Square square = game.getGameBoard().getSquare(0,0);
    game.addStone(square);
    assert(square.getStone().isPresent());
  }

  @Test
  void testStatusWhenWhiteWins(){
    List<Square> squareList = new ArrayList<>();
    Square s1 = new Square(1, 0); squareList.add(s1);
    Square s2 = new Square(1,1); squareList.add(s2);
    Square s3 = new Square(1,2);squareList.add(s3);
    Square s4 = new Square(1,3);squareList.add(s4);
    Square s5 = new Square(1,4);squareList.add(s5);
    Square s6 = new Square(1,5);squareList.add(s6);
    Square s7 = new Square(1,6);squareList.add(s7);
    Square s8 = new Square(1,7);squareList.add(s8);
    Square s9 = new Square(1,8);squareList.add(s9);
    Square s10 = new Square(1,9);squareList.add(s10);
    Square s11 = new Square(1,10);squareList.add(s11);
    Square s12 = new Square(1,11);squareList.add(s12);
    Square s13 = new Square(1,12);squareList.add(s13);
    Square s14 = new Square(1,13);squareList.add(s14);
    Square s15 = new Square(1,14);//squareList.add(s15);

    game.switchActivePlayer();
    for (int i=0; i<15;i++) {
      System.out.println(game.getActivePlayer());
      game.addStone(board.getSquare(1,i));
      game.switchActivePlayer();
    }
    System.out.println(game.getActivePlayer());
    game.addStone(s15);
      assertEquals(Status.WHITE_WINS, game.getStatus());
  }
}