package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void StatusBecomesWHITE_WINSWhenWhiteWins() {
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
    void StatusBecomesBLACK_WINSWhenBlackWins() {
        List<Square> squareList = new ArrayList<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            Square s = new Square(i, 2);
            squareList.add(s);
            game.addStone(board.getSquare(i, 2));
            game.switchActivePlayer();
        }
        assertEquals(Status.BLACK_WINS, game.getStatus());
    }
}