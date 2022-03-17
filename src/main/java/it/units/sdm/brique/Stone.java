package it.units.sdm.brique;

public class Stone {
  private Color color;

  public Stone(Color color) {
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public void switchColor(){
    color = color == Color.BLACK ? Color.WHITE : Color.BLACK;
    //todo add tests
  }
}
