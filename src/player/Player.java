package player;

import item.FullRestore;
import item.Item;
import item.Potion;
import item.Revive;
import pokemon.Pokemon;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Pokemon> pokemonsParty;
    private ArrayList<Item> items;
    private Pokemon currentPokemon;

    public Player(String name, ArrayList<Pokemon> pokemonsParty) {
        setName(name);
        setPokemonsParty(pokemonsParty);
        setCurrentPokemon(null);
        initItems();
    }

    private void initItems() {
        items = new ArrayList<Item>();
        items.add(new Potion(100, 5));
        items.add(new Revive(50,2));
        items.add(new FullRestore(1));
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
