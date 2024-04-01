package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pane.RootPane;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(RootPane.getRootPane(), 1600, 900);
        stage.setScene(scene);
        stage.setTitle("Pokemon Battle!");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
