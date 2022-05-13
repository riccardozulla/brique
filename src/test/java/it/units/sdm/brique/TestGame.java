package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private final Player player1 = new Player("Player1", Color.BLACK);
    private final Player player2 = new Player("Player2", Color.WHITE);
    private final Game game = new Game(player1, player2);
    private final Board board = Board.getBoard();

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    @Test
    void statusIsRunningAtGameInitialization() {
        assertEquals(Status.RUNNING, game.getStatus());
    }

    @ParameterizedTest
    @CsvSource({"0,0", "2,4", "3,3", "6,8", "1,9", "10,12", "11,12", "14,14"})
    void addStoneMakesSquareOccupied(int row, int col) {
        Square square = board.getSquare(row, col);
        game.addStone(square);
        assert (square.isOccupied());
    }

    @Test
    void statusBecomesWHITE_WINSWhenWhiteWins() { //TODO
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            game.addStone(board.getSquare(1, i));
            game.addStone(board.getSquare(2, i));
        }
        assertEquals(Status.WHITE_WINS, game.getStatus());
    }

    @Test
    void statusBecomesBLACK_WINSWhenBlackWins() { //TODO
        for (int i = 0; i < Board.BOARD_SIZE-1; i++) {
            game.addStone(board.getSquare(i, 2));
            game.addStone(board.getSquare(i, 3));
        }
        game.addStone(board.getSquare(14, 2));
        assertEquals(Status.BLACK_WINS, game.getStatus());
    }

    @Test
    void blackPlayerMovesFirst() {
        assertEquals(Color.BLACK, game.getActivePlayer().getStoneColor());
    }

    @Test
    void whitePlayerMovesAfterBlackPlayer() {
        game.addStone(board.getSquare(0, 0));
        assertEquals(Color.WHITE, game.getActivePlayer().getStoneColor());
    }

    @Test
    void involvedPlayersHaveDifferentStoneColor() {
        assertNotEquals(game.getPlayer1().getStoneColor(), game.getPlayer2().getStoneColor());
    }

    @Test
    void pieRuleDisabledAtGameInitialization() {
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleApplicableDuringSecondTurnAfterBlackMoved() {
        game.addStone(Board.getBoard().getSquare(0, 0));
        assertTrue(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleDisabledAfterSecondTurn() {
        game.addStone(board.getSquare(0, 0));
        game.addStone(board.getSquare(0, 1));
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleSwapsPlayerColors() {
        Color oldPlayer1Color = player1.getStoneColor();
        Color oldPlayer2Color = player2.getStoneColor();
        game.addStone(Board.getBoard().getSquare(0, 0));
        game.pieRule();
        assertNotEquals(oldPlayer1Color, player1.getStoneColor());
        assertNotEquals(oldPlayer2Color, player2.getStoneColor());
    }
}