package it.units.sdm.brique;

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

}
