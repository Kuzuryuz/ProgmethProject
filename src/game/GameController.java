package game;

import player.Player;
import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private static final Scanner sc = new Scanner(System.in);
    private static GameController instance;
    private ArrayList<Player> players;

    public GameController() {
        players = new ArrayList<>();
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public void initGame() {
        System.out.println("Enter Player 1 Name:");
        String name1 = sc.nextLine();

        System.out.println("Select Pokemon:");
        System.out.println("0. Charmander");
        System.out.println("1. Bulbasaur");
        System.out.println("2. Squirtle");
        String index1 = sc.nextLine();

        ArrayList<Pokemon> pokemonArrayList1 = new ArrayList<>();
        switch (index1) {
            case "0" -> {
                pokemonArrayList1.add(new Pokemon("Charmander", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
            case "1" -> {
                pokemonArrayList1.add(new Pokemon("Bulbasaur", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
            case "2" -> {
                pokemonArrayList1.add(new Pokemon("Squirtle", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
        }

        players.add(new Player(name1, pokemonArrayList1, pokemonArrayList1.get(0)));

        System.out.println("Enter Player 2 Name:");
        String name2 = sc.nextLine();

        System.out.println("Select Pokemon:");
        System.out.println("0. Charmander");
        System.out.println("1. Bulbasaur");
        System.out.println("2. Squirtle");
        String index2 = sc.nextLine();

        ArrayList<Pokemon> pokemonArrayList2 = new ArrayList<>();
        switch (index2) {
            case "0" -> {
                pokemonArrayList2.add(new Pokemon("Charmander", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
            case "1" -> {
                pokemonArrayList2.add(new Pokemon("Bulbasaur", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
            case "2" -> {
                pokemonArrayList2.add(new Pokemon("Squirtle", "Fire", null, 20, 10, 5,10,100,5, ""));
            }
        }

        players.add(new Player(name2, pokemonArrayList2, pokemonArrayList2.get(0)));

        for (Player player : players) {
            System.out.println(player.getName() + " have current Pokemon as " + player.getCurrentPokemon().getName());
        }
    }
}
