package utils;

import game.GameController;
import game.GameUtils;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
import javafx.util.Duration;
import pane.PokemonListPane;
import pane.RootPane;
import pokemon.Pokemon;
import usage.Status;

public class Goto {
    private static RootPane rootPane;
    private static Stage stage;
    private static boolean  volState = true;
    private static VBox bottomBattle;

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
        playButton.setOnMouseClicked(e -> GameController.getInstance().endGame());
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
        } else bgSound.pause();

        // init new AnchorPane
        AnchorPane playPage = new AnchorPane();

        // init new VBox and setup
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(35);

        // init page title
        ImageView title = GetDisplay.displayImg("titleAndBackground/selectPokemon.png");

        // add elements to vbox
        vbox.getChildren().add(title);

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        // add Select Pokemon button to vbox
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(100);
        for (int i = 1; i <= 2; i++) {
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(35);

            Text text = GetDisplay.initText("Player " + i, 35, true, "Verdana");
            vBox.getChildren().add(text);

            int buttonRemain = 3;
            if (!GameController.getInstance().getPlayers().get(i-1).getPokemonsParty().isEmpty()) {
                for (Pokemon pokemon : GameController.getInstance().getPlayers().get(i-1).getPokemonsParty()) {
                    Rectangle rectangle = new Rectangle(350, 75);
                    rectangle.setStyle("-fx-fill: #386abb;");
                    rectangle.setArcHeight(25);
                    rectangle.setArcWidth(25);

                    ImageView imageView = GetDisplay.displayImg(pokemon.getImgsrc());
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);

                    Text text1 = GetDisplay.initText(pokemon.getName(), 35, true, "Verdana");
                    text1.setFill(Color.WHITE);

                    HBox hBox = new HBox(imageView, text1);
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setSpacing(10);

                    StackPane stackPane = new StackPane(rectangle, hBox);

                    vBox.getChildren().add(stackPane);
                    buttonRemain--;
                }
            }

            boolean selected = true, alreadySelected = false;
            for (int k = buttonRemain; k > 0; k--) {
                if (!GameController.getInstance().getPlayerSelectTurn().equals("Player " + i)) {
                    selected = false;
                }
                Button selectPokemonButton = GetDisplay.initButton("Select Pokemon", 350,selected&&!alreadySelected? "#ffc900" : "#386abb");
                if (selected&&!alreadySelected) {
                    GetDisplay.clickSoundEffect(selectPokemonButton, clickSound, bgSound, Goto::ListPage);
                }
                vBox.getChildren().add(selectPokemonButton);
                alreadySelected = true;
            }
            hbox.getChildren().add(vBox);
        }
        vbox.getChildren().add(hbox);

        // init Back button
        Button backButton = GetDisplay.initButton("Back", 150, "#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, () -> mainPage());

        // add elements to playPage
        if (!GameUtils.isFinishChoose()) {
            // init guideline text
            Text guideline = GetDisplay.initText(GameController.getInstance().getPlayerSelectTurn() + " selects pokemon.", 25, true, null);
            vbox.getChildren().addAll(guideline);
        } else {
            // init play button
            Button playButton = GetDisplay.initButton("START", 800, "#ffc900");
            GetDisplay.clickSoundEffect(playButton, clickSound, bgSound, () -> battlePage());
            vbox.getChildren().addAll(playButton);
        }

        // set vbox to center of playPage
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);

        // set Back button to right-bottom of playPage
        AnchorPane.setRightAnchor(backButton, 20.0);
        AnchorPane.setBottomAnchor(backButton, 30.0);

        StackPane stack = new StackPane();
        // add elements to playPage
        playPage.getChildren().addAll(vbox, backButton);
        stack.getChildren().addAll(backgroundImageView,playPage);

        // add playPage to RootPane
        rootPane.getChildren().addAll(stack);
