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
        return GameController.getInstance().getPlayers().get(0).getPokemonsParty().size() == 3 && GameController.getInstance().getPlayers().get(1).getPokemonsParty().size() == 3;
    }

    public static void switchPlayerPlay() {
        switch (GameController.getInstance().getPlayerPlayTurn()) {
            case "Player 1" -> {
                GameController.getInstance().setPlayerPlayTurn("Player 2");
            }
            case "Player 2" -> {
                GameController.getInstance().setPlayerPlayTurn("Player 1");
            }
        }
    }

}
