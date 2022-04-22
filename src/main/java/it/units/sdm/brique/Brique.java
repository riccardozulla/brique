package it.units.sdm.brique;

import it.units.sdm.brique.controller.PlayerInitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Brique extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("playerinit.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Brique");
        stage.setScene(scene);
        stage.show();
        stage.setOnHidden(event -> {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/it/units/sdm/brique/game.fxml"));
                Stage newStage = new Stage();
                Scene newScene = new Scene(newRoot);
                newStage.setScene(newScene);
                newStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
