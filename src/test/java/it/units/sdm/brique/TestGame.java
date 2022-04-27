package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Test when a stone is in a corner

public class TestGame {
    private final Player player1 = new Player("Player1", Color.BLACK);
    private final Player player2 = new Player("Player2", Color.WHITE);
    private final Game game = new Game(player1, player2);
    private final Board board = game.getGameBoard();

    @BeforeEach
    void resetBoard() {
        board.reset();
    }

    @Test
    void statusIsRunningAtBoardReset() {
        assertEquals(Status.RUNNING, game.getStatus());
    }

    @Test
    void addStoneMakesSquareOccupied() {
        Square square = game.getGameBoard().getSquare(0, 0);
        game.addStone(square);
        assert (square.getStone().isPresent());
    }

    @Test
    void statusBecomesWHITE_WINSWhenWhiteWins() {
        List<Square> squareList = new ArrayList<>();
        game.switchActivePlayer();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            Square s = new Square(1, i);
            squareList.add(s);
            game.addStone(board.getSquare(1, i));
            game.switchActivePlayer();
        }
        assertEquals(Status.WHITE_WINS, game.getStatus());
    }

    @Test
    void statusBecomesBLACK_WINSWhenBlackWins() {
        List<Square> squareList = new ArrayList<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            Square s = new Square(i, 2);
            squareList.add(s);
            game.addStone(board.getSquare(i, 2));
            game.switchActivePlayer();
        }
        assertEquals(Status.BLACK_WINS, game.getStatus());
    }

    @Test
    void blackPlayerMovesFirst(){
        assertEquals(Color.BLACK,game.getActivePlayer().getStoneColor());
    }

    @Test
    void whiteMovesAfterBlack(){
        game.switchActivePlayer();
        assertEquals(Color.WHITE,game.getActivePlayer().getStoneColor());
    }

    @Test
    void involvedPlayersHaveDifferentStoneColor(){
        assertNotEquals(game.getPlayer1().getStoneColor(), game.getPlayer2().getStoneColor());
    }

    @Test
    void switchActivePlayerWorksProperly(){
        Player oldActivePlayer = game.getActivePlayer();
        game.switchActivePlayer();
        assertNotEquals(oldActivePlayer, game.getActivePlayer());
    }

    @Test
    void pieRuleDisabledAtBoardReset(){
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleApplicableDuringSecondTurnAfterBlackMoved(){
        game.addStone(new Square(0,0));
        assertTrue(game.isPieRuleApplicable());
    }

    @Test
    void pieRuleDisabledAfterSecondTurn(){
        game.addStone(board.getSquare(0,0));
        game.addStone(board.getSquare(0,1));
        assertFalse(game.isPieRuleApplicable());
    }

    @Test
    void afterPieRulePlayerColorsAreSwapped(){
        Color oldPlayer1Color = player1.getStoneColor();
        Color oldPlayer2Color = player2.getStoneColor();
        game.addStone(new Square(0,0));
        game.pieRule();
        assertNotEquals(player1.getStoneColor(), oldPlayer1Color);
        assertNotEquals(player2.getStoneColor(), oldPlayer2Color);
    }
}