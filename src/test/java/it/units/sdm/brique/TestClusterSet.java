package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClusterSet {

    private static final BiPredicate<Square, Square> alwaysTrue = (square1, square2) -> true;
    ClusterSet<Square> clusterSet = new ClusterSet<>(Set.of(Board.getBoard().getSquare(1, 1), Board.getBoard().getSquare(1, 2)), Color.BLACK);

    @Test
    void initializeSingleSquareClusters() {
        assertEquals(2, clusterSet.numberOfSets());
    }

    @Test
    void composeClustersCreatesOneClusterOnlyIfTwoOrthogonalAndAdjacentSquaresAreInvolved() {
        clusterSet.composeClusters(alwaysTrue);
        assertEquals(1, clusterSet.numberOfSets());
    }
}
