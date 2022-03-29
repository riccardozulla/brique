package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.ui.GraphicBoard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

  Game game = new Game(new Player("PLayer1", Color.BLACK), new Player("Player2", Color.WHITE));

  @FXML
  private AnchorPane gameView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    GraphicBoard graphicBoard = new GraphicBoard();
    graphicBoard.draw(gameView);
  }
}
