package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.PieRuleNotApplicableException;
import it.units.sdm.brique.model.exceptions.StonePlacementWhenGameIsOverException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board gameBoard = Board.getInstance();
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final BiPredicate<Square, Square> squaresOccupiedByOrthogonalAndAdjacentStones = (Square square1, Square square2) -> Square.manhattanSquareDistance(square1, square2) == 1;
    private Status status = Status.RUNNING;
    private Player activePlayer;
    private final Predicate<Set<Square>> winningCondition = (Set<Square> cluster) -> switch (activePlayer.getStoneColor()) {
        case BLACK -> cluster.stream().anyMatch(Square::isTopEdge) && cluster.stream().anyMatch(Square::isBottomEdge);
        case WHITE -> cluster.stream().anyMatch(Square::isLeftEdge) && cluster.stream().anyMatch(Square::isRightEdge);
    };
    private boolean firstGameTurnDone;
    private boolean pieRuleApplicable;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard.reset();
        if (player1.getStoneColor().equals(Color.BLACK)) {
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

    private void switchActivePlayer() {
        Player oldValue = this.activePlayer;
        activePlayer = activePlayer.equals(player1) ? player2 : player1;
        pcs.firePropertyChange("activePlayer", oldValue, activePlayer);
    }

    private void stateWinningStatus() {
        Status oldValue = this.status;
        status = activePlayer.getStoneColor().equals(Color.BLACK) ? Status.BLACK_WINS : Status.WHITE_WINS;
        pcs.firePropertyChange("status", oldValue, status);
    }

    public void playTurn(Square square) {
        if (status != Status.RUNNING) throw new StonePlacementWhenGameIsOverException("Game is over");
        makePlacement(square);
        if (activePlayerWins()) {
            stateWinningStatus();
        } else {
            togglePieRule();
            switchActivePlayer();
        }
    }

    private void makePlacement(Square square) {
        Placement placement = new Placement(activePlayer);
        placement.setChosenSquare(square);
        placement.make();
    }

    private void togglePieRule() {
        if (!firstGameTurnDone) {
            firstGameTurnDone = true;
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
        player1.switchStoneColor();
        player2.switchStoneColor();
        pieRuleApplicable = false;
        switchActivePlayer();
    }

    private boolean activePlayerWins() {
        ClusterSet<Square> activeCluster = new ClusterSet<>(getActivePlayerPlacedStones());
        activeCluster.composeClusters(squaresOccupiedByOrthogonalAndAdjacentStones);
        return activeCluster.anyClusterMatches(winningCondition);
    }

    private Set<Square> getActivePlayerPlacedStones() {
        return gameBoard.getOccupiedSquares().stream().filter(square -> square.getStone().get().getColor().equals(activePlayer.getStoneColor())).collect(Collectors.toSet());
    }

    public void addActivePlayerListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("activePlayer", listener);
    }

    public void addStatusListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("status", listener);
    }
}