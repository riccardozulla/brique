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

    void setUpBoard(int i, int j, Player blackPlayer, Player whitePlayer) {
        Square square = board.getSquare(i, j);
        Move firstMove = new Move(blackPlayer);
        firstMove.setChosenSquare(board.getDownLeft(square).get());
        firstMove.make();
        Move secondMove = new Move(whitePlayer);
        secondMove.setChosenSquare(board.getSquare(i, j));
        secondMove.make();
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
        setUpBoard(i, j, player1, player1);
        assertEquals(board.getLeft(board.getSquare(i, j)).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void escortRuleCorrectlyAppliedOnWhiteSquares(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i, j, player1, player1);
        assertEquals(Color.BLACK, board.getDown(board.getSquare(i, j)).get().getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesWhiteEnemyStone(){
        Move whiteMove = new Move(player2);
        whiteMove.setChosenSquare(board.getLeft(board.getSquare(1,1)).get());
        whiteMove.make();
        setUpBoard(1,1, player1,player1);
        assertNotEquals(Color.WHITE, board.getLeft(board.getSquare(1, 1)).get().getStone().get().getColor());
    }

    @Test
    void escortRuleCorrectlyReplacesBlackEnemyStone(){
        Move whiteMove = new Move(player1);
        whiteMove.setChosenSquare(board.getLeft(board.getSquare(1,1)).get());
        whiteMove.make();
        setUpBoard(1,1, player2,player2);
        assertNotEquals(Color.BLACK, board.getLeft(board.getSquare(1, 1)).get().getStone().get().getColor());
    }

    //replacement escort rule

}
