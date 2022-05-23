package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private final Player player1 = new Player("Player1", Color.BLACK);
    private final Player player2 = new Player("Player2", Color.WHITE);
    private final Game game = new Game(player1, player2);
    private final Board board = Board.getInstance();

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
    void afterPlayingTurnTheChosenSquareIsOccupied(int row, int col) {
        Square chosenSquare = board.getSquare(row, col);
        game.playTurn(chosenSquare);
        assertTrue(chosenSquare.isOccupied());
    }

    @Test
    void statusBecomesWHITE_WINSWhenWhitePlayerConnectsWhiteBoardEdges() {
        for (int i = 0; i < Board.BOARD_SIZE - 1; i++) {
            game.playTurn(board.getSquare(1, i)); //black plays
            game.playTurn(board.getSquare(2, i)); //white plays
        }
        game.playTurn(board.getSquare(1, Board.LAST_INDEX)); //last black move
        game.playTurn(board.getSquare(2, Board.LAST_INDEX)); //last white (winning) move
        assertEquals(Status.WHITE_WINS, game.getStatus());
    }

    @Test
    void statusBecomesBLACK_WINSWhenBlackPlayerConnectsBlackBoardEdges() {
        for (int i = 0; i < Board.BOARD_SIZE - 1; i++) {
            game.playTurn(board.getSquare(i, 2)); //black plays
            game.playTurn(board.getSquare(i, 3)); //white plays
        }
        game.playTurn(board.getSquare(Board.LAST_INDEX, 2)); //last black (winning) move
        assertEquals(Status.BLACK_WINS, game.getStatus());
    }

    @Test
    void blackPlayerMovesFirst() {
        assertEquals(Color.BLACK, game.getActivePlayer().getStoneColor());
    }

    @Test
    void whitePlayerMovesAfterBlackPlayer() {
        game.playTurn(board.getSquare(0, 0));
        assertEquals(Color.WHITE, game.getActivePlayer().getStoneColor());
    }

    @Test
    void involvedPlayersHaveDifferentStoneColor() {
        assertNotEquals(game.getPlayer1().getStoneColor(), game.getPlayer2().getStoneColor());
    }

    @Test
    void pieRuleNotApplicableAtGameInitialization() {
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleApplicableDuringSecondTurnAfterBlackMoved() {
        game.playTurn(Board.getInstance().getSquare(0, 0));
        assertTrue(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleNotApplicableAfterSecondTurn() {
        game.playTurn(board.getSquare(0, 0));
        game.playTurn(board.getSquare(0, 1));
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleSwapsPlayerColors() {
        Color oldPlayer1Color = player1.getStoneColor();
        Color oldPlayer2Color = player2.getStoneColor();
        game.playTurn(Board.getInstance().getSquare(0, 0));
        game.pieRule();
        assertAll(
                () -> assertEquals(oldPlayer2Color, player1.getStoneColor()),
                () -> assertEquals(oldPlayer1Color, player2.getStoneColor())
        );
    }
}