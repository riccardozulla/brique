package it.units.sdm.brique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSquare {
    private final Square square = new Square(Color.BLACK);

    @Test
    void toggleTestToFalse() {
        square.toggleSquareOccupied();
        assertEquals(true, square.isOccupied());
    }

    @Test
    void checkTestFalse() {
        assertEquals(false, square.isOccupied());
    }
}
