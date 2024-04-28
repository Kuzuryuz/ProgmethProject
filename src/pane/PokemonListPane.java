package pane;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import pokemon.Pokemon;
import skill.BaseSkill;
import usage.Buff;
import usage.Category;
import usage.Status;
import usage.Type;

import java.util.ArrayList;

public class PokemonListPane {
    private static PokemonListPane instance;
    private ArrayList<Pokemon> pokemons;
    private PokemonListPane() {
        pokemons = new ArrayList<>();
        pokemons.add(new Pokemon("Baxcalibur", Type.DRAGON, Type.ICE, 115, 145, 92, 75, 86, 87, "pokemon/baxcalibur.png",
                new BaseSkill[]{new BaseSkill("Dragon Claw", Type.DRAGON, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false),
                                new BaseSkill("Icicle Crash", Type.ICE, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,85,0,0,90,false),
                                new BaseSkill("Crunch", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.ATKDOWN}, 15,80,20,0,100,false),
                                new BaseSkill("Earthquake", Type.GROUND, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,100,0,0,100,false)}));
        pokemons.add(new Pokemon("Blaziken", Type.FIRE, Type.FIGHTING, 80, 160, 80, 130, 80, 100, "pokemon/blaziken.png",
                new BaseSkill[]{new BaseSkill("Blaze Kick", Type.FIRE, Category.PHYSICAL, Status.BURN, new Buff[]{Buff.NONE}, 10,85,0,10,90,false),
                        new BaseSkill("Close Combat", Type.FIGHTING, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.DEFDOWN2, Buff.SPDEFDOWN2}, 5,120,100,0,100,true),
                        new BaseSkill("Swords Dance", Type.NORMAL, Category.BUFF, Status.NONE, new Buff[]{Buff.ATKUP2}, 20,0,100,0,100,true),
                        new BaseSkill("Stone Edge", Type.ROCK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 5,100,0,0,80,false)}));
        pokemons.add(new Pokemon("Delphox", Type.FIRE, Type.PSYCHIC, 75, 69, 72, 114, 100, 104, "pokemon/delphox.png",
                new BaseSkill[]{new BaseSkill("Fire Blast", Type.FIRE, Category.SPECIAL, Status.BURN, new Buff[]{Buff.NONE}, 10,110,0,10,85,false),
                        new BaseSkill("Psychic", Type.PSYCHIC, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 10,90,0,0,100,false),
                        new BaseSkill("Will-o-Wisp", Type.FIRE, Category.STATUS, Status.BURN, new Buff[]{Buff.NONE}, 15,0,0,100,85,false),
                        new BaseSkill("Shadow Ball", Type.GHOST, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPDEFDOWN}, 15,80,20,0,100,false)}));
        pokemons.add(new Pokemon("Dragonite", Type.DRAGON, Type.FLYING, 91, 134, 95, 100, 100, 80, "pokemon/dragonite.png",
                new BaseSkill[]{new BaseSkill("Aqua Tail", Type.WATER, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,90,0,0,90,false),
                        new BaseSkill("Draco Meteor", Type.DRAGON, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPADOWN2}, 5,130,100,0,90,true),
                        new BaseSkill("Earthquake", Type.GROUND, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,100,0,0,100,false),
                        new BaseSkill("Hurricane", Type.FLYING, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 10,110,0,0,70,false)}));
        pokemons.add(new Pokemon("Gardevoir", Type.FAIRY, Type.PSYCHIC, 68, 65, 65, 125, 115, 80, "pokemon/gardevoir.png",
                new BaseSkill[]{new BaseSkill("Thunderbolt", Type.ELECTRIC, Category.SPECIAL, Status.PARALYSIS, new Buff[]{Buff.NONE}, 15,90,0,10,100,false),
                        new BaseSkill("Psychic", Type.PSYCHIC, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 10,90,0,0,100,false),
                        new BaseSkill("Moonblast", Type.FAIRY, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPADOWN}, 15,95,30,0,100,false),
                        new BaseSkill("Shadow Ball", Type.GHOST, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPDEFDOWN}, 15,80,20,0,100,false)}));
        pokemons.add(new Pokemon("Greninja", Type.WATER, Type.DARK, 72, 95, 67, 103, 71, 122, "pokemon/greninja.png",
                new BaseSkill[]{new BaseSkill("Night Slash", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,70,0,10,100,false),
                        new BaseSkill("Hydro Pump", Type.WATER, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 5,110,0,0,80,false),
                        new BaseSkill("Extrasensory", Type.PSYCHIC, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 20,80,0,0,100,false),
                        new BaseSkill("Ice Beam", Type.ICE, Category.SPECIAL, Status.FREEZE, new Buff[]{Buff.NONE}, 10,90,0,10,100,false)}));
        pokemons.add(new Pokemon("Iron Thorns", Type.ROCK, Type.ELECTRIC, 100, 134, 110, 70, 84, 72, "pokemon/ironthorns.png",
                new BaseSkill[]{new BaseSkill("Wild Charge", Type.ELECTRIC, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.RECOIL}, 15,90,100,0,100,false),
                        new BaseSkill("Crunch", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.ATKDOWN}, 15,80,20,0,100,false),
                        new BaseSkill("Earthquake", Type.GROUND, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,100,0,0,100,false),
                        new BaseSkill("Stone Edge", Type.ROCK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 5,100,0,0,80,false)}));
        pokemons.add(new Pokemon("Leafeon", Type.GRASS, Type.NULL, 65, 110, 130, 60, 65, 95, "pokemon/leafeon.png",
                new BaseSkill[]{new BaseSkill("Seed Bomb", Type.GRASS, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false),
                        new BaseSkill("Body Slam", Type.NORMAL, Category.PHYSICAL, Status.PARALYSIS, new Buff[]{Buff.NONE}, 15,85,0,30,100,false),
                        new BaseSkill("Swift", Type.NORMAL, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 20,60,0,0,100,false),
                        new BaseSkill("Take Down", Type.NORMAL, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.RECOIL}, 20,90,100,0,85,false)}));
        pokemons.add(new Pokemon("Lucario", Type.FIGHTING, Type.STEEL, 70, 110, 70, 115, 70, 90, "pokemon/lucario.png",
                new BaseSkill[]{new BaseSkill("Meteor Mash", Type.STEEL, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.ATKUP}, 10,90,20,0,90,true),
                        new BaseSkill("Close Combat", Type.FIGHTING, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.DEFDOWN2, Buff.SPDEFDOWN2}, 5,120,100,0,100,true),
                        new BaseSkill("Swords Dance", Type.NORMAL, Category.BUFF, Status.NONE, new Buff[]{Buff.ATKUP2}, 20,0,100,0,100,true),
                        new BaseSkill("Shadow Claw", Type.GHOST, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,70,0,0,100,false)}));
        pokemons.add(new Pokemon("Magcargo", Type.FIRE, Type.ROCK, 60, 50, 120, 90, 80, 30, "pokemon/magcargo.png",
                new BaseSkill[]{new BaseSkill("Blaze Kick", Type.FIRE, Category.PHYSICAL, Status.BURN, new Buff[]{Buff.NONE}, 10,85,0,10,90,false),
                        new BaseSkill("Close Combat", Type.FIGHTING, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.DEFDOWN2, Buff.SPDEFDOWN2}, 5,120,100,0,100,true),
                        new BaseSkill("Swords Dance", Type.NORMAL, Category.BUFF, Status.NONE, new Buff[]{Buff.ATKUP2}, 20,0,100,0,100,true),
                        new BaseSkill("Stone Edge", Type.ROCK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 5,100,0,0,80,false)}));
        pokemons.add(new Pokemon("Mimikyu", Type.GHOST, Type.FAIRY, 55, 90, 80, 50, 105, 96, "pokemon/mimikyu.png",
                new BaseSkill[]{new BaseSkill("Will-o-Wisp", Type.FIRE, Category.STATUS, Status.BURN, new Buff[]{Buff.NONE}, 15,0,100,0,85,false),
                        new BaseSkill("Curse", Type.GHOST, Category.BUFF, Status.NONE, new Buff[]{Buff.SPEEDDOWN,Buff.ATKUP,Buff.DEFUP}, 10,0,100,0,100,true),
                        new BaseSkill("Play Rough", Type.FAIRY, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.ATKDOWN}, 10,90,10,0,90,false),
                        new BaseSkill("Shadow Claw", Type.GHOST, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,70,0,0,100,false)}));
        pokemons.add(new Pokemon("Rayquaza", Type.DRAGON, Type.FLYING, 105, 180, 100, 180, 100, 115, "pokemon/rayquaza.png",
                new BaseSkill[]{new BaseSkill("Thunder", Type.ELECTRIC, Category.SPECIAL, Status.PARALYSIS, new Buff[]{Buff.NONE}, 10,110,0,10,70,false),
                        new BaseSkill("Draco Meteor", Type.DRAGON, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPADOWN2}, 5,130,100,0,90,true),
                        new BaseSkill("Blizzard", Type.ICE, Category.SPECIAL, Status.FREEZE, new Buff[]{Buff.NONE}, 5,110,0,10,70,false),
                        new BaseSkill("Hurricane", Type.FLYING, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 10,110,0,0,70,false)}));
        pokemons.add(new Pokemon("Sceptile", Type.GRASS, Type.NULL, 70, 85, 65, 105, 85, 120, "pokemon/sceptile.png",
                new BaseSkill[]{new BaseSkill("Leaf Storm", Type.GRASS, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPADOWN2}, 5,130,100,0,90,true),
                        new BaseSkill("Energy Ball", Type.GRASS, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPDEFDOWN}, 10,90,10,0,100,false),
                        new BaseSkill("Dragon Pulse", Type.DRAGON, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 10,85,0,0,100,false),
                        new BaseSkill("X-Scissor", Type.BUG, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false)}));
        pokemons.add(new Pokemon("Scolipede", Type.BUG, Type.POISON, 60, 100, 89, 55, 69, 112, "pokemon/scolipede.png",
                new BaseSkill[]{new BaseSkill("Agility", Type.NORMAL, Category.BUFF, Status.NONE, new Buff[]{Buff.SPEEDUP2}, 30,0,100,0,100,true),
                        new BaseSkill("Cross Poison", Type.POISON, Category.PHYSICAL, Status.POISON, new Buff[]{Buff.NONE}, 20,70,0,10,100,false),
                        new BaseSkill("Iron Tail", Type.STEEL, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,85,0,0,100,false),
                        new BaseSkill("Megahorn", Type.BUG, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,120,0,0,85,false)}));
        pokemons.add(new Pokemon("Snorlax", Type.NORMAL, Type.NULL, 160, 110, 65, 65, 110, 30, "pokemon/snorlax.png",
                new BaseSkill[]{new BaseSkill("Double-Edge", Type.NORMAL, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.RECOIL}, 15,120,100,0,100,false),
                        new BaseSkill("Crunch", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.ATKDOWN}, 15,80,20,0,100,false),
                        new BaseSkill("Curse", Type.GHOST, Category.BUFF, Status.NONE, new Buff[]{Buff.SPEEDDOWN,Buff.ATKUP,Buff.DEFUP}, 10,0,100,0,100,true),
                        new BaseSkill("Toxic", Type.POISON, Category.STATUS, Status.POISON, new Buff[]{Buff.NONE}, 10,0,0,100,90,false)}));
        pokemons.add(new Pokemon("Swampert", Type.WATER, Type.GROUND, 100, 150, 110, 95, 110, 70, "pokemon/swampert.png",
                new BaseSkill[]{new BaseSkill("Waterfall", Type.WATER, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false),
                        new BaseSkill("Stone Edge", Type.ROCK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 5,100,0,0,80,false),
                        new BaseSkill("Earthquake", Type.GROUND, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,100,0,0,100,false),
                        new BaseSkill("Ice Punch", Type.ICE, Category.PHYSICAL, Status.FREEZE, new Buff[]{Buff.NONE}, 15,75,0,10,100,false)}));
        pokemons.add(new Pokemon("Togekiss", Type.FAIRY, Type.FLYING, 85, 50, 95, 120, 115, 80, "pokemon/togekiss.png",
                new BaseSkill[]{new BaseSkill("Nasty Plot", Type.DARK, Category.BUFF, Status.NONE, new Buff[]{Buff.SPAUP2}, 20,0,0,0,100,true),
                        new BaseSkill("Thunder Wave", Type.ELECTRIC, Category.STATUS, Status.PARALYSIS, new Buff[]{Buff.NONE}, 20,0,0,100,90,false),
                        new BaseSkill("Roost", Type.FLYING, Category.BUFF, Status.NONE, new Buff[]{Buff.HEAL}, 6,0,100,0,100,true),
                        new BaseSkill("Air Slash", Type.FLYING, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 15,75,0,0,95,false)}));
        pokemons.add(new Pokemon("Weavile",Type.DARK,Type.ICE,70,120,65,45,85,125, "pokemon/weavile.png",
                new BaseSkill[]{new BaseSkill("Night Slash", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,70,0,10,100,false),
                        new BaseSkill("Swords Dance", Type.NORMAL, Category.BUFF, Status.NONE, new Buff[]{Buff.ATKUP2}, 20,0,100,0,100,true),
                        new BaseSkill("Low Kick", Type.FIGHTING, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 20,80,0,0,100,false),
                        new BaseSkill("Icicle Crash", Type.ICE, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 15,85,0,0,90,false)}));
        pokemons.add(new Pokemon("Wo-Chien", Type.DARK, Type.GRASS, 85, 85, 100, 95, 135, 70, "pokemon/wo-chien.png",
                new BaseSkill[]{new BaseSkill("Leaf Storm", Type.GRASS, Category.SPECIAL, Status.NONE, new Buff[]{Buff.SPADOWN2}, 5,130,100,0,90,true),
                        new BaseSkill("Body Slam", Type.NORMAL, Category.PHYSICAL, Status.PARALYSIS, new Buff[]{Buff.NONE}, 15,85,0,30,100,false),
                        new BaseSkill("Foul Play", Type.DARK, Category.PHYSICAL, Status.NONE, new Buff[]{Buff.NONE}, 10,95,0,0,100,false),
                        new BaseSkill("Dark Pulse", Type.DARK, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false)}));
        pokemons.add(new Pokemon("Umbreon", Type.DARK, Type.NULL, 95, 65, 110, 60, 130, 65, "pokemon/umbreon.png",
                new BaseSkill[]{new BaseSkill("Moonlight", Type.FAIRY, Category.BUFF, Status.NONE, new Buff[]{Buff.HEAL}, 6,0,100,0,100,true),
                        new BaseSkill("Dark Pulse", Type.DARK, Category.SPECIAL, Status.NONE, new Buff[]{Buff.NONE}, 15,80,0,0,100,false),
                        new BaseSkill("Curse", Type.GHOST, Category.BUFF, Status.NONE, new Buff[]{Buff.SPEEDDOWN,Buff.ATKUP,Buff.DEFUP}, 10,0,100,0,100,true),
                        new BaseSkill("Toxic", Type.POISON, Category.STATUS, Status.POISON, new Buff[]{Buff.NONE}, 10,0,0,100,90,false)}));
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
