package utils;

import game.GameController;
import game.GameUtils;
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
import pane.PokemonListPane;
import pane.RootPane;
import player.Player;
import pokemon.Pokemon;
import skill.BaseSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Goto {
    private static RootPane rootPane;
    private static Stage stage;
    private static MediaPlayer bgSound, clickSound;
    private static boolean volState = true;
    private static VBox bottomBattle;

    private static boolean getVolState() {
        return volState;
    }

    private static void setVolState(boolean volState) {
        Goto.volState = volState;
    }

    public static void setRootPane(RootPane rootPane) {

        Goto.rootPane = rootPane;
    }

    public static void setStage(Stage stage) { Goto.stage = stage; }

    private static void clear() {
        rootPane.getChildren().clear();
    }

    private static void initBgSound() {
        bgSound = GetDisplay.sound("sound/MainPage.mp3");
        bgSound.setCycleCount(MediaPlayer.INDEFINITE);
        bgSound.setVolume(0.2);
        if (getVolState()) {
            bgSound.play();
        } else bgSound.pause();
    }

    private static void initClickSound() {
        clickSound = GetDisplay.sound("sound/clickSound.mp3");
        clickSound.setVolume(0.4);
    }

    public static void mainPage() {
        clear();

        // init sound
        if (bgSound == null) {
            initBgSound();
        }
        if (clickSound == null) {
            initClickSound();
        }

        // on-off sound button
        ImageView soundON = GetDisplay.displayImg("sound/soundON.png");
        ImageView soundOFF = GetDisplay.displayImg("sound/soundOFF.png");

        if (getVolState()) {
            bgSound.play();
            soundOFF.setVisible(false);
        } else {
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

        HBox soundBox = new HBox();
        soundBox.setAlignment(Pos.TOP_RIGHT);
        soundBox.getChildren().add(soundStatus);
        soundBox.setPadding(new Insets(0, 50, 0, 0));

        // init background image
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

        // init Play button
        Button playButton = GetDisplay.initButton("Play", 350, "#386abb");
        GetDisplay.clickSoundEffect(playButton, clickSound, () -> {
            GameController.getInstance().newGame();
            playPage();
        });

        // init How to Play button
        Button howToPlayButton = GetDisplay.initButton("How to Play", 350, "#386abb");
        GetDisplay.clickSoundEffect(howToPlayButton, clickSound, Goto::howToPlayPage);

        // init Exit button
        Button exitButton = GetDisplay.initButton("Exit", 350, "#386abb");
        exitButton.setOnAction(e -> {
            stage.close();
            Platform.exit();
        });

        // add elements to mainPage
        mainPage.getChildren().addAll(mainPageTitle, spacer, playButton, howToPlayButton, exitButton, soundBox);
        StackPane stack = new StackPane(backgroundImageView, mainPage);

        // add mainPage to RootPane
        rootPane.getChildren().add(stack);
    }

    public static void playPage() {
        clear();

        // init background image
        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

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
                    GetDisplay.clickSoundEffect(selectPokemonButton, clickSound, Goto::ListPage);
                }
                vBox.getChildren().add(selectPokemonButton);
                alreadySelected = true;
            }
            hbox.getChildren().add(vBox);
        }
        vbox.getChildren().add(hbox);

        // init Back button
        Button backButton = GetDisplay.initButton("Back", 150, "#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, Goto::mainPage);

        // add elements to playPage
        if (!GameUtils.isFinishChoose()) {
            // init guideline text
            Text guideline = GetDisplay.initText(GameController.getInstance().getPlayerSelectTurn() + " selects pokemon.", 25, true, null);
            vbox.getChildren().addAll(guideline);
        } else {
            //set current pokemon, play turn
            GameController.getInstance().getPlayers().get(0).setCurrentPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0));
            GameController.getInstance().getPlayers().get(1).setCurrentPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0));
            GameUtils.setPokemonInParty();
            // init play button
            Button playButton = GetDisplay.initButton("START", 800, "#ffc900");
            GetDisplay.clickSoundEffect(playButton, clickSound, Goto::battlePage);
            playButton.setOnMouseEntered(e -> playButton.setStyle("-fx-background-radius: 15px; -fx-background-color: #FF9800; -fx-text-fill: white;"));
            playButton.setOnMouseExited(e -> playButton.setStyle("-fx-background-radius: 15px; -fx-background-color: #ffc900; -fx-text-fill: white;"));
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

        // add elements to playPage
        StackPane stack = new StackPane();
        playPage.getChildren().addAll(vbox, backButton);
        stack.getChildren().addAll(backgroundImageView,playPage);

        // add playPage to RootPane
        rootPane.getChildren().addAll(stack);
    }

    public static void howToPlayPage() {
        clear();

        // init background image
        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/pokebg.png");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        // init new VBox and setup
        VBox howToPlayPage = new VBox();
        howToPlayPage.setAlignment(Pos.CENTER);
        howToPlayPage.setPadding(new Insets(60,0,50,0));

        // init page title
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

        Text explanation = new Text("Rules\n" +
            "1. Each player can view the details of each Pokemon and must select 3 different Pokemon.\n" +
            "2. After the players have made their selection, click on \"START\" to begin the game.\n" +
            "3. First, player 1 can choose 1 of 4 actions, which are:\n" +
            "     a. Fight: This includes 4 movesets of the current Pokemon with the PP displayed (the\n" +
            "         amount of times this move can be used), the player can choose 1 of 4 movesets to\n" +
            "         attack the other player's Pokemon.\n" +
            "     b. Item: Each player starts the game with 5 Potion, 2 Revive and 1 Full Restore, the\n" +
            "         player can choose 1 of 3 items to heal the player's Pokemon.\n" +
            "         I.   Potion: heals the selected Pokémon by increasing its HP by 150. However, if a\n" +
            "              Pokémon loses all of its HP, it faints and can no longer be healed with Potion.\n" +
            "         II.  Revive: Revives selected Pokémon that have fainted, restoring half of their\n" +
            "              maximum HP.\n" +
            "         III. Full Restore: Revives selected Pokémon that have fainted and restores their\n" +
            "              maximum HP.\n" +
            "     c. Switch: Switch the current Pokémon for another Pokémon that has not yet fainted.\n" +
            "     d. Surrender: End the game with the victory of the opposite player.\n" +
            "4. After player 1's turn ends, player 2 can choose 1 of 4 actions.\n" +
            "5. If a player chooses to surrender or if the HP of the Pokemon on that player's side has\n" +
            "    been reduced to 0, the game ends and the other player wins the game.");
        explanation.setLineSpacing(15);
        explanation.setWrappingWidth(800);
        explanation.setFont(Font.font(20));
        vBoxIN.getChildren().addAll(explanation);
        scrollPane.setContent(vBoxIN);
        VBox vBoxOUT = new VBox(scrollPane);
        vBoxOUT.setPadding(new Insets(75,0,75,300));

        // init Back to Main Menu button
        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToMainMenu, clickSound, Goto::mainPage);

        StackPane explanationBox = new StackPane(rect, vBoxOUT);
        explanationBox.setAlignment(Pos.CENTER);

        // add elements to howToPlayPage
        howToPlayPage.getChildren().addAll(howToPlayTitle, explanationBox, backToMainMenu);
        StackPane stack = new StackPane(backgroundImageView, howToPlayPage);

        // add howToPlayPage to RootPane
        rootPane.getChildren().addAll(stack);
    }

    public static void ListPage() {
        clear();

        // init background image
        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/skyBackground.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        VBox listPage = new VBox();
        listPage.setAlignment(Pos.CENTER);
        listPage.setSpacing(40);
        listPage.setPadding(new Insets(60,0,60,0));

        Text title = GetDisplay.initText("Pokémon List",50,true,"Verdana");
        title.setStyle("-fx-fill: white;");
        Button backToPlayPage = GetDisplay.initButton("Back", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToPlayPage, clickSound, Goto::playPage);

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
                GetDisplay.clickSoundEffect(pokemonElement, clickSound, () -> detailPage(indexP));
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

    public static void detailPage(int index) {
        clear();

        // init background image
        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/skyBackground.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

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

        Text pokemonHP = GetDisplay.initText(String.valueOf(pokemon.getHp()), 20, false, "Verdana");
        Text pokemonAtk = GetDisplay.initText(String.valueOf(pokemon.getAtk()), 20, false, "Verdana");
        Text pokemonDef = GetDisplay.initText(String.valueOf(pokemon.getDef()), 20, false, "Verdana");
        Text pokemonSpAtk = GetDisplay.initText(String.valueOf(pokemon.getSpAtk()), 20, false, "Verdana");
        Text pokemonSpDef = GetDisplay.initText(String.valueOf(pokemon.getSpDef()), 20, false, "Verdana");
        Text pokemonSpeed = GetDisplay.initText(String.valueOf(pokemon.getSpd()), 20, false, "Verdana");

        VBox statText = new VBox(pokemonType,pHP,pAtk,pDef,pSpAtk,pSpDef,pSpeed);
        statText.setSpacing(20);
        statText.setPadding(new Insets(0,110,0,0));
        VBox pokemonStat = new VBox(pokemonHP,pokemonAtk,pokemonDef,pokemonSpAtk,pokemonSpDef,pokemonSpeed);
        pokemonStat.setSpacing(20);
        pokemonStat.setAlignment(Pos.CENTER_RIGHT);
        pokemonStat.setPadding(new Insets(0,0,0,110));

        HBox stat = new HBox(statText,pokemonStat);
        VBox allDetail = new VBox(pokemonType,stat);
        allDetail.setAlignment(Pos.CENTER_LEFT);
        allDetail.setSpacing(20);
        allDetail.setPadding(new Insets(20,50,20,50));
        allDetail.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10px;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.WHITE);
        dropShadow.setRadius(50);
        allDetail.setEffect(dropShadow);

        Text moveText = GetDisplay.initText("Moveset: ", 20, true, "Verdana");
        Text move1 = GetDisplay.initText(Arrays.stream(PokemonListPane.getInstance().getPokemons().get(index).getMoves()).findFirst().get().getName(), 20, false, "Verdana");
        Text move2 = GetDisplay.initText(Arrays.stream(PokemonListPane.getInstance().getPokemons().get(index).getMoves()).skip(1).findFirst().get().getName(), 20, false, "Verdana");
        Text move3 = GetDisplay.initText(Arrays.stream(PokemonListPane.getInstance().getPokemons().get(index).getMoves()).skip(2).findFirst().get().getName(), 20, false, "Verdana");
        Text move4 = GetDisplay.initText(Arrays.stream(PokemonListPane.getInstance().getPokemons().get(index).getMoves()).skip(3).findFirst().get().getName(), 20, false, "Verdana");

        VBox move13 = new VBox(move1,move3);
        move13.setSpacing(20);
        VBox move24 = new VBox(move2,move4);
        move24.setSpacing(20);
        HBox moveDetail = new HBox(move13,move24);
        moveDetail.setSpacing(100);
        moveDetail.setPadding(new Insets(0,0,0,20));
        VBox moveset = new VBox(moveText,moveDetail);

        moveset.setAlignment(Pos.CENTER_LEFT);
        moveset.setSpacing(20);
        moveset.setPadding(new Insets(20,50,20,50));
        moveset.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10px;");
        DropShadow dropShadowMove = new DropShadow();
        dropShadowMove.setColor(Color.WHITE);
        dropShadowMove.setRadius(50);
        moveset.setEffect(dropShadowMove);

        Button backButton = GetDisplay.initButton("Back", 450, "#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, Goto::ListPage);

        boolean isChoosen = false;
        for (Pokemon pokemonInParty : Objects.requireNonNull(GameController.getInstance().getPlayers().stream().filter(player -> player.getName().equals(GameController.getInstance().getPlayerSelectTurn())).findFirst().orElse(null)).getPokemonsParty()) {
            if (pokemon.equals(pokemonInParty)) {
                isChoosen = true;
                break;
            }
        }
        Button chooseButton = GetDisplay.initButton("Choose", 450, isChoosen? "#333333" : "#386abb");
        if (!isChoosen) {
            GetDisplay.clickSoundEffect(chooseButton, clickSound, () -> {
                GameUtils.choosePokemon(new Pokemon(pokemon));
                playPage();
            });
        }

        HBox button = new HBox(backButton, chooseButton);
        button.setAlignment(Pos.CENTER);
        button.setSpacing(50);

        VBox nameAndImg = new VBox(pokemonName, pokemonImg);
        nameAndImg.setSpacing(30);
        VBox statAndMove = new VBox(allDetail, moveset);
        statAndMove.setSpacing(10);
        HBox pokemonElement = new HBox(nameAndImg,statAndMove);
        pokemonElement.setSpacing(100);

        detailPage.getChildren().addAll(pokemonElement, button);

        StackPane stack = new StackPane(backgroundImageView, detailPage);
        rootPane.getChildren().add(stack);
    }

    public static void battlePage(){
        clear();

        // init background image
        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/battle.jpeg");
        backgroundImageView.setFitHeight(500);
        backgroundImageView.setFitWidth(1400);

        // set pokemon position
        AnchorPane battlePage = new AnchorPane();

        ImageView leftPokemon = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getImgsrc());
        leftPokemon.setFitWidth(300);
        leftPokemon.setFitHeight(300);
        ImageView rightPokemon = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getImgsrc());
        rightPokemon.setFitWidth(300);
        rightPokemon.setFitHeight(300);

        AnchorPane.setLeftAnchor(leftPokemon, 180.0);
        AnchorPane.setBottomAnchor(leftPokemon, 20.0);

        AnchorPane.setRightAnchor(rightPokemon, 180.0);
        AnchorPane.setTopAnchor(rightPokemon, 20.0);

        Text nameLeft = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getName(), 25, true, "Verdana");
        HBox leftNameStatus = new HBox(nameLeft);

        if (!Objects.equals(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getStatus().toString(), "NORM")) {
            Text statusTextL = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getStatus().toString(), 20, true, "Verdana");
            statusTextL.setFill(Color.WHITE);
            Rectangle rect = new Rectangle(80,30);
            rect.setStyle("-fx-fill: " + GetDisplay.getColorOfStatus(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getStatus().toString()) + ";");
            rect.setArcHeight(15);
            rect.setArcWidth(15);
            StackPane statusLeft = new StackPane(rect,statusTextL);
            leftNameStatus.getChildren().add(statusLeft);
            leftNameStatus.setSpacing(20);
        }

        Text hpTextL = GetDisplay.initText("HP", 20, true, "Verdana");
        ProgressBar hpBarLeft = new ProgressBar((double) GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getHp() /GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getMaxHp());
        hpBarLeft.setStyle("-fx-accent: #B4E66B");
        hpBarLeft.setPrefWidth(320);
        Text hpNumL = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getHp() +"/"+ GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getMaxHp(), 20, true, "Verdana");
        HBox leftHP = new HBox(hpTextL, hpBarLeft, hpNumL);
        leftHP.setSpacing(10);
        VBox detailLeftPokemon = new VBox(leftNameStatus, leftHP);
        detailLeftPokemon.setSpacing(10);

        Text nameRight = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getName(), 25, true, "Verdana");
        HBox rightNameStatus = new HBox(nameRight);

        if (!Objects.equals(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getStatus().toString(), "NORM")) {
            Text statusTextR = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getStatus().toString(), 20, true, "Verdana");
            statusTextR.setFill(Color.WHITE);
            Rectangle rect = new Rectangle(80,30);
            rect.setStyle("-fx-fill: " + GetDisplay.getColorOfStatus(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getStatus().toString()) + ";");
            rect.setArcHeight(15);
            rect.setArcWidth(15);
            StackPane statusRight = new StackPane(rect,statusTextR);
            rightNameStatus.getChildren().add(statusRight);
            rightNameStatus.setSpacing(20);
        }

        Text hpTextR = GetDisplay.initText("HP", 20, true, "Verdana");
        ProgressBar hpBarRight = new ProgressBar((double) GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getHp() /GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getMaxHp());
        hpBarRight.setStyle("-fx-accent: #B4E66B");
        hpBarRight.setPrefWidth(320);
        Text hpNumR = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getHp() +"/"+ GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getCurrentPokemon().getMaxHp(), 20, true, "Verdana");
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

    public static void actionPage() {
        bottomBattle.getChildren().clear();

        AnchorPane actionPage = new AnchorPane();

        //make button
        Button fight = GetDisplay.initButton("Fight",400,"#ff1f1f");
        GetDisplay.clickSoundEffect(fight, clickSound, Goto::fightPage);
        Button item = GetDisplay.initButton("Item",400,"#363b81");
        GetDisplay.clickSoundEffect(item, clickSound, Goto::itemPage);
        Button switchB = GetDisplay.initButton("Switch",400,"#5db9ff");
        GetDisplay.clickSoundEffect(switchB, clickSound, () -> {
            switchPage(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()));
        });
        Button surrender = GetDisplay.initButton("Surrender",400,"#fbd743");
        GetDisplay.clickSoundEffect(surrender, clickSound, () -> {
            GameController.getInstance().setGameEnded(true);
            GameController.getInstance().setWinner(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()).getName());
            winnerPage();
        });

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

    public static void fightPage() {
        bottomBattle.getChildren().clear();

        AnchorPane fightPage = new AnchorPane();

        // init HBox
        HBox hBox1 = new HBox();
        hBox1.setSpacing(100);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox();
        hBox2.setSpacing(100);
        hBox2.setAlignment(Pos.CENTER);

        // init skill button
        for (int i = 0; i < 4; i++) {
            BaseSkill baseSkill = Arrays.stream(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getCurrentPokemon().getMoves()).skip(i).findFirst().get();
            Button skill = GetDisplay.initButton(baseSkill.getName() + " PP:" + baseSkill.getPp(),400, baseSkill.getPp() == 0? "#333333" : "#ff1f1f");
            int finalI = i;
            if (baseSkill.getPp() > 0) {
                GetDisplay.clickSoundEffect(skill, clickSound, () -> {
                    GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).setAction("f" + (finalI+1));
                    GameUtils.switchPlayerPlay();
                    battlePage();
                    if (GameController.getInstance().getIndexPlayerPlayTurn() == 1) {
                        actionPage();
                    } else {
                        GameUtils.startTurn(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()),GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()));
                    }
                });
            }
            if (i < 2) hBox1.getChildren().add(skill);
            else hBox2.getChildren().add(skill);
        }

        // init VBox
        VBox vBox = new VBox(hBox1, hBox2);
        vBox.setPrefWidth(900);
        vBox.setPrefHeight(287.5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 170.0);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        fightPage.getChildren().addAll(backButton, vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(fightPage);
    }

    public static void itemPage() {
        bottomBattle.getChildren().clear();

        AnchorPane itemPage = new AnchorPane();

        // init item button
        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setPrefWidth(900);
        vBox.setPrefHeight(287.5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        int items = 0;
        boolean addHboxInVbox = false;
        for (int i = 0; i < 3; i++) {
            if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getItems().get(i).getAmount() > 0) {
                items++;
                String text = GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getItems().get(i).getName() + " x" + GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getItems().get(i).getAmount();
                Button item = GetDisplay.initButton(text,450,"#363b81");
                int finalI = i;
                GetDisplay.clickSoundEffect(item, clickSound, () -> {
                    GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).setAction("i" + (finalI +1));
                    battlePage();
                    pokemonUseItemWithPage();
                });

                if (items == 1) hBox.getChildren().add(item);
                else if (items == 2) hBox.getChildren().add(item);
                else vBox.getChildren().add(item);
            }
            if (items == 2 && !addHboxInVbox) {
                addHboxInVbox = true;
                vBox.getChildren().add(hBox);
            }
        }
        if (!addHboxInVbox) vBox.getChildren().add(hBox);

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 170.0);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        itemPage.getChildren().addAll(backButton, vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(itemPage);
    }

    public static void pokemonUseItemWithPage() {
        bottomBattle.getChildren().clear();

        AnchorPane pokemonUseItemWithPage = new AnchorPane();

        // init HBox
        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setPrefWidth(900);
        vBox.setPrefHeight(287.5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(50);
        vBox.setPadding(new Insets(0,0,220,0));

        // init button to select pokemon
        for (int i = 0; i < 3; i++) {
            int finalI = i;

            Rectangle rect = new Rectangle(400, 75);
            rect.setArcHeight(25);
            rect.setArcWidth(25);
            if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i1") || GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i3")) {
                if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getHp() > 0) {
                    rect.setStyle("-fx-fill: #5db9ff;");
                } else {
                    rect.setStyle("-fx-fill: #333333;");
                }
            } else if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i2")) {
                if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getHp() > 0) {
                    rect.setStyle("-fx-fill: #333333;");
                } else {
                    rect.setStyle("-fx-fill: #5db9ff;");
                }
            }

            ImageView imageView = GetDisplay.displayImg(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getImgsrc());
            imageView.setFitWidth(60);
            imageView.setFitHeight(60);

            Text text = GetDisplay.initText(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getName(), 35, true, "Verdana");
            text.setFill(Color.WHITE);

            HBox hBox1 = new HBox(imageView, text);
            hBox1.setAlignment(Pos.CENTER);
            hBox1.setSpacing(10);

            StackPane pokemon = new StackPane(rect, hBox1);
            if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i1") || GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i3")) {
                if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getHp() > 0) {
                    GetDisplay.clickSoundEffect(pokemon, clickSound, () -> {
                        GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).setPokemonUseItemWith(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(finalI));
                        GameUtils.switchPlayerPlay();
                        battlePage();
                        if (GameController.getInstance().getIndexPlayerPlayTurn() == 1) {
                            actionPage();
                        } else {
                            GameUtils.startTurn(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()),GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()));
                        }
                    });
                }
            } else if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getAction().equals("i2")) {
                if (GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(i).getHp() <= 0) {
                    GetDisplay.clickSoundEffect(pokemon, clickSound, () -> {
                        GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).setPokemonUseItemWith(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()).getPokemonsParty().get(finalI));
                        GameUtils.switchPlayerPlay();
                        battlePage();
                        if (GameController.getInstance().getIndexPlayerPlayTurn() == 1) {
                            actionPage();
                        } else {
                            GameUtils.startTurn(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()),GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()));
                        }
                    });
                }
            }

            if (i < 2) hBox.getChildren().add(pokemon);
            else vBox.getChildren().add(pokemon);

            if (i == 1) vBox.getChildren().add(hBox);
        }

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        GetDisplay.clickSoundEffect(backButton, clickSound, Goto::itemPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 170.0);

        Button playerTurn = GetDisplay.initButton(GameController.getInstance().getPlayerPlayTurn(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -85.0);

        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setRightAnchor(vBox, 250.0);

        pokemonUseItemWithPage.getChildren().addAll(backButton, vBox, playerTurn);

        //add all to children
        bottomBattle.getChildren().add(pokemonUseItemWithPage);
    }

    public static void switchPage(Player player) {
        bottomBattle.getChildren().clear();

        AnchorPane switchPage = new AnchorPane();

        Rectangle rect1 = new Rectangle(400, 75);
        if (player.getSecondPokemon().getHp() > 0) {
            rect1.setStyle("-fx-fill: #5db9ff;");
        } else {
            rect1.setStyle("-fx-fill: #333333;");
        }
        rect1.setArcHeight(25);
        rect1.setArcWidth(25);

        ImageView imageView1 = GetDisplay.displayImg(player.getSecondPokemon().getImgsrc());
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(60);

        Text text1 = GetDisplay.initText(player.getSecondPokemon().getName(), 35, true, "Verdana");
        text1.setFill(Color.WHITE);

        HBox hBox1 = new HBox(imageView1, text1);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(10);

        StackPane pokemon1 = new StackPane(rect1, hBox1);
        if (player.getSecondPokemon().getHp() > 0) {
            GetDisplay.clickSoundEffect(pokemon1, clickSound, () -> {
                player.setAction("s1");
                if (GameController.getInstance().isFainted()) {
                    GameUtils.startAction(player, null);
                } else {
                    GameUtils.switchPlayerPlay();
                    battlePage();
                    if (GameController.getInstance().getIndexPlayerPlayTurn() == 1) {
                        actionPage();
                    } else {
                        GameUtils.startTurn(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()),GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()));
                    }
                }
            });
        }

        Rectangle rect2 = new Rectangle(400, 75);
        if (player.getThirdPokemon().getHp() > 0) {
            rect2.setStyle("-fx-fill: #5db9ff;");
        } else {
            rect2.setStyle("-fx-fill: #333333;");
        }
        rect2.setArcHeight(25);
        rect2.setArcWidth(25);

        ImageView imageView2 = GetDisplay.displayImg(player.getThirdPokemon().getImgsrc());
        imageView2.setFitWidth(60);
        imageView2.setFitHeight(60);

        Text text2 = GetDisplay.initText(player.getThirdPokemon().getName(), 35, true, "Verdana");
        text2.setFill(Color.WHITE);

        HBox hBox2 = new HBox(imageView2, text2);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(10);

        StackPane pokemon2 = new StackPane(rect2, hBox2);
        if (player.getThirdPokemon().getHp() > 0) {
            GetDisplay.clickSoundEffect(pokemon2, clickSound, () -> {
                player.setAction("s2");
                if (GameController.getInstance().isFainted()) {
                    GameUtils.startAction(player, null);
                } else {
                    GameUtils.switchPlayerPlay();
                    battlePage();
                    if (GameController.getInstance().getIndexPlayerPlayTurn() == 1) {
                        actionPage();
                    } else {
                        GameUtils.startTurn(GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexPlayerPlayTurn()),GameController.getInstance().getPlayers().get(GameController.getInstance().getIndexRivalPlayTurn()));
                    }
                }
            });
        }

        Button backButton = GetDisplay.initButton("⮐",150,"#386abb");
        if (!GameController.getInstance().isFainted()) GetDisplay.clickSoundEffect(backButton, clickSound, Goto::actionPage);
        AnchorPane.setLeftAnchor(backButton, -10.0);
        AnchorPane.setBottomAnchor(backButton, 117.5);

        Button playerTurn = GetDisplay.initButton(player.getName(),200,"#386abb");
        AnchorPane.setRightAnchor(playerTurn, -10.0);
        AnchorPane.setTopAnchor(playerTurn, -138.5);

        // init HBox
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

    public static void dialogPage() {
        bottomBattle.getChildren().clear();

        // init VBox
        VBox dialog = new VBox();
        dialog.setSpacing(10);
        AnchorPane.setLeftAnchor(dialog, 150.0);
        AnchorPane.setTopAnchor(dialog, -65.0);

        for (String action : GameController.getInstance().getActions()) {
            Text text = GetDisplay.initText(action, 25, false, "Verdana");
            dialog.getChildren().add(text);
        }

        // next button
        Button next = GetDisplay.initButton("⮕",100,"#386abb");
        GetDisplay.clickSoundEffect(next, clickSound, () -> {
            if (GameController.getInstance().isGameEnded()) {
                winnerPage();
            } else {
                GameController.getInstance().setActions(new ArrayList<>());
                if (GameUtils.getFirst().getAction() != null && GameUtils.getFirst().getAction().equals("fainted")) switchPage(GameUtils.getFirst());
                else if (GameUtils.getLast().getAction() != null && !GameUtils.getLast().getAction().equals("fainted")) GameUtils.startAction(GameUtils.getLast(), GameUtils.getFirst());
                else if (GameUtils.getFirst().getAction() != null || GameUtils.getLast().getAction() != null) {
                    if (GameUtils.getFirst().getAction() != null && GameUtils.getFirst().getAction().equals("fainted")) switchPage(GameUtils.getFirst());
                    else switchPage(GameUtils.getLast());
                }
                else actionPage();
            }
        });
        AnchorPane.setRightAnchor(next, -10.0);
        AnchorPane.setBottomAnchor(next, 110.0);

        AnchorPane dialogPage = new AnchorPane(dialog,next);
        dialogPage.setPrefHeight(287.5);

        //add all to children
        bottomBattle.getChildren().addAll(dialogPage);
    }

    public static void winnerPage(){
        clear();

        ImageView backgroundImageView = GetDisplay.displayImg("titleAndBackground/winnerBG.jpg");
        backgroundImageView.setFitHeight(787.5);
        backgroundImageView.setFitWidth(1400);

        Text winner = GetDisplay.initText("WINNER", 80, true, "Verdana");
        winner.setFill(Color.WHITE);

        Rectangle rect = new Rectangle(800,300);
        rect.setArcWidth(25);
        rect.setArcHeight(25);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(5);
        rect.setFill(Color.web("#386abb"));

        Text winnerPlayer = GetDisplay.initText(GameController.getInstance().getWinner(), 80, true, "Verdana");
        winnerPlayer.setFill(Color.web("#ffc900"));

        StackPane winnerBlock = new StackPane(rect, winnerPlayer);

        Button backToMainMenu = GetDisplay.initButton("Back to Main Menu", 450, "#386abb");
        GetDisplay.clickSoundEffect(backToMainMenu, clickSound, Goto::mainPage);

        VBox vBox = new VBox(winner, winnerBlock, backToMainMenu);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(80);
        StackPane stack = new StackPane(backgroundImageView, vBox);
        rootPane.getChildren().add(stack);
    }
}