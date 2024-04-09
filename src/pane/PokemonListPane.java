package pane;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pokemon.Pokemon;
import usage.Type;

import java.util.ArrayList;

public class PokemonListPane extends VBox {
    private static PokemonListPane instance;
    private ArrayList<Pokemon> pokemons;
    private PokemonListPane() {
        pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Bulbasaur", Type.GRASS,Type.POISON,45,49,49,65,65,45, "pokemon/001.png"));
        pokemons.add(new Pokemon("Charmander",Type.FIRE,Type.NULL,39,52,43,60,50,65, "pokemon/002.png"));


        setFillWidth(false);
        setAlignment(Pos.CENTER);
    }

    public static PokemonListPane getInstance() {
        if (instance == null) {
            instance = new PokemonListPane();
        }
        return instance;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

}
