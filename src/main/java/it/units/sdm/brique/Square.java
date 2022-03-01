package it.units.sdm.brique;

public class Square {
  private Coordinate coordinate;
  private Color color;
  private Stone stone;

  public Square(Coordinate coordinate, Color color, Stone stone) {
    this.coordinate = coordinate;
    this.color = color;
    this.stone = stone;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public Color getColor() {
    return color;
  }

  public Stone getStone() {
    return stone;
  }

}

