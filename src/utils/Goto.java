package utils;

import game.GameController;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
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

        Text text = new Text("PokeBattle!");
        text.setFill(Color.DARKCYAN);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 40));

        rootPane.getChildren().addAll(text, initPlayButton());
    }

    private static Button initPlayButton() {
        Button button = new Button("Play");
        button.setBorder(new Border(new BorderStroke(Color.DARKCYAN, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        button.setBackground(Background.fill(Color.WHITE));
        button.setTextFill(Color.DARKCYAN);
        button.setFont(Font.font(null, FontWeight.BOLD, 25));
        button.setPrefWidth(300);
        button.setOnMouseClicked(e -> GameController.getInstance().initGame());
        return button;
    }
}
