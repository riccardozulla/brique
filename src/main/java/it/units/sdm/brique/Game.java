package it.units.sdm.brique;

public class Game {
    private Status status;
    private Player[] players;
    private Board gameBoard;
    private Player nextMove;

    public Status getStatus() {
        return status;
    }

    public Player getNextMove() {
        return nextMove;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getGameBoard() {
        return gameBoard;
    }
}
