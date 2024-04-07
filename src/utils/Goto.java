package utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pane.RootPane;

public class Goto {
    private static RootPane rootPane;
    private static Stage stage;

    public static void setRootPane(RootPane rootPane) {

        Goto.rootPane = rootPane;
    }
    public static void setStage(Stage stage) { Goto.stage = stage; }

    private static void clear() {
        rootPane.getChildren().clear();
    }

    public static void mainPage() {
        clear();

        // init new VBox and setup
        VBox mainPage = new VBox();
        mainPage.setAlignment(Pos.CENTER);
        mainPage.setPadding(new Insets(150,0,0,0));
        mainPage.setSpacing(25);

        // init game title
        Text title = GetDisplay.initText("PokeBattle!", 70, true, "Verdana");

        // init Region for spacing
        Region spacer = new Region();
        spacer.setPrefHeight(80);

        // init Play button
        Button playButton = GetDisplay.initButton("Play", 350, "#868BFF");
        playButton.setOnAction(e -> playPage());

        // init How to Play button
        Button howToPlayButton = GetDisplay.initButton("How to Play", 350, "#868BFF");
        howToPlayButton.setOnAction(e -> howToPlayPage());

        // init Exit button
        Button exitButton = GetDisplay.initButton("Exit", 350, "#868BFF");
        exitButton.setOnAction(e -> {
            stage.close();
            Platform.exit();
        });

        // add elements to mainPage
        mainPage.getChildren().addAll(title, spacer, playButton, howToPlayButton, exitButton);

        // add mainPage to RootPane
        rootPane.getChildren().add(mainPage);
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
        Button backButton = GetDisplay.initButton("Back", 150, "#868BFF");
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
