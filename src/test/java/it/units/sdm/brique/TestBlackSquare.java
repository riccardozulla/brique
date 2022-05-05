package it.units.sdm.brique;

import it.units.sdm.brique.model.BlackSquare;
import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBlackSquare {

    Board board = Board.getBoard();

    @Test
    void color() {
        assertEquals(Color.BLACK, new BlackSquare(0, 0).getColor());
    }

    @ParameterizedTest
    @CsvSource({"0,1", "4,5", "7,8", "12,13",})
    void hasExpectedEscortListWhenSquareNotOnEdges(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getDownSquare(square), board.getRightSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }

    @ParameterizedTest
    @CsvSource({"14,1", "14,5", "14,9", "14,13"})
    void hasOnlyOneEscortWhenSquareOnBottomEdge(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getRightSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }

    @ParameterizedTest
    @CsvSource({"1,14", "5,14", "9,14", "13,14"})
    void hasOnlyOneEscortWhenSquareOnLeftEdge(int row, int col) {
        Square square = board.getSquare(row, col);
        List<Square> expectedEscortList = List.of(board.getDownSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }
}
