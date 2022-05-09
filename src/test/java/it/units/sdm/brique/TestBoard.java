package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
  private final Board board = Board.getBoard();

  @Test
  void numberOfSquaresIsTheExpectedOne(){
    assertEquals(Board.BOARD_SIZE*Board.BOARD_SIZE, board.getSquaresStream().count());
  }

  @ParameterizedTest
  @CsvSource({"0,0,WHITE", "1,1,WHITE", "2,0,WHITE", "1,0,BLACK", "0,1,BLACK", "3,0,BLACK", "14,14,WHITE"})
  void squareColorIsTheExpectedOne(int x, int y, Color color) {
    assertEquals(color, board.getSquare(x, y).getColor());
  }

  @Test
  void resetBoard() {
    board.reset();
    assertFalse(board.getSquaresStream().anyMatch(Square::isOccupied));
  }
}
