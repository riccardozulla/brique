package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.Square;

import java.util.Set;
import java.util.stream.Collectors;

public class TestClusterSetOfSquares extends TestClusterSet<Square> {

    @Override
    protected Set<Square> generateElementsSet(int elementsNumber) {
        return Board.getInstance().getSquaresStream().limit(elementsNumber).collect(Collectors.toSet());
    }
}




