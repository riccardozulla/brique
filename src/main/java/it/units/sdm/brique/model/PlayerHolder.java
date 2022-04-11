package it.units.sdm.brique.model;

public final class PlayerHolder {
    private Player player1;
    private Player player2;
    private static PlayerHolder INSTANCE = new PlayerHolder();

    private PlayerHolder() {}

    public static PlayerHolder getInstance() {
        if (INSTANCE == null) INSTANCE = new PlayerHolder();
        return INSTANCE;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }
}
