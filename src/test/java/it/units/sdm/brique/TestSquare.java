package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {
    private final Square square = new Square(0,0);

    @Test
    void settingStoneOnSquareMakesGetStonePresent() {
        square.setStone(new Stone(Color.BLACK));
        assert(square.isOccupied());
    }

    @Test
    void emptySquareHasNotStoneOnIt() {
        assertFalse(square.isOccupied());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,1", "2,2","3,3","4,4","5,5","6,6","7,7","8,8","9,9","10,10","11,11","12,12","13,13","14,14", "0,14", "1,13", "2,12", "3,11", "4,10", "5,9", "6,8","8,6", "9,5", "10,4","11,3","12,2","13,1","14,0"})
    void getColorReturnsWhiteOnTheDiagonals(int i, int j){
        Square square = new Square(i,j);
        assertEquals(Color.WHITE, square.getColor());
    }

    @ParameterizedTest
    @CsvSource({"0,1","0,3", "0,5","0,7","0,9","0,11","0,13"})
    void getColorReturnsBlackOnTheEvenSquaresOfFirstRow(int i, int j){
        Square square = new Square(i,j);
        assertEquals(Color.BLACK, square.getColor());
    }

    @ParameterizedTest
    @CsvSource({"14,1","14,3", "14,5","14,7","14,9","14,11","14,13"})
    void getColorReturnsBlackOnTheEvenSquaresOfLastRow(int i, int j){
        Square square = new Square(i,j);
        assertEquals(Color.BLACK, square.getColor());
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,2", "2,2,2,3", "3,3,3,4", "4,4,4,5", "5,5,5,6","6,6,6,7","7,7,7,8","8,8,8,9","9,9,9,10", "10,10,10,11"})
    void manhattanDistanceBetweenAdjacentOrthogonalSquareIsOne(int i, int j, int k, int l)
    {
        assertEquals(1, Square.manhattanSquareDistance(new Square(i,j), new Square(k,l)));
    }

    @ParameterizedTest
    @CsvSource({"0,14", "1,14", "2,14", "3,14","4,14","5,14","6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
    void lastColSquaresAreOnTheRightEdge(int i, int j){
        Square lastColSquare = new Square(i,j);
        assertTrue(lastColSquare.isRightEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "2,0", "3,0","4,0","5,0","6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0"})
    void firstColSquaresAreOnTheLeftEdge(int i, int j){
        Square firstColSquare = new Square(i,j);
        assertTrue(firstColSquare.isLeftEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3","0,4","0,5","0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14"})
    void firstRowSquaresAreOnTheTopEdge(int i, int j){
        Square firstRowSquare = new Square(i,j);
        assertTrue(firstRowSquare.isTopEdge());
    }

    @ParameterizedTest
    @CsvSource({"14,0", "14,1", "14,2", "14,3","14,4", "14,5","14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
    void lastRowSquaresAreOnTheBottomEdge(int i, int j){
        Square lastRowSquare = new Square(i,j);
        assertTrue(lastRowSquare.isBottomEdge());
    }

    @ParameterizedTest
    @CsvSource({"0,14", "1,14", "2,14", "3,14","4,14","5,14","6,14","7,14","8,14","9,14","10,14","11,14","12,14","13,14","14,14"})
    void getColumnOnRightEdgeSquareReturnsExpectedValue(int i, int j){
        Square lastColSquare = new Square(i,j);
        assertEquals(14, lastColSquare.getColumn());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "1,0", "2,0", "3,0","4,0","5,0","6,0","7,0","8,0","9,0","10,0","11,0","12,0","13,0","14,0"})
    void getColumnOnLeftEdgeSquareReturnsExpectedValue(int i, int j){
        Square firstColSquare = new Square(i,j);
        assertEquals(0, firstColSquare.getColumn());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3","0,4","0,5","0,6","0,7","0,8","0,9","0,10","0,11","0,12","0,13","0,14"})
    void getRowOnTopEdgeSquareReturnsExpectedValue(int i, int j){
        Square firstRowSquare = new Square(i,j);
        assertEquals(0, firstRowSquare.getRow());
    }

    @ParameterizedTest
    @CsvSource({"14,0", "14,1", "14,2", "14,3","14,4", "14,5","14,6","14,7","14,8","14,9","14,10","14,11","14,12","14,13","14,14"})
    void getRowOnBottomEdgeSquareReturnsExpectedValue(int i, int j){
        Square lastRowSquare = new Square(i,j);
        assertEquals(14, lastRowSquare.getRow());
    }

    //cornerSquares top & left
}
