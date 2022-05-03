package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClusterSet {

    ClusterSet clusterSet = new ClusterSet(Set.of(Board.getBoard().getSquare(1,1), Board.getBoard().getSquare(1,2)), Color.BLACK);

    @Test
    void initializeSingleSquareClusters() {
        assertEquals(2, clusterSet.numberOfSets());
    }

    @Test
    void composeClustersCreatesOneClusterOnlyIfTwoOrthogonalAndAdjacentSquaresAreInvolved() {
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
    }
}
