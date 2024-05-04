package game;

import player.Player;

import java.util.ArrayList;

public class GameController {
    private static GameController instance;
    private ArrayList<Player> players;
    private String playerSelectTurn;
    private String playerPlayTurn;
    private int indexPlayerPlayTurn;
    private int indexRivalPlayTurn;
    private ArrayList<String> actions;
    private boolean isFainted;
    private boolean isGameEnded;
    private String winner;

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
        setPlayerSelectTurn("Player 1");
        setPlayerPlayTurn("Player 1");
        setIndexPlayerPlayTurn(0);
        setIndexRivalPlayTurn(1);
        setActions(new ArrayList<>());
        setFainted(false);
        setGameEnded(false);
    }

    public void newGame() {
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

    public String getPlayerPlayTurn() {
        return playerPlayTurn;
    }

    public void setPlayerPlayTurn(String playerPlayTurn) {
        this.playerPlayTurn = playerPlayTurn;
    }

    public int getIndexPlayerPlayTurn() {
        return indexPlayerPlayTurn;
    }

    public void setIndexPlayerPlayTurn(int indexPlayerPlayTurn) {
        this.indexPlayerPlayTurn = indexPlayerPlayTurn;
    }

    public int getIndexRivalPlayTurn() {
        return indexRivalPlayTurn;
    }

    public void setIndexRivalPlayTurn(int indexRivalPlayTurn) {
        this.indexRivalPlayTurn = indexRivalPlayTurn;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public boolean isGameEnded() {
        return isGameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        isGameEnded = gameEnded;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
