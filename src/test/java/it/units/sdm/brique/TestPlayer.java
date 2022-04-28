package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.WHITE);

    @Test
    void player1NicknameCorrectlyAssigned(){
        assertEquals("Player1", player1.getNickname());
    }

    @Test
    void player2NicknameCorrectlyAssigned(){
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
    void blackPlayerStoneColorIsSetToWhite(){
        player1.setStoneColor(Color.WHITE);
        assertEquals(Color.WHITE, player1.getStoneColor());
    }

    @Test
    void whitePlayerStoneColorIsSetToBlack(){
        player2.setStoneColor(Color.BLACK);
        assertEquals(Color.BLACK, player2.getStoneColor());
    }
}
