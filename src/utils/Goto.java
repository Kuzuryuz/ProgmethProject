package utils;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import pane.RootPane;

import java.io.File;

public class Goto {
    private static RootPane rootPane;
    private static Stage stage;
    public static StackPane stack = new StackPane();
    public static void setRootPane(RootPane rootPane) {

        Goto.rootPane = rootPane;
    }
    public static void setStage(Stage stage) { Goto.stage = stage; }

    private static void clear() {
        rootPane.getChildren().clear();
    }

    public static void mainPage() {
        clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.play();

        ImageView soundON = GetDisplay.displayImg("soundON.png");
        ImageView soundOFF = GetDisplay.displayImg("soundOFF.png");
        soundOFF.setVisible(false);
        soundON.setFitWidth(30);
        soundON.setFitHeight(30);
        soundON.setOnMouseClicked(e -> {
            bgSound.pause();
            soundON.setVisible(false);
            soundOFF.setVisible(true);
        });
        soundOFF.setFitWidth(30);
        soundOFF.setFitHeight(30);
        soundOFF.setOnMouseClicked(e -> {
            bgSound.play();
            soundON.setVisible(true);
            soundOFF.setVisible(false);
        });

        StackPane soundStatus = new StackPane(soundON,soundOFF);
        ImageView backgroundImageView = GetDisplay.displayImg("pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        // init new VBox and setup
        VBox mainPage = new VBox();
        mainPage.setAlignment(Pos.CENTER);
        mainPage.setPadding(new Insets(80, 0, 0, 0));
        mainPage.setSpacing(25);

        // init game title
        //Text title = GetDisplay.initText("PokeBattle!", 70, true, "Verdana");
        ImageView MainPageTitle = GetDisplay.displayImg("pokebattle.png");

        // init Region for spacing
        Region spacer = new Region();
        spacer.setPrefHeight(80);

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");

        // init Play button
        Button playButton = GetDisplay.initButton("Play", 350, "#386abb");
        playButton.setOnMousePressed(e -> {
            clickSound.play();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> clickSound.pause());
            delay.play();
        });
        playButton.setOnMouseReleased(e->{
            bgSound.pause();
            PauseTransition delay = new PauseTransition(Duration.seconds(0.3));
            delay.setOnFinished(event -> playPage());
            delay.play();
        });

        // init How to Play button
        Button howToPlayButton = GetDisplay.initButton("How to Play", 350, "#386abb");
        howToPlayButton.setOnMousePressed(e -> {
            clickSound.play();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> clickSound.pause());
            delay.play();
        });
        howToPlayButton.setOnMouseReleased(e -> {
            bgSound.pause();
            PauseTransition delay = new PauseTransition(Duration.seconds(0.3));
            delay.setOnFinished(event -> howToPlayPage());
            delay.play();
        });

        // init Exit button
        Button exitButton = GetDisplay.initButton("Exit", 350, "#386abb");
        exitButton.setOnAction(e -> {
            stage.close();
            Platform.exit();
        });

        HBox soundBox = new HBox();
        soundBox.setAlignment(Pos.TOP_RIGHT);
        soundBox.getChildren().add(soundStatus);
        soundBox.setPadding(new Insets(0, 50, 0, 0));

        // add elements to mainPage
        mainPage.getChildren().addAll(MainPageTitle, spacer, playButton, howToPlayButton, exitButton, soundBox);
        stack.getChildren().addAll(backgroundImageView, mainPage);
        // add mainPage to RootPane
        rootPane.getChildren().add(stack);
    }

    private static void playPage() {
        clear();

        // init new AnchorPane
        AnchorPane playPage = new AnchorPane();
        playPage.setMinHeight(787.5);

        // init new VBox and setup
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(35);

        // init page title
        Text title = GetDisplay.initText("Select Pokemon", 70, true, null);

        // init new TilePane for playerText
        TilePane playerText = new TilePane();
        playerText.setAlignment(Pos.CENTER);
        playerText.setHgap(300);

        // init player text
        Text player1 = GetDisplay.initText("Player 1", 40, false, null);
        Text player2 = GetDisplay.initText("Player 2", 40, false, null);

        // add elements to tilePane
        playerText.getChildren().addAll(player1, player2);

        // add elements to vbox
        vbox.getChildren().addAll(title, playerText);

        // add Select Pokemon button to vbox
        for (int i = 0; i < 3; i++) {
            // init new TilePane for Select Pokemon button
            TilePane tilePane = new TilePane();
            tilePane.setAlignment(Pos.CENTER);
            tilePane.setHgap(100);

            // init Select Pokemon Button
            for (int k = 0; k < 2; k++) {
                Button selectPokemonButton = GetDisplay.initButton("Select Pokemon", 350, i==0&&k==0? "#C3C3C3" : "#969696");
                tilePane.getChildren().add(selectPokemonButton);
            }

            // add elements to vbox
            vbox.getChildren().addAll(tilePane);
        }

        // init guideline text
        Text guideline = GetDisplay.initText("Player 1 select 1st Pokemon", 25, true, null);

        // init Back button
        Button backButton = GetDisplay.initButton("Back", 150, "#386abb");
        backButton.setOnAction(e -> mainPage());

        // add elements to playPage
        vbox.getChildren().addAll(guideline);

        // set vbox to center of playPage
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);

        // set Back button to right-bottom of playPage
        AnchorPane.setRightAnchor(backButton, 20.0);
        AnchorPane.setBottomAnchor(backButton, 20.0);

        // add elements to playPage
        playPage.getChildren().addAll(vbox, backButton);

        // add playPage to RootPane
        rootPane.getChildren().addAll(playPage);
    }

    private static void howToPlayPage() {
        clear();

        // init new VBox and setup
        VBox howToPlayPage = new VBox();
        howToPlayPage.setAlignment(Pos.CENTER);
        howToPlayPage.setPadding(new Insets(60,0,0,0));
        howToPlayPage.setSpacing(40);

        // init page title
        Text title = GetDisplay.initText("How to Play", 50, true, "Verdana");

        // mock box
        Rectangle mock = new Rectangle(900,450);

        // init Back to Main Menu button
        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#868BFF");
        backToMainMenu.setOnAction(e -> mainPage());

        // add elements to howToPlayPage
        howToPlayPage.getChildren().addAll(title, mock, backToMainMenu);

        // add howToPlayPage to RootPane
        rootPane.getChildren().addAll(howToPlayPage);
    }
}
