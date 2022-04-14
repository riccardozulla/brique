package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {
    private final Square square = new Square(0,0);

    @Test
    void stoneIsCorrectlySet() {
        square.setStone(new Stone(Color.BLACK));
        assert(square.getStone().isPresent());
    }

    @Test
    void emptyStoneHasNotStoneOnIt() {
        assertFalse(square.getStone().isPresent());
    }
}
