package it.units.sdm.brique.model;

import java.util.Optional;

public class Square {
  private final Color color;
  private Stone stone;
  private final int row;
  private final int column;

  public Square(int row, int column) {
    this.row = row;
    this.column = column;
    this.color = (row + column) % 2 == 0 ? Color.WHITE : Color.BLACK;
  }

  public Color getColor() {
    return color;
  }

  public Optional<Stone> getStone() {
    return Optional.ofNullable(stone);
  }

  public int getRow() {
    return row;
  }

  public int getColumn() { return column; }

  public boolean isTopEdge() {
    return row ==Board.FIRST_INDEX;
  }

  public boolean isBottomEdge() {
    return row ==Board.LAST_INDEX;
  }

  public boolean isLeftEdge(){ return column ==Board.FIRST_INDEX; }

  public boolean isRightEdge(){
    return column ==Board.LAST_INDEX;
  }

  public void setStone(Stone stone) {
    this.stone = stone;
  }

  public void removeStone(){
    stone = null;
  }

  public static int manhattanSquareDistance(Square square1, Square square2) {
    return Math.abs(square1.getRow() - square2.getRow())
            + Math.abs(square1.getColumn() - square2.getColumn());
  }
}

