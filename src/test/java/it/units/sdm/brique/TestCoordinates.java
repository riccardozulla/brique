package it.units.sdm.brique;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class TestCoordinates {

    Coordinate c = new Coordinate(3,4);
    @Test
    void testX() {
        assertEquals(3, c.getX());
    }
    void testY() {
        assertEquals(4, c.getY());
    }
}
