package it.units.sdm.brique.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Game {
    private Status status = Status.RUNNING;
    private final Player player1;
    private final Player player2;
    private final Board gameBoard;
    private Player activePlayer;
    private boolean whiteMadeFirstMove;
    private boolean whiteAppliedPieRule;
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
        Move move = new Move(activePlayer);
        move.setChosenSquare(square);
        move.make();
        checkWhiteMadeFirstMove();
        if (move.isWinning())
            stateWinningStatus();
        switchActivePlayer();
    }

    private void checkWhiteMadeFirstMove() {
        if (getActivePlayer().getStoneColor() == Color.WHITE && !whiteMadeFirstMove){
            whiteMadeFirstMove=true;
        }
    }

    private boolean pieRuleApplicable() {
        return getActivePlayer().getStoneColor() == Color.WHITE && !whiteMadeFirstMove;
    }

    public void pieRule() {
        if (pieRuleApplicable()) {
            if (player1.equals(activePlayer)) {
                player2.setStoneColor(Color.WHITE);
                player1.setStoneColor(Color.BLACK);
            } else if (player2.equals(activePlayer)) {
                player1.setStoneColor(Color.WHITE);
                player2.setStoneColor(Color.BLACK);
            }
            whiteAppliedPieRule = true;
            switchActivePlayer();
        }
    }

    public void addActivePlayerListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("activePlayer", listener);
    }

    public void addStatusListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener("status", listener);
    }
}