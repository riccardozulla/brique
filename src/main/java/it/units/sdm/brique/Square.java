package it.units.sdm.brique;

public class Square {
  private final Color color;
  private Stone stone;
  private Boolean isOccupied = false;
  private Coordinate coordinate;

  public Square(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public Stone getStone() {
    return stone;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public Boolean getIsOccupied() {
    return isOccupied;
  }

  public void setStone(Stone stone) {
    this.stone = stone;
  }

  public void toggleSquareOccupied() {
    this.isOccupied = true;
  }

}

