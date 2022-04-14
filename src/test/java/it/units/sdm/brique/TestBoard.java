package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBoard {
  private Board board = Board.getBoard();

  @ParameterizedTest
  @CsvSource({"0,0,WHITE", "1,1,WHITE", "2,0,WHITE", "1,0,BLACK", "0,1,BLACK", "3,0,BLACK", "14,14,WHITE"})
  void testSquareColor(int x, int y, Color color) {
    assertEquals(color, board.getSquare(x, y).getColor());
  }

  @Test
  void resetBoard() {
    board.reset();
    assertFalse(Arrays.stream(board.getSquares()).flatMap(Arrays::stream).anyMatch(square -> square.getStone().isPresent()));
  }

  @Test
  void getUpSquare() {
    assertEquals(board.getSquare(0, 1), board.getUpSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getDownSquare() {
    assertEquals(board.getSquare(2, 1), board.getDownSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getLeftSquare() {
    assertEquals(board.getSquare(1, 0), board.getLeftSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getRightSquare() {
    assertEquals(board.getSquare(1, 2), board.getRightSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getDownLeftSquare() {
    assertEquals(board.getSquare(2, 0), board.getDownLeftSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getUpRightSquare() {
    assertEquals(board.getSquare(0, 2), board.getUpRightSquare(board.getSquare(1, 1)).get());
  }

  @Test
  void getUpSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getUpSquare(board.getSquare(0, 0)));
  }

  @Test
  void getDownSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getDownSquare(board.getSquare(14, 0)));
  }

  @Test
  void getRightSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getRightSquare(board.getSquare(0, 14)));
  }

  @Test
  void getLeftSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getLeftSquare(board.getSquare(0, 0)));
  }

  @Test
  void getUpRightSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getUpRightSquare(board.getSquare(0, 0)));
  }

  @Test
  void getDownLeftSquareReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getDownLeftSquare(board.getSquare(0, 0)));
  }
}
