package skill;

import pokemon.Pokemon;
import usage.Category;
import usage.Status;
import usage.Type;

import java.util.Objects;

public class BaseSkill {
    private String name;
    private Type type;

    private Category category;

    private Status status;
    private int pp;
    private int power;
    private int accuracy;

    private int statusChance;
    private int maxPP;

    public BaseSkill(String name, Type type, Category category, Status status, int pp, int power, int statusChance, int accuracy) {
        this.setName(name);
        this.setType(type);
        this.setCategory(category);
        this.setStatus(status);
        this.setPp(pp);
        this.setPower(power);
        this.setStatusChance(statusChance);
        this.setAccuracy(accuracy);
        this.setMaxPP(pp * 160 / 100);
    }

    public void useSkill(Pokemon opponent, int atk, int spa) {
        if (this.getPp() <= 0) {
            System.out.println("No PP left for this move!");
            return;
        }
        if (this.getCategory() != Category.STATUS) {
            boolean sup = false;
            boolean not = false;
            boolean nope = false;

            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    System.out.println(" is fully paralyzed! ");
                    return;
                }
            }


            int damage;
            if (this.getCategory() == Category.PHYSICAL) {
                damage = (int) Math.round((1.3 * this.getPower() * (atk / opponent.getDef())) + 2);
            } else if (this.getCategory() == Category.SPECIAL) {
                damage = (int) Math.round((1.3 * this.getPower() * (spa / opponent.getSpd())) + 2);
            } else {
                damage = 0;
            }

            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                System.out.println("But it missed..!");
                return;
            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.NORMAL) || (Objects.equals(opponent.getType2(), Type.NORMAL))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.NORMAL) || (Objects.equals(opponent.getType2(), Type.NORMAL))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {
                damage = 0;
                if (sup == true) {
                    sup = false;
                    nope = true;
                } else if (not == true) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (nope != true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (nope != true)) {

                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            opponent.setHp(opponent.getHp() - damage);

            if (sup == true) {
                System.out.println("It's super effective!");
            }

            if (not == true) {
                System.out.println("It's not very effective...");
            }

            if (nope == true) {
                System.out.println("But it had no effect...!");
            }

            int gacha2 = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha2 <= this.getStatusChance()) {
                opponent.setStatus(this.getStatus());
                if (Objects.equals(opponent.getStatus(), Status.POISON)) {
                    System.out.println(opponent.getName() + " was poisoned!");
                } else if (Objects.equals(opponent.getStatus(), Status.PARALYSIS)) {
                    System.out.println(opponent.getName() + " was paralyzed!");
                } else if (Objects.equals(opponent.getStatus(), Status.BURN)) {
                    System.out.println(opponent.getName() + " was burned!");
                } else if (Objects.equals(opponent.getStatus(), Status.FREEZE)) {
                    System.out.println(opponent.getName() + " was frozen!");
                } else if (Objects.equals(opponent.getStatus(), Status.SLEEP)) {
                    System.out.println(opponent.getName() + " was put to sleep!");
                }
            }
        } else {
            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    System.out.println(" is fully paralyzed! ");
                    return;
                }
            }


            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                System.out.println("But it missed..!");
                return;
            }

            opponent.setStatus(this.getStatus());
            if (Objects.equals(opponent.getStatus(), Status.POISON)) {
                System.out.println(opponent.getName() + " was poisoned!");
            } else if (Objects.equals(opponent.getStatus(), Status.PARALYSIS)) {
                System.out.println(opponent.getName() + " was paralyzed!");
            } else if (Objects.equals(opponent.getStatus(), Status.BURN)) {
                System.out.println(opponent.getName() + " was burned!");
            } else if (Objects.equals(opponent.getStatus(), Status.FREEZE)) {
                System.out.println(opponent.getName() + " was frozen!");
            } else if (Objects.equals(opponent.getStatus(), Status.SLEEP)) {
                System.out.println(opponent.getName() + " was put to sleep!");
            }
        }

        this.setPp(this.getPp() - 1);
    }

    ;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getStatusChance() {
        return statusChance;
    }

    public void setStatusChance(int statusChance) {
        this.statusChance = statusChance;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public void setMaxPP(int maxPP) {
        this.maxPP = maxPP;
    }
}
