package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestSquare {
    private final Square square = new Square(new Coordinate(0,0), Color.BLACK);

    @Test
    void toggleTestToTrue() {
        square.setStone(new Stone(Color.BLACK));
        assertNotEquals(Optional.empty(), square.getStone());
    }

    @Test
    void checkTestFalse() {
        assertEquals(false, square.getStone() == null);
    }
}
