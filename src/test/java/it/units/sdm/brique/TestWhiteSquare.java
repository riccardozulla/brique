package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.WhiteSquare;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWhiteSquare {

    Board board = Board.getBoard();

    @Test
    void color() {
        assertEquals(Color.WHITE, new WhiteSquare(0, 0).getColor());
    }

    @ParameterizedTest
    @CsvSource({"1,1", "4,4,", "10,10", "14,14", "12,2", "4,10"})
    void hasExpectedEscortListWhenSquareNotOnEdges(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getUpSquare(square), board.getLeftSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }

    @Test
    void hasEmptyEscortListWhenSquareOnTopLeftCorner() {
        Square square = board.getSquare(0, 0);
        assertEquals(Collections.emptyList(), square.getEscorts());
    }

    @ParameterizedTest
    @CsvSource({"0,2", "0,6", "0,10", "0,14"})
    void hasOnlyOneEscortWhenSquareOnTopEdge(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getLeftSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }

    @ParameterizedTest
    @CsvSource({"2,0", "6,0", "10,0", "14,0"})
    void hasOnlyOneEscortWhenSquareOnLeftEdge(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getUpSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }
}
