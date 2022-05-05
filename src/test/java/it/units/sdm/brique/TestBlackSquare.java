package it.units.sdm.brique;

import it.units.sdm.brique.model.BlackSquare;
import it.units.sdm.brique.model.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBlackSquare {

    @Test
    void color() {
        assertEquals(Color.BLACK, new BlackSquare(0,0).getColor());
    }
}
