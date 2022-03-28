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
  void getUp() {
    assertEquals(board.getSquare(0, 1), board.getUp(board.getSquare(1, 1)).get());
  }

  @Test
  void getDown() {
    assertEquals(board.getSquare(2, 1), board.getDown(board.getSquare(1, 1)).get());
  }

  @Test
  void getLeft() {
    assertEquals(board.getSquare(1, 0), board.getLeft(board.getSquare(1, 1)).get());
  }

  @Test
  void getRight() {
    assertEquals(board.getSquare(1, 2), board.getRight(board.getSquare(1, 1)).get());
  }

  @Test
  void getDownLeft() {
    assertEquals(board.getSquare(2, 0), board.getDownLeft(board.getSquare(1, 1)).get());
  }

  @Test
  void getUpRight() {
    assertEquals(board.getSquare(0, 2), board.getUpRight(board.getSquare(1, 1)).get());
  }

  @Test
  void getUpReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getUp(board.getSquare(0, 0)));
  }

  @Test
  void getDownReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getDown(board.getSquare(14, 0)));
  }

  @Test
  void getRightReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getRight(board.getSquare(0, 14)));
  }

  @Test
  void getLeftReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getLeft(board.getSquare(0, 0)));
  }

  @Test
  void getUpRightReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getUpRight(board.getSquare(0, 0)));
  }

  @Test
  void getDownLeftReturnsOptionalEmpty() {
    assertEquals(Optional.empty(), board.getDownLeft(board.getSquare(0, 0)));
  }
}
