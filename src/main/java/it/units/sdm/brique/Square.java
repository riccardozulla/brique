package it.units.sdm.brique;

public class Square {
  private final Color color;
  private Stone stone;
  private final Coordinate coordinate;

  public Square(Coordinate coordinate, Color color) {
    this.coordinate = coordinate;
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

  public void setStone(Stone stone) {
    this.stone = stone;
  }

}

