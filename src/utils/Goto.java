package utils;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import pane.PokemonListPane;
import pane.RootPane;
import pokemon.Pokemon;

public class Goto {
    private static RootPane rootPane;
    private static Stage stage;
    private static boolean  volState = true;

    public static boolean getVolState() {
        return volState;
    }

    public static void setVolState(boolean volState) {
        Goto.volState = volState;
    }

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
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        ImageView soundON = GetDisplay.displayImg("soundON.png");
        ImageView soundOFF = GetDisplay.displayImg("soundOFF.png");
        if (getVolState()) {
            bgSound.play();
            soundOFF.setVisible(false);
        }else{
            bgSound.pause();
            soundON.setVisible(false);
        }
        soundON.setFitWidth(30);
        soundON.setFitHeight(30);
        soundON.setOnMouseClicked(e -> {
            bgSound.pause();
            setVolState(false);
            soundON.setVisible(false);
            soundOFF.setVisible(true);
        });
        soundOFF.setFitWidth(30);
        soundOFF.setFitHeight(30);
        soundOFF.setOnMouseClicked(e -> {
            bgSound.play();
            setVolState(true);
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
        ImageView mainPageTitle = GetDisplay.displayImg("pokebattle.png");

        // init Region for spacing
        Region spacer = new Region();
        spacer.setPrefHeight(80);

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        // init Play button
        Button playButton = GetDisplay.initButton("Play", 350, "#386abb");
        GetDisplay.clickSoundEffect(playButton, clickSound, bgSound, () -> playPage());

        // init How to Play button
        Button howToPlayButton = GetDisplay.initButton("How to Play", 350, "#386abb");
        GetDisplay.clickSoundEffect(howToPlayButton, clickSound, bgSound, () -> howToPlayPage());

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
        mainPage.getChildren().addAll(mainPageTitle, spacer, playButton, howToPlayButton, exitButton, soundBox);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(backgroundImageView, mainPage);
        // add mainPage to RootPane
        rootPane.getChildren().add(stack);
    }

    private static void playPage() {
        clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

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

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        // add Select Pokemon button to vbox
        for (int i = 0; i < 3; i++) {
            // init new TilePane for Select Pokemon button
            TilePane tilePane = new TilePane();
            tilePane.setAlignment(Pos.CENTER);
            tilePane.setHgap(100);

            // init Select Pokemon Button
            for (int k = 0; k < 2; k++) {
                Button selectPokemonButton = GetDisplay.initButton("Select Pokemon", 350, i==0&&k==0? "#C3C3C3" : "#969696");
                GetDisplay.clickSoundEffect(selectPokemonButton, clickSound, bgSound, () -> ListPage());
                tilePane.getChildren().add(selectPokemonButton);
            }

            // add elements to vbox
            vbox.getChildren().addAll(tilePane);
        }

        // init guideline text
        Text guideline = GetDisplay.initText("Player 1 select 1st Pokemon", 25, true, null);

        // init Back button
        Button backButton = GetDisplay.initButton("Back", 150, "#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, () -> mainPage());

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

        StackPane stack = new StackPane();
        // add elements to playPage
        playPage.getChildren().addAll(vbox, backButton);
        stack.getChildren().addAll(playPage);

        // add playPage to RootPane
        rootPane.getChildren().addAll(stack);
    }

    private static void howToPlayPage() {
        clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        ImageView backgroundImageView = GetDisplay.displayImg("pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        // init new VBox and setup
        VBox howToPlayPage = new VBox();
        howToPlayPage.setAlignment(Pos.CENTER);
        howToPlayPage.setPadding(new Insets(60,0,50,0));

        // init page title
        //Text title = GetDisplay.initText("How to Play", 50, true, "Verdana");
        ImageView howToPlayTitle = GetDisplay.displayImg("howToPlay.png");

        // mock box
        Rectangle rect = new Rectangle(900,450);
        rect.setFill(Color.WHITE);
        rect.setStroke(Paint.valueOf("#386abb"));
        rect.setStrokeWidth(3);
        rect.setArcWidth(30);
        rect.setArcHeight(30);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox vBoxIN = new VBox();
        vBoxIN.setPrefSize(900,450);

        Text explanation = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo " +
                "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla " +
                "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit " +
                "anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
                "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat " +
                "cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor" +
                " sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna " +
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea");
        explanation.setLineSpacing(15);
        explanation.setWrappingWidth(800);
        explanation.setFont(Font.font(20));
        vBoxIN.getChildren().addAll(explanation);
        scrollPane.setContent(vBoxIN);
        VBox vBoxOUT = new VBox(scrollPane);
        vBoxOUT.setPadding(new Insets(75,0,75,300));

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        // init Back to Main Menu button
        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToMainMenu, clickSound, bgSound, () -> mainPage());

        StackPane explanationBox = new StackPane(rect, vBoxOUT);
        explanationBox.setAlignment(Pos.CENTER);
        StackPane stack = new StackPane();
        // add elements to howToPlayPage
        howToPlayPage.getChildren().addAll(howToPlayTitle, explanationBox, backToMainMenu);
        stack.getChildren().addAll(backgroundImageView, howToPlayPage);

        // add howToPlayPage to RootPane
        rootPane.getChildren().addAll(stack);
    }

    private static void ListPage() {
        clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        VBox listPage = new VBox();
        listPage.setAlignment(Pos.CENTER);
        listPage.setSpacing(40);
        listPage.setPadding(new Insets(60,0,60,0));

        Text title = GetDisplay.initText("Pokémon List",50,true,"Verdana");
        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToMainMenu, clickSound, bgSound, () -> mainPage());

        listPage.getChildren().add(title);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox pokemonContainer = new VBox();
        pokemonContainer.setAlignment(Pos.CENTER);
        pokemonContainer.setPrefSize(1400,450);
        pokemonContainer.setSpacing(30);

        for (int i=0, p=0; i<5; i++){
            HBox pokemonInfo = new HBox();
            pokemonInfo.setAlignment(Pos.CENTER);
            pokemonInfo.setSpacing(50);

            for (int j=0; j<4 && p < PokemonListPane.getInstance().getPokemons().size(); j++, p++) {
                Pokemon pokemon = PokemonListPane.getInstance().getPokemons().get(p);
                ImageView pokemonImg = GetDisplay.displayImg(pokemon.getImgsrc());
                pokemonImg.setFitHeight(180);
                pokemonImg.setFitWidth(180);
                Text pokemonName = GetDisplay.initText(pokemon.getName(), 18, true, "Verdana");
                VBox pokemonElement = new VBox();
                pokemonElement.setAlignment(Pos.CENTER);
                pokemonElement.getChildren().addAll(pokemonImg, pokemonName);
                pokemonElement.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px; -fx-background-radius: 10px;");
                pokemonElement.setSpacing(20);

                pokemonElement.setOnMouseEntered(e->{
                    pokemonElement.setStyle("-fx-background-color: #d6d4d4; -fx-padding: 10px; -fx-background-radius: 10px;");
                });
                pokemonElement.setOnMouseExited(e->{
                    pokemonElement.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px; -fx-background-radius: 10px;");
                });
                pokemonInfo.getChildren().addAll(pokemonElement);
            }
            pokemonContainer.getChildren().add(pokemonInfo);
        }
        scrollPane.setContent(pokemonContainer);
        listPage.getChildren().addAll(scrollPane, backToMainMenu);
        rootPane.getChildren().addAll(listPage);
    }

    private static void detailpage() {

    }
}