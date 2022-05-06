package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {

    private final Board board = Board.getBoard();
    private final Square defaultSquare = generateMockSquare(0, 0);

    private Square generateMockSquare(int row, int column) {
        return new Square(row, column) {
            @Override
            public Color getColor() {
                return null;
            }

            @Override
            public List<Square> getEscorts() {
                return null;
            }
        };
    }

    @Test
    void isOccupiedAfterStonePlacement() {
        defaultSquare.setStone(new Stone(Color.BLACK));
        assert (defaultSquare.isOccupied());
    }

    @Test
    void isEmptyByDefault() {
        assertFalse(defaultSquare.isOccupied());
    }

    @Test
    void isEmptyAfterStoneRemoval() {
        defaultSquare.setStone(new Stone(Color.BLACK));
        defaultSquare.removeStone();
        assertFalse(defaultSquare.isOccupied());
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,2", "2,2,2,3", "3,3,3,4", "4,4,4,5", "5,5,5,6", "6,6,6,7", "7,7,7,8", "8,8,8,9", "9,9,9,10", "10,10,10,11"})
    void manhattanDistanceBetweenAdjacentOrthogonalSquareIsOne(int i, int j, int k, int l) {
        assertEquals(1, Square.manhattanSquareDistance(generateMockSquare(i, j), generateMockSquare(k, l)));
    }

    @ParameterizedTest
    @CsvSource({"0,14", "1,14", "2,14", "3,14", "4,14", "5,14", "6,14", "7,14", "8,14", "9,14", "10,14", "11,14", "12,14", "13,14", "14,14"})
    void lastColSquaresAreOnTheRightEdge(int i, int j) {
        Square lastColSquare = generateMockSquare(i, j);
        assertTrue(lastColSquare.isRightEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0", "7,0", "8,0", "9,0", "10,0", "11,0", "12,0", "13,0", "14,0"})
    void firstColSquaresAreOnTheLeftEdge(int i, int j) {
        Square firstColSquare = generateMockSquare(i, j);
        assertTrue(firstColSquare.isLeftEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "0,10", "0,11", "0,12", "0,13", "0,14"})
    void firstRowSquaresAreOnTheTopEdge(int i, int j) {
        Square firstRowSquare = generateMockSquare(i, j);
        assertTrue(firstRowSquare.isTopEdge());
    }

    @ParameterizedTest
    @CsvSource({"14,0", "14,1", "14,2", "14,3", "14,4", "14,5", "14,6", "14,7", "14,8", "14,9", "14,10", "14,11", "14,12", "14,13", "14,14"})
    void lastRowSquaresAreOnTheBottomEdge(int i, int j) {
        Square lastRowSquare = generateMockSquare(i, j);
        assertTrue(lastRowSquare.isBottomEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,14", "1,14", "2,14", "3,14", "4,14", "5,14", "6,14", "7,14", "8,14", "9,14", "10,14", "11,14", "12,14", "13,14", "14,14"})
    void getColumnOnRightEdgeSquareReturnsExpectedValue(int i, int j) {
        Square lastColSquare = generateMockSquare(i, j);
        assertEquals(14, lastColSquare.getColumn());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0", "7,0", "8,0", "9,0", "10,0", "11,0", "12,0", "13,0", "14,0"})
    void getColumnOnLeftEdgeSquareReturnsExpectedValue(int i, int j) {
        Square firstColSquare = generateMockSquare(i, j);
        assertEquals(0, firstColSquare.getColumn());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "0,10", "0,11", "0,12", "0,13", "0,14"})
    void getRowOnTopEdgeSquareReturnsExpectedValue(int i, int j) {
        Square firstRowSquare = generateMockSquare(i, j);
        assertEquals(0, firstRowSquare.getRow());
    }

    @ParameterizedTest
    @CsvSource({"14,0", "14,1", "14,2", "14,3", "14,4", "14,5", "14,6", "14,7", "14,8", "14,9", "14,10", "14,11", "14,12", "14,13", "14,14"})
    void getRowOnBottomEdgeSquareReturnsExpectedValue(int i, int j) {
        Square lastRowSquare = generateMockSquare(i, j);
        assertEquals(14, lastRowSquare.getRow());
    }

    @ParameterizedTest
    @CsvSource({"0,1,1,1","1,2,2,2", "2,3,3,3", "3,4,4,4","4,5,5,5", "5,6,6,6", "6,7,7,7", "7,8,8,8", "8,9,9,9", "9,10,10,10", "10,11,11,11"})
    void getUpSquare(int actualRow, int actualCol, int upRow, int upCol) {
        assertEquals(board.getSquare(actualRow, actualCol), board.getSquare(upRow, upCol).getUpSquare());
    }

    @ParameterizedTest
    @CsvSource({"2,1,1,1","3,2,2,2", "4,3,3,3", "5,4,4,4","6,5,5,5", "7,6,6,6", "8,7,7,7", "9,8,8,8", "10,9,9,9", "11,10,10,10", "12,11,11,11"})
    void getDownSquare(int actualRow, int actualCol, int downRow, int downCol) {
        assertEquals(board.getSquare(actualRow, actualCol), board.getSquare(downRow, downCol).getDownSquare());
    }

    @ParameterizedTest
    @CsvSource({"1,0,1,1","2,1,2,2", "3,2,3,3", "4,3,4,4","5,4,5,5", "6,5,6,6", "7,6,7,7", "8,7,8,8", "9,8,9,9", "10,9,10,10", "11,10,11,11"})
    void getLeftSquare(int actualRow, int actualCol, int rightRow, int rightCol) {
        assertEquals(board.getSquare(actualRow, actualCol), board.getSquare(rightRow, rightCol).getLeftSquare());
    }

    @ParameterizedTest
    @CsvSource({"1,2,1,1","2,3,2,2", "3,4,3,3", "4,5,4,4","5,6,5,5", "6,7,6,6", "7,8,7,7", "8,9,8,8", "9,10,9,9", "10,11,10,10", "11,12,11,11"})
    void getRightSquare(int actualRow, int actualCol, int rightRow, int rightCol) {
        assertEquals(board.getSquare(actualRow, actualCol), board.getSquare(rightRow, rightCol).getRightSquare());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14"})
    void getUpSquareThrowsExceptionIfSquareIsOnTopEdge(int i, int j) {
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getSquare(i,j).getUpSquare());
    }

    @ParameterizedTest
    @CsvSource({"14,0", "14,1", "14,2", "14,3", "14,4", "14,5", "14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
    void getDownSquareThrowsExceptionIfSquareIsOnBottomEdge(int i, int j) {
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getSquare(i, j).getDownSquare());
    }

    @ParameterizedTest
    @CsvSource({"0,14", "1,14", "2,14", "3,14", "4,14", "5,14", "6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
    void getRightSquareThrowsExceptionIfSquareIsOnRightEdge(int i, int j) {
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getSquare(i, j).getRightSquare());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0"})
    void getLeftSquareThrowsExceptionIfSquareIsOnLeftEdge(int i, int j) {
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> board.getSquare(i, j).getLeftSquare());
    }

}
