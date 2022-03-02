package it.units.sdm.brique;

public class Game {
    private static final int BOARD_SIZE = 15;
    private Status status = Status.RUNNING;
    private final Player[] players;
    private final Board gameBoard;
    private Player nextMove;

    public Game(Player player1, Player player2){
        this.players = new Player[]{player1, player2};
        this.gameBoard = new Board(BOARD_SIZE);
    }

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

    public void addStone(int x, int y, Color color) {
        gameBoard.getSquare(x, y).setStone(new Stone(color));
    }
}
