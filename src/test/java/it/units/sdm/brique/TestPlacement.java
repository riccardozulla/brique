package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlacement {

    private final Board board = Board.getBoard();
    private final Player player1 = new Player("defaultName1", Color.BLACK);
    private final Player player2 = new Player("defaultName2", Color.WHITE);

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    Square getDownLeft(Square square) {
        return square.getDownSquare().getLeftSquare();
    }

    @ParameterizedTest
    @CsvSource({"0,0","2,2","4,4","6,6","8,8","10,10","12,12","14,14"})
    void makeMoveAddsStoneOnTheChosenSquare(int i, int j)
    {
        Square chosenSquare = board.getSquare(i,j);
        Placement placement = new Placement(player1);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertTrue(chosenSquare.isOccupied());
    }

    @Test
    void placeStoneInOccupiedSquare() {
        Square chosenSquare = board.getSquare(0,0);
        chosenSquare.setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player1);
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> placement.setChosenSquare(chosenSquare));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void escortRuleCorrectlyAppliedOnBlackSquares(int i, int j) {
        Square chosenSquare = board.getSquare(i,j);
        getDownLeft(chosenSquare).setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player1);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertEquals(Color.BLACK, chosenSquare.getLeftSquare().getStone().get().getColor());
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyAppliedOnWhiteSquares(int i, int j) {
        Square chosenSquare = board.getSquare(i,j);
        getDownLeft(chosenSquare).setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player1);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertEquals(Color.BLACK, chosenSquare.getDownSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesEnemyStone(){
        Square chosenSquare = board.getSquare(1,1);
        chosenSquare.getLeftSquare().setStone(new Stone(player2.getStoneColor()));
        getDownLeft(chosenSquare).setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player1);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertNotEquals(Color.WHITE, chosenSquare.getLeftSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleNotAppliedWithEnemyStones(){
        Square chosenSquare = board.getSquare(1,1);
        getDownLeft(chosenSquare).setStone(new Stone(player1.getStoneColor()));
        Placement placement = new Placement(player2);
        placement.setChosenSquare(chosenSquare);
        placement.make();
        assertFalse(chosenSquare.getLeftSquare().isOccupied());
    }

}
