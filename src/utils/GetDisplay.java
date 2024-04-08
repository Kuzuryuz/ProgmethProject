package utils;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

    public static void clickSoundEffect(Node clickNode, MediaPlayer clickSound, MediaPlayer bgSound, Runnable onReleaseAction){
        clickNode.setOnMousePressed(e -> {
            clickSound.play();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> clickSound.pause());
            delay.play();
        });
        clickNode.setOnMouseReleased(e->{
            bgSound.pause();
            PauseTransition delay = new PauseTransition(Duration.seconds(0.3));
            delay.setOnFinished(event -> onReleaseAction.run());
            delay.play();
        });
    }
}
