package it.units.sdm.brique;

public class Stone {
  private Color color;
  private final int x;
  private final int y;

  public Stone(int x, int y, Color color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int distance (Stone otherStone) {
    return Math.abs (this.x - otherStone.x) + Math.abs (this.y - otherStone.y);
  }

  public boolean equalsCoordinates(Stone stone) {
    return x==stone.x && y==stone.y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stone stone = (Stone) o;
    if (x != stone.x) return false;
    if (y != stone.y) return false;
    return color == stone.color;
  }
}
