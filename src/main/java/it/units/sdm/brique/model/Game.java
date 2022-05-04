package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.PieRuleNotApplicableException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board gameBoard;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final BiPredicate<Square, Square> squaresOccupiedByOrthogonalAndAdjacentStonesPredicate = (Square square1, Square square2) -> Square.manhattanSquareDistance(square1, square2) == 1;
    private Status status = Status.RUNNING;
    private Player activePlayer;
    private final Predicate<Set<Square>> winningCondition = (Set<Square> cluster) -> switch (activePlayer.getStoneColor()) {
        case BLACK -> cluster.stream().anyMatch(Square::isTopEdge) && cluster.stream().anyMatch(Square::isBottomEdge);
        case WHITE -> cluster.stream().anyMatch(Square::isLeftEdge) && cluster.stream().anyMatch(Square::isRightEdge);
    };
    private boolean firstGameMoveDone;
    private boolean pieRuleApplicable;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = Board.getBoard();
        this.gameBoard.reset();
        if (player1.getStoneColor() == Color.BLACK) {
            activePlayer = player1;
        } else {
            activePlayer = player2;
        }
    }

    public Status getStatus() {
        return status;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void switchActivePlayer() {
        Player oldValue = this.activePlayer;
        activePlayer = activePlayer.equals(player1) ? player2 : player1;
        pcs.firePropertyChange("activePlayer", oldValue, activePlayer);
    }

    public void stateWinningStatus() {
        Status oldValue = this.status;
        if (activePlayer.getStoneColor() == Color.BLACK) {
            status = Status.BLACK_WINS;
        } else {
            status = Status.WHITE_WINS;
        }
        pcs.firePropertyChange("status", oldValue, status);
    }

    public void addStone(Square square) {
        Move move = new Move(activePlayer);
        move.setChosenSquare(square);
        move.make();
        if (activePlayerWins()) {
            stateWinningStatus();
            return;
        }
        togglePieRule();
        switchActivePlayer();
    }

    private void togglePieRule() {
        if (!firstGameMoveDone) {
            firstGameMoveDone = true;
            pieRuleApplicable = true;
        } else if (pieRuleApplicable) {
            pieRuleApplicable = false;
        }
    }

    public boolean isPieRuleApplicable() {
        return pieRuleApplicable;
    }

    public void pieRule() {
        if (!pieRuleApplicable) {
            throw new PieRuleNotApplicableException("Pie rule not applicable");
        }
        if (player1.equals(activePlayer)) {
            player2.setStoneColor(Color.WHITE);
            player1.setStoneColor(Color.BLACK);
        } else if (player2.equals(activePlayer)) {
            player1.setStoneColor(Color.WHITE);
            player2.setStoneColor(Color.BLACK);
        }
        pieRuleApplicable = false;
        switchActivePlayer();
    }

    public boolean activePlayerWins() {
        ClusterSet activeCluster = new ClusterSet(getActivePlayerPlacedStones(), activePlayer.getStoneColor());
        activeCluster.composeClusters(squaresOccupiedByOrthogonalAndAdjacentStonesPredicate);
        return activeCluster.anyClusterMatches(winningCondition);
    }

    private Set<Square> getActivePlayerPlacedStones() {
        return gameBoard.getOccupiedSquares().stream().
                filter(square -> square.getStone().get().getColor() == activePlayer.getStoneColor()).
                collect(Collectors.toSet());
    }

    public void addActivePlayerListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("activePlayer", listener);
    }

    public void addStatusListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("status", listener);
    }
}