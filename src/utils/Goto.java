package utils;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pane.RootPane;

public class Goto {
    private static RootPane rootPane;

    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }

    public static void clear() {
        rootPane.getChildren().clear();
    }

    public static void mainPage() {
        clear();

        Text text = new Text("Pokemon Battle!");
        text.setFill(Color.DARKCYAN);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 32));

        Button playButton = new Button("Play");

        rootPane.getChildren().addAll(text, playButton);
    }
}
