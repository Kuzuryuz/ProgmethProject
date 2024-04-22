package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import player.Player;
import pokemon.Pokemon;

import java.util.ArrayList;

public class GameUtils {
    public static void choosePokemon(Pokemon pokemon) {
        switch (GameController.getInstance().getPlayerSelectTurn()) {
            case "Player 1" -> {
                ArrayList<Pokemon> pokemons = new ArrayList<>(GameController.getInstance().getPlayers().get(0).getPokemonsParty());
                pokemons.add(pokemon);
                GameController.getInstance().getPlayers().get(0).setPokemonsParty(pokemons);

                GameController.getInstance().setPlayerSelectTurn("Player 2");
            }
            case "Player 2" -> {
                ArrayList<Pokemon> pokemons = new ArrayList<>(GameController.getInstance().getPlayers().get(1).getPokemonsParty());
                pokemons.add(pokemon);
                GameController.getInstance().getPlayers().get(1).setPokemonsParty(pokemons);

                GameController.getInstance().setPlayerSelectTurn("Player 1");
            }
        }
    }

    public static boolean isFinishChoose() {
        if(GameController.getInstance().getPlayers().get(0).getPokemonsParty().size()==3 && GameController.getInstance().getPlayers().get(1).getPokemonsParty().size()==3) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void switchPlayerPlay() {
        switch (GameController.getInstance().getIndexPlayerPlayTurn()) {
            case 0 -> {
                GameController.getInstance().setPlayerPlayTurn("Player 2");
                GameController.getInstance().setIndexPlayerPlayTurn(1);
            }
            case 1 -> {
                GameController.getInstance().setPlayerPlayTurn("Player 1");
                GameController.getInstance().setIndexPlayerPlayTurn(0);
            }
        }
    }

}
