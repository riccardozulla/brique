package it.units.sdm.brique;

public class Stone {
  private Color color;
  private int x;
  private int y;

  public Stone(Color color) {
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
}
