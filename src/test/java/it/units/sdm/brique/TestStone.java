package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStone {

    private final Stone stone = new Stone(Color.WHITE);
    private final Stone otherStone = new Stone(Color.BLACK);

    @ParameterizedTest
    @EnumSource(value = Color.class)
    void testNewStoneColor(Color color) {
        Stone stone = new Stone(color);
        assertEquals(color, stone.getColor());
    }

    @ParameterizedTest
    @EnumSource(value = Color.class)
    void setColorAssignsStoneColor(Color color) {
        stone.setColor(color);
        assertEquals(color, stone.getColor());
    }

    @Test
    void switchColorTurnsWhiteIntoBlack() {
        stone.switchColor();
        assertEquals(Color.BLACK, stone.getColor());
    }

    @Test
    void switchColorTurnsBlackIntoWhite() {
        otherStone.switchColor();
        assertEquals(Color.WHITE, otherStone.getColor());
    }
}
