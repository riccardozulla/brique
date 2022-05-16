package it.units.sdm.brique.utility;

import it.units.sdm.brique.model.Player;

public final class PlayerHolder {
    private static final PlayerHolder playerHolderInstance = new PlayerHolder();
    private Player player1;
    private Player player2;

    private PlayerHolder() {
    }

    public static PlayerHolder getInstance() {
        return playerHolderInstance;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
