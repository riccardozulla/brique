package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Game;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.ui.GraphicBoard;
import it.units.sdm.brique.ui.GraphicSquare;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable, PropertyChangeListener {

    Game game = new Game(new Player("PLayer1", Color.BLACK), new Player("Player2", Color.WHITE));
    GraphicBoard graphicBoard;

    @FXML
    private TilePane gameView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicBoard = new GraphicBoard();
        graphicBoard.draw(gameView);
        graphicBoard.setOnMouseClicked(event -> {
            if (event.isStillSincePress() && event.getTarget() instanceof GraphicSquare)
                game.addStoneAndCheckEscortRule(((GraphicSquare) event.getTarget()).getSquare());
        });
        gameView.setPrefSize(400, 400);
        gameView.setAlignment(Pos.CENTER);
        gameView.setStyle("-fx-background-color: #89c4ff");//dff0d8
        game.addActivePlayerListener(this);
        game.addStatusListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        switch(propertyChangeEvent.getPropertyName()) {
            case "activePlayer" -> graphicBoard.update();
            case "status" -> {
                System.out.println(propertyChangeEvent.getNewValue());
                graphicBoard.update();
                graphicBoard.setDisable(true);
            }
        }
    }
}