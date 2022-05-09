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

    void setUpBoard(int i, int j, Color color) {
        board.getSquare(i, j).setStone(new Stone(color));
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
        setUpBoard(0,0, player1.getStoneColor());
        Placement placement = new Placement(player1);
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> placement.setChosenSquare(Board.getBoard().getSquare(0, 0)));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void escortRuleCorrectlyAppliedOnBlackSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        setUpBoard(i+1, j-1, player1.getStoneColor());
        Placement placement = new Placement(player1);
        placement.setChosenSquare(board.getSquare(i, j));
        placement.make();
        assertEquals(board.getSquare(i, j).getLeftSquare().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyAppliedOnWhiteSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i+1, j-1, player1.getStoneColor());
        Placement placement = new Placement(player1);
        placement.setChosenSquare(board.getSquare(i, j));
        placement.make();
        assertEquals(Color.BLACK, board.getSquare(i, j).getDownSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesEnemyStone(){
        setUpBoard(1, 0, player2.getStoneColor());
        setUpBoard(2, 0, player1.getStoneColor());
        Placement placement = new Placement(player1);
        placement.setChosenSquare(board.getSquare(1, 1));
        placement.make();
        assertNotEquals(Color.WHITE, board.getSquare(1, 1).getLeftSquare().getStone().get().getColor());
    }

    @Test
    void escortRuleNotAppliedWithEnemyStones(){
        setUpBoard(2, 0, player1.getStoneColor());
        Placement placement = new Placement(player2);
        assertFalse(board.getSquare(1,1).getLeftSquare().isOccupied());
    }

}
