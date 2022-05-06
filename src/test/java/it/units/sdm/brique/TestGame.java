package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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
        assert (square.isOccupied());
    }

    @Test
    void statusBecomesWHITE_WINSWhenWhiteWins() {
        game.switchActivePlayer();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            game.addStone(board.getSquare(1, i));
            game.switchActivePlayer();
        }
        assertEquals(Status.WHITE_WINS, game.getStatus());
    }

    @Test
    void statusBecomesBLACK_WINSWhenBlackWins() {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
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
        game.addStone(Board.getBoard().getSquare(0,0));
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
        game.addStone(Board.getBoard().getSquare(0,0));
        game.pieRule();
        assertNotEquals(player1.getStoneColor(), oldPlayer1Color);
        assertNotEquals(player2.getStoneColor(), oldPlayer2Color);
    }

    @ParameterizedTest
    @CsvSource({"0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14"})
    void verticalBlackEdgeToEdgePathIsWinning(int col) {
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(i, col)).collect(Collectors.toSet());
        squareSet.forEach(s -> s.setStone(new Stone(Color.BLACK)));
        assertTrue(game.activePlayerWins());
    }

    @ParameterizedTest
    @CsvSource({"0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14"})
    void horizontalWhiteEdgeToEdgePathIsWinning(int row) {
        game.switchActivePlayer();
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(row, i)).collect(Collectors.toSet());
        squareSet.forEach(s -> s.setStone(new Stone(Color.WHITE)));
        assertTrue(game.activePlayerWins());
    }
}