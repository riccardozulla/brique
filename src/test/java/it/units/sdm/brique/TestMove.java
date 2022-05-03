package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMove {

    private final Board board = Board.getBoard();
    private final Player player1 = new Player("defaultName1", Color.BLACK);
    private final Player player2 = new Player("defaultName2", Color.WHITE);

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    void setUpBoard(int i, int j, boolean samePlayer) {
        Square square = board.getSquare(i, j);
        Move firstMove = new Move(player1);
        firstMove.setChosenSquare(board.getDownLeftSquare(square));
        firstMove.make();
        Move secondMove;
        if (samePlayer) secondMove = new Move(player1);
        else secondMove = new Move(player2);
        secondMove.setChosenSquare(board.getSquare(i, j));
        secondMove.make();
    }

    @ParameterizedTest
    @CsvSource({"0,0","2,2","4,4","6,6","8,8","10,10","12,12","14,14"})
    void makeMoveAddsStoneOnTheChosenSquare(int i, int j)
    {
        Move move = new Move(player1);
        move.setChosenSquare(board.getSquare(i,j));
        move.make();
        assertTrue(board.getSquare(i,j).isOccupied());
    }

    @Test
    void placeStoneInOccupiedSquare() {
        board.getSquare(0, 0).setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> move.setChosenSquare(Board.getBoard().getSquare(0, 0)));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void escortRuleCorrectlyAppliedOnBlackSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        setUpBoard(i, j, true);
        assertEquals(board.getLeftSquare(board.getSquare(i, j)).getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyAppliedOnWhiteSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i, j, true);
        assertEquals(Color.BLACK, board.getDownSquare(board.getSquare(i, j)).getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesEnemyStone(){
        Move whiteMove = new Move(player2);
        whiteMove.setChosenSquare(board.getLeftSquare(board.getSquare(1,1)));
        whiteMove.make();
        setUpBoard(1,1, true);
        assertNotEquals(Color.WHITE, board.getLeftSquare(board.getSquare(1, 1)).getStone().get().getColor());
    }

    @Test
    void escortRuleNotAppliedWithEnemyStones(){
        setUpBoard(1,1, false);
        assertFalse(board.getLeftSquare(board.getSquare(1,1)).isOccupied());
    }

}
