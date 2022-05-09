package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBoard {
    private final Board board = Board.getBoard();

    @Test
    void numberOfSquaresIsTheExpectedOne() {
        assertEquals(Board.BOARD_SIZE * Board.BOARD_SIZE, board.getSquaresStream().count());
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

    @ParameterizedTest
    @CsvSource({"1,1", "4,7", "9, 2", "11,13"})
    void singleSquareWithStoneOnItIsOccupiedAsExpected(int i, int j) {
        board.reset();
        Square square = board.getSquare(i, j);
        square.setStone(new Stone(Color.BLACK));
        assertEquals(List.of(square), board.getOccupiedSquares());
    }
}
