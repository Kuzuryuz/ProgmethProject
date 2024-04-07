package utils;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GetDisplay {
    public static Text initText(String title, int size, Boolean bold, String fontFamily) {
        Text text = new Text(title);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(fontFamily, bold? FontWeight.BOLD : FontWeight.NORMAL, size));
        return text;
    }

    public static Button initButton(String text, int width, String bgcolor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 15px; -fx-background-color: " + bgcolor + "; -fx-text-fill: white; ");
        button.setFont(Font.font(null, FontWeight.BOLD, 35));
        button.setPrefWidth(width);
        return button;
    }
}
