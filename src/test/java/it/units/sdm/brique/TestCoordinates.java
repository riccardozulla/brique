package it.units.sdm.brique;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TestCoordinates {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15})
    void test_X_Coordinates(int number){
        Coordinate c = new Coordinate (number,0);
        assertEquals(number, c.getX());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15})
    void test_Y_Coordinates(int number){
        Coordinate c = new Coordinate (0,number);
        assertEquals(number, c.getY());
    }
}
