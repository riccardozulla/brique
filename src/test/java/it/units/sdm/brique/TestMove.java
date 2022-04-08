package it.units.sdm.brique;

import it.units.sdm.brique.model.*;
import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TestMove {

    @Test
    void placeStoneInOccupiedSquare() {
        Board.getBoard().getSquare(0, 0).setStone(new Stone(Color.BLACK));
        Move move = new Move(new Player("Player", Color.BLACK));
        assertThrowsExactly(StoneAlreadyPresentException.class, () -> move.setChosenSquare(Board.getBoard().getSquare(0, 0)));
    }
}
