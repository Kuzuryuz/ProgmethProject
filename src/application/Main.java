package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pane.RootPane;
import utils.Goto;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Goto.setStage(stage);

        Scene scene = new Scene(RootPane.getRootPane(), 1400, 787.5);
        stage.setScene(scene);
        stage.setTitle("PokeBattle!");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
