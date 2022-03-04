package it.units.sdm.brique;

public class Square {
  private final Color color;
  private Stone stone;
  private Boolean isOccupied = false;

  public Square(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public Stone getStone() {
    return stone;
  }

  public Boolean isOccupied() {
    return isOccupied;
  }

  public void setStone(Stone stone) {
    this.stone = stone;
  }

  public void toggleSquareOccupied() {
    this.isOccupied = true;
  }

}

