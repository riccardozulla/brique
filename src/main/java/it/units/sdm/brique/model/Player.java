package it.units.sdm.brique.model;

public class Player {
    private String nickname;
    private Color stoneColor;

    public Player (String nickname, Color stoneColor){
        this.nickname = nickname;
        this.stoneColor = stoneColor;
    }

    public String getNickname() {
        return nickname;
    }

    public Color getStoneColor() {
        return stoneColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!nickname.equals(player.nickname)) return false;
        return stoneColor == player.stoneColor;
    }

    @Override
    public int hashCode() {
        int result = nickname.hashCode();
        result = 31 * result + stoneColor.hashCode();
        return result;
    }
}
