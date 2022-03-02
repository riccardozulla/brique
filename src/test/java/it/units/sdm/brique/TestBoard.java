package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoard {
  private static final int SIZE = 16;
  private final Board board = new Board(SIZE);

  @Test
  void testGetterBoardSize() {
    assertEquals(SIZE, board.getBoardSize());
  }

  @ParameterizedTest
  @CsvSource({"0,0,WHITE", "1,1,WHITE", "2,0,WHITE", "1,0,BLACK", "0,1,BLACK", "3,0,BLACK", "15,15,WHITE"})
  void testSquareColor(int x, int y, Color color) {
    assertEquals(color, board.getSquare(x, y).getColor());
  }

}
