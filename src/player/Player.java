package player;

import item.Item;
import pokemon.Pokemon;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Pokemon> pokemonsParty;
    private ArrayList<Item> items;
    private Pokemon currentPokemon;

    public Player(String name, ArrayList<Pokemon> pokemonsParty, Pokemon currentPokemon) {
        setName(name);
        setPokemonsParty(pokemonsParty);
        setCurrentPokemon(currentPokemon);
        initItems();
    }

    private void initItems() {
        items = new ArrayList<Item>();
    }

    public void changeCurrentPokemon(Pokemon newPokemon) {
        this.currentPokemon = newPokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Pokemon> getPokemonsParty() {
        return pokemonsParty;
    }

    public void setPokemonsParty(ArrayList<Pokemon> pokemonsParty) {
        this.pokemonsParty = pokemonsParty;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Pokemon getCurrentPokemon() {
        return currentPokemon;
    }

    public void setCurrentPokemon(Pokemon currentPokemon) {
        this.currentPokemon = currentPokemon;
    }
}
