package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    @ParameterizedTest
    @ValueSource(strings = {"Alessandro", "Antonio", "Riccardo"})
    void testPlayerNickname(String nickname){
        Player player = new Player(nickname, Color.BLACK);
        assertEquals(nickname, player.getNickname());
    }

    @Test
    void blackPlayerHasBlackStoneColor() {
        Player player = new Player("default", Color.BLACK);
        assertEquals(Color.BLACK, player.getStoneColor());
    }

    @Test
    void whitePlayerHasWhiteStoneColor() {
        Player player = new Player("default", Color.WHITE);
        assertEquals(Color.WHITE, player.getStoneColor());
    }

}
