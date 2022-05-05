package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import it.units.sdm.brique.model.WhiteSquare;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWhiteSquare {

    @Test
    void color() {
        assertEquals(Color.WHITE, new WhiteSquare(0,0).getColor());
    }

    @Test
    void hasExpectedEscortListWhenSquareNotOnEdges() {
        Board board = Board.getBoard();
        Square square = board.getSquare(1,1);
        List<Square> expectedEscortList = List.of(board.getUpSquare(square), board.getLeftSquare(square));
        assertEquals(expectedEscortList, square.getEscorts());
    }
}
