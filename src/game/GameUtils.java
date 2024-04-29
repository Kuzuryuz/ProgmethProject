package game;

import item.FullRestore;
import item.Item;
import item.Potion;
import item.Revive;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import player.Player;
import pokemon.Pokemon;
import utils.Goto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
        switch (GameController.getInstance().getIndexPlayerPlayTurn()) {
            case 0 -> {
                GameController.getInstance().setPlayerPlayTurn("Player 2");
                GameController.getInstance().setIndexPlayerPlayTurn(1);
                GameController.getInstance().setIndexRivalPlayTurn(0);
            }
            case 1 -> {
                GameController.getInstance().setPlayerPlayTurn("Player 1");
                GameController.getInstance().setIndexPlayerPlayTurn(0);
                GameController.getInstance().setIndexRivalPlayTurn(1);
            }
        }
    }
    public static void startAction(Player first,Player opponent) {
        //เอาไว้ใช้action
        switch (first.getAction()) {
            case "f1" -> {
                Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
            }case  "f2" -> {
                Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
            }case "f3" -> {
                Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
            }case "f4" -> {
                Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
            }case "i1" -> {
                Item item = first.getItems().get(0);
                if (item instanceof Potion) {
                    ((Potion) item).useHeal(first.getCurrentPokemon());
                } else {
                    System.out.println("This item is not a potion.");
                }
            }case "i2" -> {
                Item item = first.getItems().get(1);
                if (item instanceof Revive) {
                    ((Revive) item).useHeal(first.getCurrentPokemon());
                } else {
                    System.out.println("This item is not a Revive.");
                }
            }case "i3" -> {
                Item item = first.getItems().get(2);
                if (item instanceof FullRestore) {
                    ((FullRestore) item).useHeal(first.getCurrentPokemon());
                } else {
                    System.out.println("This item is not a FullResetore.");
                }
            }case "s1" -> {
                first.setCurrentPokemon(first.getSecondPokemon());
                setpokemoninparty();
            }case "s2" -> {
                first.setCurrentPokemon(first.getThirdPokemon());
                setpokemoninparty();
            }default -> {
                System.out.println("Invalid action: " + first.getAction());
            }
        }
    }

    public static void startTurn(Player p1,Player p2) {
        //ไว้เริ่มใช้งานฟังก์ชั่นต่างๆ เพื่อไปเรียกใช้ whofirst,startaction อีกที
        if(isFinishChooseAction()) {
            Player first = whoFirst(p1,p2);
            Player last;
            if(first == p1) {
                last = p2;
            }else {
                last = p1;
            }
            Goto.DialogPage();
            //เริ่มactionของตัวแรกที่ได้ตีก่อน
            startAction(first,last);
            //เริ่มactionของตัวสอง
            startAction(last,first);
            first.setAction(null);
            last.setAction(null);

        }
    }
    public static Player whoFirst(Player p1,Player p2) {
        //เช้คว่าใครเริ่มก่อน
        if(Objects.equals(p1.getAction(), "s1") || Objects.equals(p1.getAction(), "s2")) {
            return p1;
        } else if (Objects.equals(p1.getAction(), "i1") || Objects.equals(p1.getAction(), "i2") || Objects.equals(p1.getAction(), "i3")) {
            return p1;
        } else if (Objects.equals(p1.getAction(), "f1") || Objects.equals(p1.getAction(), "f2") || Objects.equals(p1.getAction(), "f3") || Objects.equals(p1.getAction(), "f4")) {
            if(Objects.equals(p2.getAction(), "f1") || Objects.equals(p2.getAction(), "f2") || Objects.equals(p2.getAction(), "f3") || Objects.equals(p2.getAction(), "f4")) {
                if(p1.getCurrentPokemon().getSpd() >= p2.getCurrentPokemon().getSpd()) {
                    return p1;
                }
            }
        } else {
            return p2;
        }
        return p2;
    }

    public static boolean isFinishChooseAction() {
        //เช้คว่าได้กดaction ทั้งคู่ยัง
        return (GameController.getInstance().getPlayers().get(0).getAction() != null && GameController.getInstance().getPlayers().get(1).getAction() != null);
    }
    public static void setpokemoninparty() {
        //set ว่าตัวที่2,3เป็นตัวอะไร ของplayer1
        if(GameController.getInstance().getPlayers().get(0).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0))) {
            GameController.getInstance().getPlayers().get(0).setSecondPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(1));
            GameController.getInstance().getPlayers().get(0).setThirdPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(2));
        } else if (GameController.getInstance().getPlayers().get(0).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(1))) {
            GameController.getInstance().getPlayers().get(0).setSecondPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0));
            GameController.getInstance().getPlayers().get(0).setThirdPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(2));
        } else if (GameController.getInstance().getPlayers().get(0).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(2))) {
            GameController.getInstance().getPlayers().get(0).setSecondPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(0));
            GameController.getInstance().getPlayers().get(0).setThirdPokemon(GameController.getInstance().getPlayers().get(0).getPokemonsParty().get(1));
        }
        //set ว่าตัวที่2,3เป็นตัวอะไร ของplayer2
        if(GameController.getInstance().getPlayers().get(1).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0))) {
            GameController.getInstance().getPlayers().get(1).setSecondPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(1));
            GameController.getInstance().getPlayers().get(1).setThirdPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(2));
        } else if (GameController.getInstance().getPlayers().get(1).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(1))) {
            GameController.getInstance().getPlayers().get(1).setSecondPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0));
            GameController.getInstance().getPlayers().get(1).setThirdPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(2));
        } else if (GameController.getInstance().getPlayers().get(1).getCurrentPokemon().equals(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(2))) {
            GameController.getInstance().getPlayers().get(1).setSecondPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(0));
            GameController.getInstance().getPlayers().get(1).setThirdPokemon(GameController.getInstance().getPlayers().get(1).getPokemonsParty().get(1));
        }
    }
}