//        if(GameUtils.isFinishChoose()) {
//            PauseTransition delay = new PauseTransition(Duration.seconds(3));
//            delay.setOnFinished(e -> {
//                // After the delay
//                battlePage();
//            });
//            delay.play();
//        }
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
        Button backToPlayPage = GetDisplay.initButton("Back", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToPlayPage, clickSound, bgSound, () -> playPage());

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
        listPage.getChildren().addAll(scrollPane, backToPlayPage);

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
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, Goto::ListPage);

        Button chooseButton = GetDisplay.initButton("Choose", 450, "#386abb");
        chooseButton.setOnMouseClicked(e ->GameUtils.choosePokemon(pokemon));
        GetDisplay.clickSoundEffect(chooseButton, clickSound, bgSound, Goto::playPage);

        HBox button = new HBox(backButton, chooseButton);
        button.setAlignment(Pos.CENTER);
        button.setSpacing(50);

        detailPage.getChildren().addAll(pokemonName, pokemonElement, button);

        StackPane stack = new StackPane(backgroundImageView, detailPage);
        rootPane.getChildren().add(stack);
    }

    private static void battlePage(){
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/battle.jpeg");
        backgroundImageView.setFitHeight(500);
        backgroundImageView.setFitWidth(1400);

        // set battle page
        AnchorPane battlePage = new AnchorPane();

        ImageView leftPokemon = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0).getImgsrc());
        leftPokemon.setFitWidth(300);
        leftPokemon.setFitHeight(300);
        ImageView rightPokemon = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0).getImgsrc());
        rightPokemon.setFitWidth(300);
        rightPokemon.setFitHeight(300);

        AnchorPane.setLeftAnchor(leftPokemon, 180.0);
        AnchorPane.setBottomAnchor(leftPokemon, 20.0);

        AnchorPane.setRightAnchor(rightPokemon, 180.0);
        AnchorPane.setTopAnchor(rightPokemon, 20.0);

        Text nameLeft = GetDisplay.initText(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0).getName(), 25, true, "Verdana");
        HBox leftNameStatus = new HBox(nameLeft);

        Text statusTextL = GetDisplay.initText("PAR", 20, true, "Verdana");
        statusTextL.setFill(Color.WHITE);
        Rectangle rect1 = new Rectangle(60,30);
        rect1.setStyle("-fx-fill: #386abb;");
        rect1.setArcHeight(15);
        rect1.setArcWidth(15);
        StackPane statusLeft = new StackPane(rect1,statusTextL);
        leftNameStatus.getChildren().add(statusLeft);
        leftNameStatus.setSpacing(20);

        leftNameStatus.setSpacing(10);
        Text hpTextL = GetDisplay.initText("HP", 20, true, "Verdana");
        ProgressBar hpBarLeft = new ProgressBar(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0).getHp());
        hpBarLeft.setStyle("-fx-accent: #B4E66B");
        hpBarLeft.setPrefWidth(320);
        Text hpNumL = GetDisplay.initText(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0).getHp() +"/"+ GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0).getHp(), 20, true, "Verdana");
        HBox leftHP = new HBox(hpTextL, hpBarLeft, hpNumL);
        leftHP.setSpacing(10);
        VBox detailLeftPokemon = new VBox(leftNameStatus, leftHP);
        detailLeftPokemon.setSpacing(10);

        Text nameRight = GetDisplay.initText(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0).getName(), 25, true, "Verdana");
        HBox rightNameStatus = new HBox(nameRight);

        Text statusTextR = GetDisplay.initText("PAR", 20, true, "Verdana");
        statusTextR.setFill(Color.WHITE);
        Rectangle rect2 = new Rectangle(60,30);
        rect2.setStyle("-fx-fill: #386abb;");
        rect2.setArcHeight(15);
        rect2.setArcWidth(15);
        StackPane statusRight = new StackPane(rect2,statusTextR);
        rightNameStatus.getChildren().add(statusRight);
        rightNameStatus.setSpacing(20);

        Text hpTextR = GetDisplay.initText("HP", 20, true, "Verdana");
        ProgressBar hpBarRight = new ProgressBar(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0).getHp());
        hpBarRight.setStyle("-fx-accent: #B4E66B");
        hpBarRight.setPrefWidth(320);
        Text hpNumR = GetDisplay.initText(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0).getHp() +"/"+ GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0).getHp(), 20, true, "Verdana");
        HBox rightHP = new HBox(hpTextR, hpBarRight, hpNumR);
        rightHP.setSpacing(10);
        VBox detailRightPokemon = new VBox(rightNameStatus, rightHP);
        detailRightPokemon.setSpacing(10);

        AnchorPane.setRightAnchor(detailLeftPokemon, 115.0);
        AnchorPane.setBottomAnchor(detailLeftPokemon, 65.0);

        AnchorPane.setLeftAnchor(detailRightPokemon, 140.0);
        AnchorPane.setTopAnchor(detailRightPokemon, 72.0);

        battlePage.getChildren().addAll(leftPokemon,rightPokemon,detailLeftPokemon,detailRightPokemon);

        // set bottom page
        bottomBattle = new VBox();
        bottomBattle.setBackground((new Background(new BackgroundFill(Color.web("#FCFCDD"), CornerRadii.EMPTY, Insets.EMPTY))));
        bottomBattle.setAlignment(Pos.CENTER);
        bottomBattle.setTranslateY(500);

        actionPage();
        StackPane stackPane = new StackPane(backgroundImageView, battlePage, bottomBattle);
        rootPane.getChildren().addAll(stackPane);
    }

    private static void actionPage() {
        bottomBattle.getChildren().clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        AnchorPane actionPage = new AnchorPane();

        //make button
        Button fight = GetDisplay.initButton("Fight",400,"#ff1f1f");
        GetDisplay.clickSoundEffect(fight, clickSound, bgSound, Goto::fightPage);
        Button item = GetDisplay.initButton("Item",400,"#363b81");
        GetDisplay.clickSoundEffect(item, clickSound, bgSound, Goto::itemPage);
        Button switchB = GetDisplay.initButton("Switch",400,"#5db9ff");
        GetDisplay.clickSoundEffect(switchB, clickSound, bgSound, Goto::switchPage);
        Button surrender = GetDisplay.initButton("Surrender",400,"#fbd743");
        GetDisplay.clickSoundEffect(surrender, clickSound, bgSound, Goto::winnerPage);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        HBox hBox1 = new HBox(fight, item);
        hBox1.setSpacing(100);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(switchB, surrender);
        hBox2.setSpacing(100);
        hBox2.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setPrefWidth(1400);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        actionPage.getChildren().addAll(vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(actionPage);
    }

    private static void fightPage() {
        bottomBattle.getChildren().clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        AnchorPane fightPage = new AnchorPane();

        //make button
        Button fight1 = GetDisplay.initButton("move1",400,"#ff1f1f");
        GetDisplay.changePlayerTurn(fight1, clickSound, bgSound, Goto::actionPage);
        Button fight2 = GetDisplay.initButton("move2",400,"#ff1f1f");
        GetDisplay.changePlayerTurn(fight2, clickSound, bgSound, Goto::actionPage);
        Button fight3 = GetDisplay.initButton("move3",400,"#ff1f1f");
        GetDisplay.changePlayerTurn(fight3, clickSound, bgSound, Goto::actionPage);
        Button fight4 = GetDisplay.initButton("move4",400,"#ff1f1f");
        GetDisplay.changePlayerTurn(fight4, clickSound, bgSound, Goto::actionPage);

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 170.0);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        HBox hBox1 = new HBox(fight1, fight2);
        hBox1.setSpacing(100);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(fight3, fight4);
        hBox2.setSpacing(100);
        hBox2.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setPrefWidth(900);
        vBox.setPrefHeight(287.5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        fightPage.getChildren().addAll(backButton, vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(fightPage);
    }

    private static void itemPage() {
        bottomBattle.getChildren().clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        AnchorPane itemPage = new AnchorPane();

        //make button
        Button item1 = GetDisplay.initButton("Potion x",450,"#363b81");
        GetDisplay.changePlayerTurn(item1, clickSound, bgSound, Goto::actionPage);
        Button item2 = GetDisplay.initButton("Revive x",450,"#363b81");
        GetDisplay.changePlayerTurn(item2, clickSound, bgSound, Goto::actionPage);
        Button item3 = GetDisplay.initButton("Full Restore x",450,"#363b81");
        GetDisplay.changePlayerTurn(item3, clickSound, bgSound, Goto::actionPage);

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 170.0);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        HBox hBox = new HBox(item1, item2);
        hBox.setSpacing(100);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hBox, item3);
        vBox.setPrefWidth(900);
        vBox.setPrefHeight(287.5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        itemPage.getChildren().addAll(backButton, vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(itemPage);
    }

    private static void switchPage() {
        bottomBattle.getChildren().clear();

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        AnchorPane switchPage = new AnchorPane();

        Rectangle rect1 = new Rectangle(400, 75);
        rect1.setStyle("-fx-fill: #5db9ff;");
        rect1.setArcHeight(25);
        rect1.setArcWidth(25);

        ImageView imageView1 = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(1).getImgsrc());
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(60);

        Text text1 = GetDisplay.initText(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(1).getName(), 35, true, "Verdana");
        text1.setFill(Color.WHITE);

        HBox hBox1 = new HBox(imageView1, text1);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(10);

        StackPane pokemon1 = new StackPane(rect1, hBox1);
        GetDisplay.changePlayerTurn(pokemon1, clickSound, bgSound, Goto::actionPage);

        Rectangle rect2 = new Rectangle(400, 75);
        rect2.setStyle("-fx-fill: #5db9ff;");
        rect2.setArcHeight(25);
        rect2.setArcWidth(25);

        ImageView imageView2 = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(2).getImgsrc());
        imageView2.setFitWidth(60);
        imageView2.setFitHeight(60);

        Text text2 = GetDisplay.initText(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(2).getName(), 35, true, "Verdana");
        text2.setFill(Color.WHITE);

        HBox hBox2 = new HBox(imageView2, text2);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(10);

        StackPane pokemon2 = new StackPane(rect2, hBox2);
        GetDisplay.changePlayerTurn(pokemon2, clickSound, bgSound, Goto::actionPage);

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, bgSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 117.5);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -138.5);

        HBox hBox = new HBox(pokemon1, pokemon2);
        hBox.setSpacing(100);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,220,0));

        AnchorPane.setLeftAnchor(hBox, 250.0);
        AnchorPane.setRightAnchor(hBox, 250.0);

        switchPage.getChildren().addAll(backButton, hBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().addAll(switchPage);
    }

    private static void winnerPage(){
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/winnerBG.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        MediaPlayer bgSound = GetDisplay.sound("res/sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        }else bgSound.pause();

        Text winner = GetDisplay.initText("WINNER", 80, true, "Verdana");
        winner.setFill(Color.WHITE);
        MediaPlayer clickSound = GetDisplay.sound("res/sound/clickSound.mp3");
        clickSound.setVolume(0.4);

        Rectangle rect = new Rectangle(800,300);
        rect.setArcWidth(25);
        rect.setArcHeight(25);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(5);
        rect.setFill(Color.web("#386abb"));

        Text winnerPlayer = GetDisplay.initText("Player X", 80, true, "Verdana");
        winnerPlayer.setFill(Color.web("#ffc900"));

        StackPane winnerBlock = new StackPane(rect, winnerPlayer);

        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToMainMenu, clickSound, bgSound, () -> mainPage());

        VBox vBox = new VBox(winner, winnerBlock, backToMainMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        StackPane stack = new StackPane(backgroundImageView, vBox);
        rootPane.getChildren().add(stack);
    }
}