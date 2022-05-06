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
  void getSquaresLengthReturnsExpectedDimensions(){
    assertEquals(board.getSquares().length, Board.BOARD_SIZE);
  }

  @ParameterizedTest
  @CsvSource({"0,0,WHITE", "1,1,WHITE", "2,0,WHITE", "1,0,BLACK", "0,1,BLACK", "3,0,BLACK", "14,14,WHITE"})
  void testSquareColor(int x, int y, Color color) {
    assertEquals(color, board.getSquare(x, y).getColor());
  }

  @Test
  void resetBoard() {
    board.reset();
    assertFalse(board.getSquaresStream().anyMatch(Square::isOccupied));
  }

//TODO: delete tests

//  @ParameterizedTest
//  @CsvSource({"2,0,1,1", "3,1,2,2", "4,2,3,3", "5,3,4,4","6,4,5,5", "7,5,6,6", "8,6,7,7", "9,7,8,8", "9,7,8,8", "10,8,9,9", "11,9,10,10"})
//  void getDownLeftSquare(int actualRow, int actualCol, int downLeftRow, int downLeftCol) {
//    assertEquals(board.getSquare(actualRow, actualCol), board.getDownLeftSquare(board.getSquare(downLeftRow, downLeftCol)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"0,2,1,1", "1,1,2,0", "3,1,4,0", "4,2,5,1","5,3,6,2", "6,4,7,3", "7,5,8,4", "8,6,9,5", "9,7,10,6", "10,8,11,7", "11,9,12,8"})
//  void getUpRightSquare(int actualRow, int actualCol, int upRightRow, int upRightCol) {
//    assertEquals(board.getSquare(actualRow, actualCol), board.getUpRightSquare(board.getSquare(upRightRow, upRightCol)));
//  }
//
//
//  @ParameterizedTest
//  @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14","1,14", "2,14", "3,14", "4,14", "5,14", "6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
//  void getUpRightSquareThrowsExceptionIfSquareIsOnTopOrRightEdge(int i, int j) {
//    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getUpRightSquare(board.getSquare(i, j)));
//  }
//
//  @ParameterizedTest
//  @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0","14,1", "14,2", "14,3", "14,4", "14,5", "14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
//  void getDownLeftSquareThrowsExceptionIfSquareIsOnLeftOrBottomEdge(int i, int j) {
//    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getDownLeftSquare(board.getSquare(i, j)));
//  }
}
