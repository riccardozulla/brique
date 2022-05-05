package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.WhiteSquare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestWhiteSquare {

    @Test
    void color() {
        assertEquals(Color.WHITE, new WhiteSquare(0,0).getColor());
    }
}
