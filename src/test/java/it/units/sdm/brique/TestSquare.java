package it.units.sdm.brique;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {
    private final Square square = new Square(0,0);

    @Test
    void toggleTestToTrue() {
        square.setStone(new Stone(Color.BLACK));
        assert(square.getStone().isPresent());
    }

    @Test
    void checkTestFalse() {
        assertFalse(square.getStone().isPresent());
    }
}
