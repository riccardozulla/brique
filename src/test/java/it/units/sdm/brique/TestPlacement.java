package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlacement {

    private final Board board = Board.getBoard();
    private final Player player1 = new Player("defaultName1", Color.BLACK);
    private final Player player2 = new Player("defaultName2", Color.WHITE);

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    private void makePlacement(Square square, Player player) {
        Placement placement = new Placement(player);
        placement.setChosenSquare(square);
        placement.make();
    }

    @ParameterizedTest
    @CsvSource({"0,0", "2,2", "4,4", "6,6", "8,8", "10,10", "12,12", "14,14"})
    void makeMoveAddsStoneOnTheChosenSquare(int i, int j) {
        Square chosenSquare = board.getSquare(i, j);
        Placement placement = new Placement(player1);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertTrue(chosenSquare.isOccupied());
    }

    @Test
    void placeStoneInOccupiedSquare() {
        Square chosenSquare = board.getSquare(0, 0);
        chosenSquare.setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player1);
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> placement.setChosenSquare(chosenSquare));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7", "5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyApplied(int i, int j) {
        Square square = board.getSquare(i, j);
        square.getEscorts().forEach(escortSquare -> makePlacement(escortSquare, player1));
        assertEquals(Color.BLACK, square.getStone().get().getColor());
    }

    @ParameterizedTest
    @CsvSource({"4,5","6,10","8,9","13,2","1,1","3,3","5,10","11,5"})
    void escortRuleCorrectlyReplacesEnemyStone(int i, int j) {
        Square square = board.getSquare(i, j);
        makePlacement(square, player2);
        square.getEscorts().forEach(escortSquare -> makePlacement(escortSquare, player1));
        assertNotEquals(Color.WHITE, square.getStone().get().getColor());
    }

    @ParameterizedTest
    @CsvSource({"4,5","6,10","8,9","13,2","1,1","3,3","5,10","11,5"})
    void escortRuleNotAppliedWithEnemyStones(int i, int j) {
        Square square = board.getSquare(i, j);
        List<Square> escorts = square.getEscorts();
        makePlacement(escorts.get(0), player1);
        makePlacement(escorts.get(1), player2);
        assertFalse(square.isOccupied());
    }
}
