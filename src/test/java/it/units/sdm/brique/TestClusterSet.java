package it.units.sdm.brique;

import it.units.sdm.brique.model.Board;
import it.units.sdm.brique.model.ClusterSet;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource({"0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14"})
    void whiteEdgeToEdgePathIsWinning(int row) {
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(row, i)).collect(Collectors.toSet());
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.WHITE);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }

    @ParameterizedTest
    @CsvSource({"0", "1","2","3","4","5","6","7","8","9","10","11","12","13","14"})
    void blackEdgeToEdgePathIsWinning(int col) {
        Set<Square> squareSet = IntStream.range(0, Board.BOARD_SIZE).mapToObj(i -> Board.getBoard().getSquare(i, col)).collect(Collectors.toSet());
        ClusterSet clusterSet = new ClusterSet(squareSet, Color.BLACK);
        clusterSet.composeClusters();
        assertEquals(1, clusterSet.numberOfSets());
        assertTrue(clusterSet.winningPath());
    }
}
