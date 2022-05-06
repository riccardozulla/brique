package it.units.sdm.brique.controller;

import it.units.sdm.brique.model.Color;
import it.units.sdm.brique.model.Player;
import it.units.sdm.brique.utility.PlayerHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane welcomeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ColorAdjust blackout = new ColorAdjust();
        blackout.setBrightness(-0.8);
        player1StoneImageView.setEffect(blackout);
        welcomeView.getStylesheets().addAll("it/units/sdm/brique/style.css", "it/units/sdm/brique/playerInit_style.css");
    }

    @FXML
    protected void handleConfirmButtonAction(ActionEvent event) throws Exception {
        if (isNicknameEmpty()) {
            displayNicknameErrorAlert("The nickname can't be empty." +
                    "\nPlease choose a nickname.");
        } else if (isSameNickname()) {
            displayNicknameErrorAlert("The two players can't have the same nickname." +
                    "\nPlease choose a different nickname.");
        } else if (isNicknameTooLong()) {
            displayNicknameErrorAlert("The nickname can't exceed 15 characters." +
                    "\nPlease choose a shorter nickname.");
        } else {
            savePlayerData();
            Parent gameView = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage secondaryStage = new Stage();
            Scene newScene = new Scene(gameView);
            secondaryStage.setScene(newScene);
            secondaryStage.setMinWidth(400);
            secondaryStage.setMinHeight(500);
            secondaryStage.show();
            currentStage.close();
        }
    }

    @FXML
    protected void handleToggleButtonAction(ActionEvent event) {
        swapColor();
    }

    private void swapColor() {
        ImageView img1 = swapHappened ? player2StoneImageView : player1StoneImageView;
        ImageView img2 = swapHappened ? player1StoneImageView : player2StoneImageView;
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

    private boolean isSameNickname() {
        return player1TextField.getText().equals(player2TextField.getText());
    }

    private boolean isNicknameEmpty() {
        return player1TextField.getText().isEmpty() || player2TextField.getText().isEmpty();
    }

    private boolean isNicknameTooLong() {
        return player1TextField.getText().length() >= 15 || player2TextField.getText().length() >= 15;
    }

    private void displayNicknameErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Nickname error!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
