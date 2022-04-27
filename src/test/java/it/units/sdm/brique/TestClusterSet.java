package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClusterSet {

    Square s1 = new Square(1, 1);
    Square s2 = new Square(1, 2);

    ClusterSet clusterSet = new ClusterSet(Set.of(s1, s2), Color.BLACK);


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
        Set<Square> squareSet = new HashSet<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            squareSet.add(Board.getBoard().getSquare(1, i));
        }
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.WHITE);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }

    @Test
    void blackEdgeToEdgePathIsWinning() {
        Set<Square> squareSet = new HashSet<>();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            squareSet.add(Board.getBoard().getSquare(i, 1));
        }
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.BLACK);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }
}
