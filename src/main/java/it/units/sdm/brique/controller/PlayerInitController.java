package it.units.sdm.brique.controller;

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
import javafx.scene.control.Alert;
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
        String url = getClass().getResource("../images/swap.png").toString();
        ImageView img = new ImageView(new Image(url));
        img.setFitWidth(40);
        img.setFitHeight(40);
        swapButton.setGraphic(img);
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.8);
        player1StoneImageView.setEffect(blackout);
    }

    @FXML protected void handleConfirmButtonAction(ActionEvent event) {
        if(player1TextField.getText().equals(player2TextField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Nickname error!");
            alert.setContentText("You entered the same nickname for both players. "
                    + "\nPlease choose a different nickname.");
            alert.showAndWait();
        }
        else {
            savePlayerData();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage newStage = new Stage();
                Scene scene = new Scene(root);
                newStage.setScene(scene);
                currentStage.close();
                newStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML protected void handleToggleButtonAction(ActionEvent event) {
        if(swapHappened)
            swapColor(player2StoneImageView, player1StoneImageView);
        else
            swapColor(player1StoneImageView, player2StoneImageView);
    }

    private void swapColor(ImageView img1, ImageView img2) {
        img1.setEffect(null);
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.8);
        img2.setEffect(blackout);
        swapHappened = !swapHappened;
    }

    private void savePlayerData() {
        Player player1 = new Player(player1TextField.getText(), swapHappened ? Color.WHITE : Color.BLACK);
        Player player2 = new Player(player2TextField.getText(), swapHappened ? Color.BLACK : Color.WHITE);
        PlayerHolder playerHolder = PlayerHolder.getInstance();
        playerHolder.setPlayer1(player1);
        playerHolder.setPlayer2(player2);
    }
}
