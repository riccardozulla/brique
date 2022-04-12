package it.units.sdm.brique.controller;

import it.units.sdm.brique.Brique;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.model.PlayerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerInitController implements Initializable {

    @FXML
    private TextField player1TextField;
    @FXML
    private TextField player2TextField;
    @FXML
    private Button confirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        confirmButton.setDefaultButton(true);
    }

    @FXML protected void handleConfirmButtonAction(ActionEvent event) {
        Player player1 = new Player(player1TextField.getText(), Color.BLACK);
        Player player2 = new Player(player2TextField.getText(), Color.WHITE);
        PlayerHolder playerHolder = PlayerHolder.getInstance();
        playerHolder.setPlayer1(player1);
        playerHolder.setPlayer2(player2);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
            Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            currentStage.close();
            newStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
