package utils;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class GetDisplay {
    public static Text initText(String title, int size, Boolean bold, String fontFamily) {
        Text text = new Text(title);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(fontFamily, bold ? FontWeight.BOLD : FontWeight.NORMAL, size));
        return text;
    }

    public static Button initButton(String text, int width, String bgcolor) {
        Button button = new Button(text);
        button.setStyle("-fx-background-radius: 15px; -fx-background-color: " + bgcolor + "; -fx-text-fill: white; ");
        button.setFont(Font.font(null, FontWeight.BOLD, 35));
        button.setPrefWidth(width);
        return button;
    }

    public static ImageView displayImg(String imgPath) {
        String classLoaderPath = ClassLoader.getSystemResource(imgPath).toString();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(classLoaderPath));
        return imageView;
    }

    public static MediaPlayer sound(String soundPath){
        Media media = new Media(new File(soundPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }
}
