package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

  @ParameterizedTest
  @CsvSource({"2,1,1,1","3,2,2,2", "4,3,3,3", "5,4,4,4","6,5,5,5", "7,6,6,6", "8,7,7,7", "9,8,8,8", "10,9,9,9", "11,10,10,10", "12,11,11,11"})
  void getDown(int actualRow, int actualCol, int downRow, int downCol) {
    assertEquals(board.getSquare(actualRow, actualCol), board.getDown(board.getSquare(downRow, downCol)).get());
  }

  @ParameterizedTest
  @CsvSource({"1,0,1,1","2,1,2,2", "3,2,3,3", "4,3,4,4","5,4,5,5", "6,5,6,6", "7,6,7,7", "8,7,8,8", "9,8,9,9", "10,9,10,10", "11,10,11,11"})
  void getLeft(int actualRow, int actualCol, int rightRow, int rightCol) {
    assertEquals(board.getSquare(actualRow, actualCol), board.getLeft(board.getSquare(rightRow, rightCol)).get());
  }

  @ParameterizedTest
  @CsvSource({"1,2,1,1","2,3,2,2", "3,4,3,3", "4,5,4,4","5,6,5,5", "6,7,6,6", "7,8,7,7", "8,9,8,8", "9,10,9,9", "10,11,10,10", "11,12,11,11"})
          void getRight(int actualRow, int actualCol, int rightRow, int rightCol) {
    assertEquals(board.getSquare(actualRow, actualCol), board.getRight(board.getSquare(rightRow, rightCol)).get());
  }

  @ParameterizedTest
  @CsvSource({"2,0,1,1", "3,1,2,2", "4,2,3,3", "5,3,4,4","6,4,5,5", "7,5,6,6", "8,6,7,7", "9,7,8,8", "9,7,8,8", "10,8,9,9", "11,9,10,10"})
  void getDownLeft(int actualRow, int actualCol, int downLeftRow, int downLeftCol) {
    assertEquals(board.getSquare(actualRow, actualCol), board.getDownLeft(board.getSquare(downLeftRow, downLeftCol)).get());
  }

  @ParameterizedTest
  @CsvSource({"0,2,1,1", "1,1,2,0", "3,1,4,0", "4,2,5,1","5,3,6,2", "6,4,7,3", "7,5,8,4", "8,6,9,5", "9,7,10,6", "10,8,11,7", "11,9,12,8"})
  void getUpRight(int actualRow, int actualCol, int upRightRow, int upRightCol) {
    assertEquals(board.getSquare(actualRow, actualCol), board.getUpRight(board.getSquare(upRightRow, upRightCol)).get());
  }

  @ParameterizedTest
  @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14"})
  void getUpReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getUp(board.getSquare(i,j)));
  }

  @ParameterizedTest
  @CsvSource({"14,0", "14,1", "14,2", "14,3", "14,4", "14,5", "14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
  void getDownReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getDown(board.getSquare(i, j)));
  }

  @ParameterizedTest
  @CsvSource({"0,14", "1,14", "2,14", "3,14", "4,14", "5,14", "6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
  void getRightReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getRight(board.getSquare(i, j)));
  }

  @ParameterizedTest
  @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0"})
  void getLeftReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getLeft(board.getSquare(i, j)));
  }

  @ParameterizedTest
  @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14","1,14", "2,14", "3,14", "4,14", "5,14", "6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
  void getUpRightReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getUpRight(board.getSquare(i, j)));
  }

  @ParameterizedTest
  @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0","14,1", "14,2", "14,3", "14,4", "14,5", "14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
  void getDownLeftReturnsOptionalEmpty(int i, int j) {
    assertEquals(Optional.empty(), board.getDownLeft(board.getSquare(i, j)));
  }
}
