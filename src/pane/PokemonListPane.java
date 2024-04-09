package pane;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pokemon.Pokemon;
import usage.Type;

import java.util.ArrayList;

public class PokemonListPane {
    private static PokemonListPane instance;
    private ArrayList<Pokemon> pokemons;
    private PokemonListPane() {
        pokemons = new ArrayList<>();
//        pokemons.add(new Pokemon("Bulbasaur", Type.GRASS,Type.POISON,45,49,49,65,65,45, "pokemon/001.png"));
//        pokemons.add(new Pokemon("Charmander",Type.FIRE,Type.NULL,39,52,43,60,50,65, "pokemon/002.png"));
        pokemons.add(new Pokemon("Mimikyu",Type.GHOST,Type.FAIRY,55,90,80,50,105,96,"pokemon/mimikyu.png"));
        pokemons.add(new Pokemon("Delphox",Type.FIRE,Type.PSYCHIC,75,69,72,114,100,104,"pokemon/delphox.png"));
        pokemons.add(new Pokemon("Leafeon",Type.GRASS,Type.NULL,65,110,130,60,65,95,"pokemon/leafeon.png"));
        pokemons.add(new Pokemon("Umbreon",Type.DARK,Type.NULL,95,65,110,60,130,65,"pokemon/umbreon.png"));
        pokemons.add(new Pokemon("Snorlax",Type.NORMAL,Type.NULL,160,110,65,65,110,30,"pokemon/snorlax.png"));
        pokemons.add(new Pokemon("Lucario",Type.FIGHTING,Type.STEEL,70,110,70,115,70,90, "pokemon/lucario.png"));
        pokemons.add(new Pokemon("Gardevoir",Type.FAIRY,Type.PSYCHIC,68,65,65,125,115,80, "pokemon/gardevoir.png"));
        pokemons.add(new Pokemon("Dragonite",Type.DRAGON,Type.FLYING,91,134,95,100,100,80, "pokemon/dragonite.png"));
        pokemons.add(new Pokemon("Sceptile",Type.GRASS,Type.NULL,70,85,65,105,85,120, "pokemon/sceptile.png"));
        pokemons.add(new Pokemon("Greninja",Type.WATER,Type.DARK,72,95,67,103,71,122, "pokemon/greninja.png"));
        pokemons.add(new Pokemon("Scolipede",Type.BUG,Type.POISON,60,100,89,55,69,112, "pokemon/scolipede.png"));
        pokemons.add(new Pokemon("Baxcalibur",Type.DRAGON,Type.ICE,115,145,92,75,86,87, "pokemon/baxcalibur.png"));
        pokemons.add(new Pokemon("Magcargo",Type.FIRE,Type.ROCK,60,50,120,90,80,30, "pokemon/magcargo.png"));
        pokemons.add(new Pokemon("Wo-Chien",Type.DARK,Type.GRASS,85,85,100,95,135,70, "pokemon/wo-chien.png"));
        pokemons.add(new Pokemon("Iron Thorns",Type.ROCK,Type.ELECTRIC,100,134,110,70,84,72, "pokemon/ironthorns.png"));
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
