package utils;

import game.GameUtils;
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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

    public static void clickSoundEffect(Node clickNode, MediaPlayer clickSound, Runnable onReleaseAction){
        clickNode.setOnMousePressed(e -> {
            clickSound.stop();
            clickSound.play();
        });
        clickNode.setOnMouseReleased(e->{
            onReleaseAction.run();
        });
    }

    public static String getColorOfType(String type){
        Map<String, String> colorOfType = new HashMap<>();
        colorOfType.put("NORMAL", "#AAB09F");
        colorOfType.put("BUG", "#94BC4A");
        colorOfType.put("DARK", "#736C75");
        colorOfType.put("DRAGON", "#6A7BAF");
        colorOfType.put("ELECTRIC", "#E5C531");
        colorOfType.put("FAIRY", "#E397D1");
        colorOfType.put("FIGHTING", "#CB5F48");
        colorOfType.put("FIRE", "#EA7A3C");
        colorOfType.put("FLYING", "#7DA6DE");
        colorOfType.put("GHOST", "#846AB6");
        colorOfType.put("GRASS", "#71C558");
        colorOfType.put("GROUND", "#CC9F4F");
        colorOfType.put("ICE", "#70CBD4");
        colorOfType.put("POISON", "#B468B7");
        colorOfType.put("PSYCHIC", "#E5709B");
        colorOfType.put("ROCK", "#B2A061");
        colorOfType.put("STEEL", "#89A1B0");
        colorOfType.put("WATER", "#539AE2");
        return colorOfType.get(type);
    }
}
