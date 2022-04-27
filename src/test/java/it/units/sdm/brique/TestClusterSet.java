package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void whiteEdgeToEdgePathIsWinning() {
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(1, i)).collect(Collectors.toSet());
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.WHITE);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }

    @Test
    void blackEdgeToEdgePathIsWinning() {
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(i, 1)).collect(Collectors.toSet());
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.BLACK);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }
}
