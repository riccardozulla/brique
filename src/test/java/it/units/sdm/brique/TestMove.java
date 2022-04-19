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

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    @Test
    void placeStoneInOccupiedSquare() {
        board.getSquare(0, 0).setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> move.setChosenSquare(Board.getBoard().getSquare(0, 0)));
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getLeft(square).get().getStone().isPresent());
        assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getDown(square).get().getStone().isPresent());
        assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsFree(int x, int y) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a white square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.WHITE));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assertFalse(board.getLeft(square).get().getStone().isPresent());
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsFree(int x, int y) {
        //Precondition: The square near the occupied escort is free. The specified coordinates are on a black square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.WHITE));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assertFalse(board.getDown(square).get().getStone().isPresent());
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.BLACK));
        board.getLeft(square).get().setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getLeft(square).get().getStone().isPresent());
        assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenFriendlyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.BLACK));
        board.getDown(square).get().setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getDown(square).get().getStone().isPresent());
        assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"8, 12", "1, 1", "5, 7"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndBlackSquareIsOccupiedByEnemyStone(int x, int y) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a white square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.WHITE));
        board.getLeft(square).get().setStone(new Stone(Color.WHITE));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getLeft(square).get().getStone().isPresent());
        assertEquals(board.getLeft(square).get().getStone().get().getColor(), Color.WHITE);
    }

    @ParameterizedTest
    @CsvSource({"5, 6", "6, 9", "10, 11"})
    void testStonePlacementWhenEnemyStoneIsAlreadyPresentAndWhiteSquareIsOccupiedByEnemyStone(int x, int y) {
        //Precondition: The square near the occupied escort is occupied by an enemy stone. The specified coordinates are on a black square.
        Square square = board.getSquare(x, y);
        board.getDownLeft(square).get().setStone(new Stone(Color.WHITE));
        board.getDown(square).get().setStone(new Stone(Color.WHITE));
        Move move = new Move(new Player("Player", Color.BLACK));
        move.setChosenSquare(square);
        move.make();
        assert(board.getDown(square).get().getStone().isPresent());
        assertEquals(board.getDown(square).get().getStone().get().getColor(), Color.WHITE);
    }

}
