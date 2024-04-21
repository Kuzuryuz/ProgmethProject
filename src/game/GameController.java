package game;

import player.Player;

import java.util.ArrayList;

public class GameController {
    public static GameController instance;
    private ArrayList<Player> players;
    private String playerSelectTurn;


    private GameController() {
        players = new ArrayList<>();
        players.add(new Player("Player 1", new ArrayList<>()));
        players.add(new Player("Player 2", new ArrayList<>()));

        startGame();
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private void startGame() {
        playerSelectTurn = "Player 1";
    }

    public void endGame() {
        instance = new GameController();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getPlayerSelectTurn() {
        return playerSelectTurn;
    }

    public void setPlayerSelectTurn(String playerSelectTurn) {
        this.playerSelectTurn = playerSelectTurn;
    }


}
