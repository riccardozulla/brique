package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.BiPredicate;

import static org.junit.jupiter.api.Assertions.*;

public class TestClusterSet {

    private static final BiPredicate<Square, Square> alwaysTrue = (square1, square2) -> true;
    private static final BiPredicate<Square, Square> alwaysFalse = (square1, square2) -> false;
    private final ClusterSet<Square> clusterSet = new ClusterSet<>(Set.of(Board.getInstance().getSquare(1, 1), Board.getInstance().getSquare(1, 2)));

    @Test
    void initializeSingleSquareClusters() {
        assertEquals(2, clusterSet.numberOfSets());
    }

    @Test
    void composeClustersCreatesOneSingleClusterSetWithAlwaysTrueBiPredicate() {
        clusterSet.composeClusters(alwaysTrue);
        assertEquals(1, clusterSet.numberOfSets());
    }

    @Test
    void composeClustersCreatesTwoDisjointClusterSetsWithAlwaysFalseBiPredicate() {
        clusterSet.composeClusters(alwaysFalse);
        assertEquals(2, clusterSet.numberOfSets());
    }

    @Test
    void anyClusterMatchesReturnsTrueWithAlwaysTruePredicate() {
        assertTrue(clusterSet.anyClusterMatches((cluster) -> true));
    }

    @Test
    void anyClusterMatchesReturnsFalseWithAlwaysFalsePredicate() {
        assertFalse(clusterSet.anyClusterMatches((cluster) -> false));
    }
}
