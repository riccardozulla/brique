package it.units.sdm.brique;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.Stone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestSquare {
    private final Square square = new Square(0,0);

    @Test
    void settingStoneOnSquareMakesGetStonePresent() {
        square.setStone(new Stone(Color.BLACK));
        assert(square.getStone().isPresent());
    }

    @Test
    void emptySquareHasNotStoneOnIt() {
        assertFalse(square.getStone().isPresent());
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,2", "2,2,2,3", "3,3,3,4", "4,4,4,5", "5,5,5,6","6,6,6,7","7,7,7,8","8,8,8,9","9,9,9,10", "10,10,10,11"})
    void manhattanDistanceBetweenAdjacentOrthogonalSquareIsOne(int i, int j, int k, int l)
    {
        assertEquals(1, Square.manhattanSquareDistance(new Square(i,j), new Square(k,l)));
    }
}
