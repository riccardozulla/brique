package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSquare {
    private final Square square = new Square(new Coordinate(0,0), Color.BLACK);

    @Test
    void toggleTestToFalse() {
        square.toggleSquareOccupied();
        assertEquals(true, square.getIsOccupied());
    }

    @Test
    void checkTestFalse() {
        assertEquals(false, square.getIsOccupied());
    }
}
