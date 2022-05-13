package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    private Player player1 = new Player("Player1", Color.BLACK);
    private Player player2 = new Player("Player2", Color.WHITE);

    @Test
    void player1NicknameCorrectlyAssigned() {
        assertEquals("Player1", player1.getNickname());
    }

    @Test
    void player2NicknameCorrectlyAssigned() {
        assertEquals("Player2", player2.getNickname());
    }

    @Test
    void blackPlayerHasBlackStoneColor() {
        assertEquals(Color.BLACK, player1.getStoneColor());
    }

    @Test
    void whitePlayerHasWhiteStoneColor() {
        assertEquals(Color.WHITE, player2.getStoneColor());
    }

    @Test
    void blackPlayerStoneColorIsChangedToWhite() {
        player1.setStoneColor(Color.WHITE);
        assertEquals(Color.WHITE, player1.getStoneColor());
    }

    @Test
    void whitePlayerStoneColorIsChangedToBlack() {
        player2.setStoneColor(Color.BLACK);
        assertEquals(Color.BLACK, player2.getStoneColor());
    }
}