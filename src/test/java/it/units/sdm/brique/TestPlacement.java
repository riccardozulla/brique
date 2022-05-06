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

    void setUpBoard(int i, int j, boolean samePlayer) {
        Square square = board.getSquare(i, j);
        Placement firstPlacement = new Placement(player1);
        firstPlacement.setChosenSquare(board.getDownLeftSquare(square));
        firstPlacement.make();
        Placement secondPlacement;
        if (samePlayer) secondPlacement = new Placement(player1);
        else secondPlacement = new Placement(player2);
        secondPlacement.setChosenSquare(board.getSquare(i, j));
        secondPlacement.make();
    }

    @ParameterizedTest
    @CsvSource({"0,0","2,2","4,4","6,6","8,8","10,10","12,12","14,14"})
    void makeMoveAddsStoneOnTheChosenSquare(int i, int j)
    {
        Placement placement = new Placement(player1);
        placement.setChosenSquare(board.getSquare(i,j));
        placement.make();
        assertTrue(board.getSquare(i,j).isOccupied());
    }

    @Test
    void placeStoneInOccupiedSquare() {
        board.getSquare(0, 0).setStone(new Stone(Color.BLACK));
        Placement placement = new Placement(new Player("Player", Color.BLACK));
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> placement.setChosenSquare(Board.getBoard().getSquare(0, 0)));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void escortRuleCorrectlyAppliedOnBlackSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        setUpBoard(i, j, true);
        assertEquals(board.getSquare(i, j).getLeftSquare().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyAppliedOnWhiteSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i, j, true);
        assertEquals(Color.BLACK, board.getSquare(i, j).getDownSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesEnemyStone(){
        Placement whitePlacement = new Placement(player2);
        whitePlacement.setChosenSquare(board.getSquare(1,1).getLeftSquare());
        whitePlacement.make();
        setUpBoard(1,1, true);
        assertNotEquals(Color.WHITE, board.getSquare(1, 1).getLeftSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleNotAppliedWithEnemyStones(){
        setUpBoard(1,1, false);
        assertFalse(board.getSquare(1,1).getLeftSquare().isOccupied());
    }

}
