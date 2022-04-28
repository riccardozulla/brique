package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TestMove {

    private final Board board = Board.getBoard();
    private final Player player1 = new Player("default", Color.BLACK);
    private final Player player2 = new Player("default", Color.WHITE);

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
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsFree(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        setUpBoard(i, j, player1, player1);
        assert (board.getLeft(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getLeft(board.getSquare(i, j)).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsFree(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i, j, player1, player1);
        assert (board.getDown(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getDown(board.getSquare(i, j)).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsFree(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        setUpBoard(i, j, player2, player1);
        assertFalse(board.getLeft(board.getSquare(i, j)).get().getStone().isPresent());
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsFree(int i, int j) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        setUpBoard(i, j, player2, player1);
        assertFalse(board.getDown(board.getSquare(i, j)).get().getStone().isPresent());
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int i, int j) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
        setUpBoard(i, j, player1, player1);
        board.getLeft(board.getSquare(i, j)).get().setStone(new Stone(Color.BLACK));
        assert (board.getLeft(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getLeft(board.getSquare(i, j)).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int i, int j) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
        setUpBoard(i, j, player1, player1);
        board.getDown(board.getSquare(i, j)).get().setStone(new Stone(Color.BLACK));
        assert (board.getDown(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getDown(board.getSquare(i, j)).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int i, int j) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
        setUpBoard(i, j, player2, player1);
        board.getLeft(board.getSquare(i, j)).get().setStone(new Stone(Color.WHITE));
        assert (board.getLeft(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getLeft(board.getSquare(i, j)).get().getStone().get().getColor(), Color.WHITE);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int i, int j) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
        setUpBoard(i, j, player2, player1);
        board.getDown(board.getSquare(i, j)).get().setStone(new Stone(Color.WHITE));
        assert (board.getDown(board.getSquare(i, j)).get().getStone().isPresent());
        assertEquals(board.getDown(board.getSquare(i, j)).get().getStone().get().getColor(), Color.WHITE);
    }

}
