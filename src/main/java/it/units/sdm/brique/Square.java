package it.units.sdm.brique;

import java.util.Optional;

public class Square {
  private final Color color;
  private Optional<Stone> stone;
  private final int x;
  private final int y;

  public Square(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
    this.stone = Optional.empty();
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
    return x==0;
  }

  public boolean isBottomEdge() {
    return x==Board.BOARD_SIZE-1;
  }

  public boolean isLeftEdge(){ return y==0; }

  public boolean isRightEdge(){
    return y==Board.BOARD_SIZE-1;
  }

  public void setStone(Stone stone) {
    this.stone = Optional.of(stone);
  }

  public static int manhattanSquareDistance(Square square1, Square square2) {
    return Math.abs(square1.getX() - square2.getX())
            + Math.abs(square1.getY() - square2.getY());
  }
}

