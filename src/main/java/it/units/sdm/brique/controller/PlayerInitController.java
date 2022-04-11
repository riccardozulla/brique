package it.units.sdm.brique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        System.out.println("Name entered 1: " + player1TextField.getText());
        System.out.println("Name entered 1: " + player2TextField.getText());
    }
}
