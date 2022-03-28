package it.units.sdm.brique.model;

import java.util.Optional;

public class Square {
  private final Color color;
  private Optional<Stone> stone;
  private final int x;
  private final int y;

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    this.stone = Optional.empty();
    this.color = (x + y) % 2 == 0 ? Color.WHITE : Color.BLACK;
  }

  public Color getColor() {
    return color;
  }

  public Optional<Stone> getStone() {
    return stone;
  }

  public int getX() {
    return x;
  }

  public int getY() { return y; }

  public boolean isTopEdge() {
    return x==Board.FIRST_INDEX;
  }

  public boolean isBottomEdge() {
    return x==Board.LAST_INDEX;
  }

  public boolean isLeftEdge(){ return y==Board.FIRST_INDEX; }

  public boolean isRightEdge(){
    return y==Board.LAST_INDEX;
  }

  public void setStone(Stone stone) {
    this.stone = Optional.ofNullable(stone);
  }

  public static int manhattanSquareDistance(Square square1, Square square2) {
    return Math.abs(square1.getX() - square2.getX())
            + Math.abs(square1.getY() - square2.getY());
  }
}

