package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
    private final Board board = Board.getInstance();

    @Test
    void numberOfSquaresIsTheExpectedOne() {
        int expectedNumberOfSquares = Board.BOARD_SIZE * Board.BOARD_SIZE;
        assertEquals(expectedNumberOfSquares, board.getSquaresStream().count());
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

    @Test
    void squaresOccupiedWithStonesOnThemAreOccupiedAsExpected(){
        Set<Square> allBoardSquares = board.getSquaresStream().collect(Collectors.toSet());
        allBoardSquares.forEach(s -> s.setStone(new Stone(Color.BLACK)));
        assertEquals(allBoardSquares, board.getOccupiedSquares().stream().collect(Collectors.toSet()));
    }
}
