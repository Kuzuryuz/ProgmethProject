package utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

        ImageView soundON = GetDisplay.displayImg("sound/soundON.png");
        ImageView soundOFF = GetDisplay.displayImg("sound/soundOFF.png");
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

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        // init new VBox and setup
        VBox mainPage = new VBox();
        mainPage.setAlignment(Pos.CENTER);
        mainPage.setPadding(new Insets(80, 0, 0, 0));
        mainPage.setSpacing(25);

        // init game title
        //Text title = GetDisplay.initText("PokeBattle!", 70, true, "Verdana");
        ImageView mainPageTitle = GetDisplay.displayImg("titleAndBackground/pokebattle.png");

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
        StackPane stack = new StackPane(backgroundImageView, mainPage);
        // add mainPage to RootPane
        rootPane.getChildren().add(stack);
    }

    private static void playPage() {
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

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
        ImageView title = GetDisplay.displayImg("titleAndBackground/selectPokemon.png");

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
                Button selectPokemonButton = GetDisplay.initButton("Select Pokemon", 350, i==0&&k==0? "#ffc900" : "#386abb");
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
        stack.getChildren().addAll(backgroundImageView,playPage);

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

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        // init new VBox and setup
        VBox howToPlayPage = new VBox();
        howToPlayPage.setAlignment(Pos.CENTER);
        howToPlayPage.setPadding(new Insets(60,0,50,0));

        // init page title
        //Text title = GetDisplay.initText("How to Play", 50, true, "Verdana");
        ImageView howToPlayTitle = GetDisplay.displayImg("titleAndBackground/howToPlay.png");

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
        // add elements to howToPlayPage
        howToPlayPage.getChildren().addAll(howToPlayTitle, explanationBox, backToMainMenu);
        StackPane stack = new StackPane(backgroundImageView, howToPlayPage);

        // add howToPlayPage to RootPane
        rootPane.getChildren().addAll(stack);
    }

    private static void ListPage() {
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/skyBackground.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

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
        title.setStyle("-fx-fill: white;");
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

            for (int j = 0; j<4 && p < PokemonListPane.getInstance().getPokemons().size(); j++, p++) {
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

                int indexP = p;
                GetDisplay.clickSoundEffect(pokemonElement, clickSound, bgSound, () -> detailPage(indexP));
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

        StackPane stack = new StackPane(backgroundImageView, listPage);
        rootPane.getChildren().add(stack);
    }

    private static void detailPage(int index) {
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/skyBackground.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        VBox detailPage = new VBox();
        detailPage.setPadding(new Insets(80,300,80,300));
        detailPage.setSpacing(40);
        detailPage.setAlignment(Pos.CENTER_LEFT);

        Pokemon pokemon = PokemonListPane.getInstance().getPokemons().get(index);
        ImageView pokemonImg = GetDisplay.displayImg(pokemon.getImgsrc());
        DropShadow imgShadow = new DropShadow();
        imgShadow.setColor(Color.WHITE);
        imgShadow.setRadius(50);
        pokemonImg.setEffect(imgShadow);
        pokemonImg.setFitHeight(400);
        pokemonImg.setFitWidth(400);

        Text pokemonName = GetDisplay.initText(pokemon.getName(), 60, true, "Verdana");
        pokemonName.setStyle("-fx-fill: white;");

        Text pType = GetDisplay.initText("Type: ", 20, true, "Verdana");
        Text pHP = GetDisplay.initText("HP: ", 20, true, "Verdana");
        Text pAtk = GetDisplay.initText("Attack: ", 20, true, "Verdana");
        Text pDef = GetDisplay.initText("Defense: ", 20, true, "Verdana");
        Text pSpAtk = GetDisplay.initText("Sp.Attack: ", 20, true, "Verdana");
        Text pSpDef = GetDisplay.initText("Sp.Defense: ", 20, true, "Verdana");
        Text pSpeed = GetDisplay.initText("Speed: ", 20, true, "Verdana");

        HBox pokemonType = new HBox(pType);
        pokemonType.setSpacing(20);

        if (!pokemon.getType().name().equals("NULL")){
            Button pokemonType1 = new Button(pokemon.getType().name());
            pokemonType1.setMinWidth(150);
            String buttonColor1 = GetDisplay.getColorOfType(pokemon.getType().name());
            pokemonType1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: " + buttonColor1 + "; -fx-padding: 10px; -fx-background-radius: 10px;");
            pokemonType.getChildren().add(pokemonType1);
        }

        if (!pokemon.getType2().name().equals("NULL")){
            Button pokemonType2 = new Button(pokemon.getType2().name());
            pokemonType2.setMinWidth(150);
            String buttonColor2 = GetDisplay.getColorOfType(pokemon.getType2().name());
            pokemonType2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: " + buttonColor2 + "; -fx-padding: 10px; -fx-background-radius: 10px;");
            pokemonType.getChildren().add(pokemonType2);
        }

        Text pokemonHP = GetDisplay.initText(String.valueOf(pokemon.getHp()), 20, true, "Verdana");
        Text pokemonAtk = GetDisplay.initText(String.valueOf(pokemon.getAtk()), 20, true, "Verdana");
        Text pokemonDef = GetDisplay.initText(String.valueOf(pokemon.getDef()), 20, true, "Verdana");
        Text pokemonSpAtk = GetDisplay.initText(String.valueOf(pokemon.getSpa()), 20, true, "Verdana");
        Text pokemonSpDef = GetDisplay.initText(String.valueOf(pokemon.getSpd()), 20, true, "Verdana");
        Text pokemonSpeed = GetDisplay.initText(String.valueOf(pokemon.getSpe()), 20, true, "Verdana");

        VBox statTopic = new VBox(pokemonType,pHP,pAtk,pDef,pSpAtk,pSpDef,pSpeed);
        statTopic.setSpacing(20);
        statTopic.setPadding(new Insets(0,110,0,0));
        VBox pokemonStat = new VBox(pokemonHP,pokemonAtk,pokemonDef,pokemonSpAtk,pokemonSpDef,pokemonSpeed);
        pokemonStat.setSpacing(20);
        pokemonStat.setAlignment(Pos.CENTER_RIGHT);
        pokemonStat.setPadding(new Insets(0,0,0,110));

        HBox stat = new HBox(statTopic,pokemonStat);
        VBox allDetail = new VBox(pokemonType,stat);
        allDetail.setAlignment(Pos.CENTER_LEFT);
        allDetail.setSpacing(20);
        allDetail.setPadding(new Insets(0,50,0,50));
        allDetail.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10px;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(50);
        allDetail.setEffect(dropShadow);

        HBox pokemonElement = new HBox(pokemonImg, allDetail);
        pokemonElement.setSpacing(100);

        Button backButton = GetDisplay.initButton("Back", 450, "#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, () -> ListPage());

        Button chooseButton = GetDisplay.initButton("Choose", 450, "#386abb");
        GetDisplay.clickSoundEffect(chooseButton, clickSound, bgSound, () -> playPage());

        HBox button = new HBox(backButton, chooseButton);
        button.setAlignment(Pos.CENTER);
        button.setSpacing(50);

        detailPage.getChildren().addAll(pokemonName, pokemonElement, button);

        StackPane stack = new StackPane(backgroundImageView, detailPage);
        rootPane.getChildren().add(stack);
    }
}