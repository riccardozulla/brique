package it.units.sdm.brique.model;

import it.units.sdm.brique.model.exceptions.StoneAlreadyPresentException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Game {
    private Status status = Status.RUNNING;
    private final Player player1;
    private final Player player2;
    private final Board gameBoard;
    private Player activePlayer;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameBoard = Board.getBoard();
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
        square.getStone().ifPresentOrElse(s -> {
            throw new StoneAlreadyPresentException("Stone already present in the given square!");
        }, () -> square.setStone(new Stone(activePlayer.getStoneColor())));
    }

    private void addOrReplaceStone(Square square) {
        square.getStone().ifPresentOrElse(
                stone -> stone.setColor(activePlayer.getStoneColor()),
                () -> addStone(square));
    }

    public void addStoneAndCheckEscortRule(Square square) {
        addStone(square);
        checkEscortRule(square);
        if (isWinningTurn()) {
            stateWinningStatus();
            return;
        }
        switchActivePlayer();
    }

    private void checkEscortRule(Square square) {
        gameBoard.getDownLeft(square).flatMap(Square::getStone).ifPresent(stone -> {
            if (stoneBelongsToActivePlayer(stone)) {
                if (square.getColor() == Color.WHITE) {
                    addOrReplaceStone(gameBoard.getLeft(square).get()); //left square always exits
                } else {
                    addOrReplaceStone(gameBoard.getDown(square).get());
                }
            }
        });
        gameBoard.getUpRight(square).flatMap(Square::getStone).ifPresent(stone -> {
            if (stoneBelongsToActivePlayer(stone)) {
                if (square.getColor() == Color.WHITE) {
                    addOrReplaceStone(gameBoard.getUp(square).get());
                } else {
                    addOrReplaceStone(gameBoard.getRight(square).get());
                }
            }
        });
    }

    private boolean stoneBelongsToActivePlayer(Stone stone) {
        return stone.getColor() == activePlayer.getStoneColor();
    }

    public void addActivePlayerListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("activePlayer", listener);
    }

    public void addStatusListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("status", listener);
    }

    private boolean isWinningTurn() {
        var placedStones = Arrays.stream(gameBoard.getSquares()).flatMap(Arrays::stream).
                filter(square -> square.getStone().isPresent()).
                filter(square -> square.getStone().get().getColor() == activePlayer.getStoneColor()).collect(Collectors.toSet());
        ClusterSet activeCluster = new ClusterSet(placedStones, activePlayer.getStoneColor());
        activeCluster.composeClusters();
        return activeCluster.winningPath();
    }

}