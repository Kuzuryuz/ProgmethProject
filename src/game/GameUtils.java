package game;

import item.FullRestore;
import item.Item;
import item.Potion;
import item.Revive;
import player.Player;
import pokemon.Pokemon;
import utils.Goto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameUtils {
    private static Player first, last;

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

    public static void startAction(Player first, Player opponent) {
        //เอาไว้ใช้action
        ArrayList<String> action = GameController.getInstance().getActions();
        if (!GameController.getInstance().isFainted()) first.getCurrentPokemon().checkStatus();
        if (first.getCurrentPokemon().getHp() == 0 && !GameController.getInstance().isFainted()) {
            action = GameController.getInstance().getActions();
            action.add(first.getCurrentPokemon().getName() + " fainted!");
            GameController.getInstance().setActions(action);
            first.setAction("fainted");
            GameController.getInstance().setFainted(true);

            checkIsAllDead(first, opponent);

            Goto.battlePage();
            Goto.dialogPage();
        } else {
            switch (first.getAction()) {
                case "f1" -> {
                    if (!first.getCurrentPokemon().checkFrozen()) {
                        action.add(first.getCurrentPokemon().getName() + " used " + Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getName() + "!");
                        GameController.getInstance().setActions(action);

                        Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
                        first.getCurrentPokemon().setHp(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserHp());
                        first.getCurrentPokemon().setSpd(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserSpe());
                        first.getCurrentPokemon().setSpDef(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserSpd());
                        first.getCurrentPokemon().setSpAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserSpa());
                        first.getCurrentPokemon().setDef(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserDef());
                        first.getCurrentPokemon().setAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).findFirst().get().getUserAtk());

                        checkIsPokemonDead(opponent);
                        checkIsAllDead(opponent, first);
                    }
                }
                case "f2" -> {
                    if (!first.getCurrentPokemon().checkFrozen()) {
                        action.add(first.getCurrentPokemon().getName() + " used " + Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getName() + "!");
                        GameController.getInstance().setActions(action);

                        Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
                        first.getCurrentPokemon().setHp(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserHp());
                        first.getCurrentPokemon().setSpd(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserSpe());
                        first.getCurrentPokemon().setSpDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserSpd());
                        first.getCurrentPokemon().setSpAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserSpa());
                        first.getCurrentPokemon().setDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserDef());
                        first.getCurrentPokemon().setAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(1).findFirst().get().getUserAtk());

                        checkIsPokemonDead(opponent);
                        checkIsAllDead(opponent, first);
                    }
                }
                case "f3" -> {
                    if (!first.getCurrentPokemon().checkFrozen()) {
                        action.add(first.getCurrentPokemon().getName() + " used " + Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getName() + "!");
                        GameController.getInstance().setActions(action);

                        Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
                        first.getCurrentPokemon().setHp(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserHp());
                        first.getCurrentPokemon().setSpd(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserSpe());
                        first.getCurrentPokemon().setSpDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserSpd());
                        first.getCurrentPokemon().setSpAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserSpa());
                        first.getCurrentPokemon().setDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserDef());
                        first.getCurrentPokemon().setAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(2).findFirst().get().getUserAtk());

                        checkIsPokemonDead(opponent);
                        checkIsAllDead(opponent, first);
                    }
                }
                case "f4" -> {
                    if (!first.getCurrentPokemon().checkFrozen()) {
                        action.add(first.getCurrentPokemon().getName() + " used " + Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getName() + "!");
                        GameController.getInstance().setActions(action);

                        Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().useSkill(opponent.getCurrentPokemon(),first.getCurrentPokemon());
                        first.getCurrentPokemon().setHp(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserHp());
                        first.getCurrentPokemon().setSpd(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserSpe());
                        first.getCurrentPokemon().setSpDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserSpd());
                        first.getCurrentPokemon().setSpAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserSpa());
                        first.getCurrentPokemon().setDef(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserDef());
                        first.getCurrentPokemon().setAtk(Arrays.stream(first.getCurrentPokemon().getMoves()).skip(3).findFirst().get().getUserAtk());

                        checkIsPokemonDead(opponent);
                        checkIsAllDead(opponent, first);
                    }
                }
                case "i1" -> {
                    Item item = first.getItems().get(0);
                    if (item instanceof Potion) {
                        ((Potion) item).useHeal(first.getPokemonUseItemWith());
                        item.setAmount(item.getAmount() - 1);

                        action.add(first.getName() + " used a Potion with " + first.getPokemonUseItemWith().getName() + ".");
                        action.add(first.getPokemonUseItemWith().getName() + "'s HP was restored.");
                        GameController.getInstance().setActions(action);
                    }
                }
                case "i2" -> {
                    Item item = first.getItems().get(1);
                    if (item instanceof Revive) {
                        ((Revive) item).useCure(first.getPokemonUseItemWith());
                        ((Revive) item).useRevive(first.getPokemonUseItemWith());
                        item.setAmount(item.getAmount() - 1);

                        action.add(first.getName() + " used a Revive with " + first.getPokemonUseItemWith().getName() + ".");
                        action.add(first.getPokemonUseItemWith().getName() + " recovered from fainting!");
                        GameController.getInstance().setActions(action);
                    }
                }
                case "i3" -> {
                    Item item = first.getItems().get(2);
                    if (item instanceof FullRestore) {
                        ((FullRestore) item).useHeal(first.getPokemonUseItemWith());
                        ((FullRestore) item).useCure(first.getPokemonUseItemWith());
                        item.setAmount(item.getAmount() - 1);

                        action.add(first.getName() + " used a Full Restore with " + first.getPokemonUseItemWith().getName() + ".");
                        action.add(first.getPokemonUseItemWith().getName() + "'s HP was restored.");
                        GameController.getInstance().setActions(action);
                    }
                }
                case "s1" -> {
                    first.setCurrentPokemon(first.getSecondPokemon());
                    setPokemonInParty();

                    action.add(first.getName() + " switched pokemon to " + first.getCurrentPokemon().getName() + ".");
                    GameController.getInstance().setActions(action);

                    GameController.getInstance().setFainted(false);
                }
                case "s2" -> {
                    first.setCurrentPokemon(first.getThirdPokemon());
                    setPokemonInParty();

                    action.add(first.getName() + " switched pokemon to " + first.getCurrentPokemon().getName() + ".");
                    GameController.getInstance().setActions(action);

                    GameController.getInstance().setFainted(false);
                }
            }

            first.setAction(null);
            Goto.battlePage();
            Goto.dialogPage();
        }
    }

    public static void startTurn(Player p1,Player p2) {
        //ไว้เริ่มใช้งานฟังก์ชั่นต่างๆ เพื่อไปเรียกใช้ whofirst,startaction อีกที
        first = whoFirst(p1,p2);
        if (first == p1) last = p2;
        else last = p1;

        //เริ่มactionของตัวแรกที่ได้ตีก่อน
        startAction(first,last);
    }

    private static Player whoFirst(Player p1, Player p2) {
        //เช้คว่าใครเริ่มก่อน
        if (Objects.equals(p1.getAction(), "s1") || Objects.equals(p1.getAction(), "s2") || Objects.equals(p1.getAction(), "i1") || Objects.equals(p1.getAction(), "i2") || Objects.equals(p1.getAction(), "i3")) {
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

    private static void checkIsAllDead(Player player, Player opponent) {
        boolean isAllDead = true;
        for (Pokemon pokemon : player.getPokemonsParty()) {
            if (pokemon.getHp() > 0) {
                isAllDead = false;
                break;
            }
        }
        if (isAllDead) {
            GameController.getInstance().setGameEnded(true);
            GameController.getInstance().setWinner(opponent.getName());
        }
    }

    private static void checkIsPokemonDead(Player player) {
        if (player.getCurrentPokemon().getHp() == 0) {
            ArrayList<String> action = GameController.getInstance().getActions();
            action.add(player.getCurrentPokemon().getName() + " fainted!");
            GameController.getInstance().setActions(action);
            player.setAction("fainted");
            GameController.getInstance().setFainted(true);
        }
    }

    public static void setPokemonInParty() {
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

    public static Player getFirst() {
        return first;
    }

    public static Player getLast() {
        return last;
    }
}
