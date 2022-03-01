package it.units.sdm.brique;

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
}
