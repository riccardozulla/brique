package it.units.sdm.brique.controller;

import it.units.sdm.brique.Brique;
import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.model.PlayerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerInitController implements Initializable {

    private boolean swapHappened = false;

    @FXML
    private TextField player1TextField;
    @FXML
    private TextField player2TextField;
    @FXML
    private ImageView player1StoneImageView;
    @FXML
    private ImageView player2StoneImageView;
    @FXML
    private ToggleButton swapButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO: Use CSS file instead
        ImageView img = new ImageView(new Image("https://icon-library.com/images/swap-icon/swap-icon-14.jpg"));
        img.setFitWidth(50);
        img.setFitHeight(50);
        swapButton.setGraphic(img);
        System.out.println(swapButton.getBackground());
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.8);
        player1StoneImageView.setEffect(blackout);
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

    @FXML protected void handleToggleButtonAction(ActionEvent event) {
        if(!swapHappened) {
            player1StoneImageView.setEffect(null);
            ColorAdjust blackout = new ColorAdjust();
            blackout.setBrightness(-0.8);
            player2StoneImageView.setEffect(blackout);
            swapHappened = true;
        } else {
            player2StoneImageView.setEffect(null);
            ColorAdjust blackout = new ColorAdjust();
            blackout.setBrightness(-0.8);
            player1StoneImageView.setEffect(blackout);
            swapHappened = false;
        }
    }
}
