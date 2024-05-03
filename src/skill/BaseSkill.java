package skill;

import game.GameController;
import pokemon.Pokemon;
import usage.Buff;
import usage.Category;
import usage.Status;
import usage.Type;

import java.util.ArrayList;
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
    private Buff[] buff;
    private int buffChance;
    private boolean selfBuff;

    //Read line 1392 for more info
    private int userAtk;
    private int userSpa;
    private int userDef;
    private int userSpd;
    private int userSpe;
    private int userHp;
    private int userMaxHp;

    public BaseSkill(String name, Type type, Category category, Status status, Buff[] buff, int pp, int power, int buffChance, int statusChance, int accuracy, boolean selfBuff) {
        this.setName(name);
        this.setType(type);
        this.setCategory(category);
        this.setStatus(status);
        this.setPp(pp);
        this.setPower(power);
        this.setStatusChance(statusChance);
        this.setAccuracy(accuracy);
        this.setBuff(buff);
        this.setBuffChance(buffChance);
        this.setSelfBuff(selfBuff);
    }

    public void useSkill(Pokemon opponent, Pokemon user) {
        this.setUserAtk(user.getAtk());
        this.setUserDef(user.getDef());
        this.setUserSpa(user.getSpAtk());
        this.setUserSpd(user.getSpDef());
        this.setUserSpe(user.getSpd());
        this.setUserHp(user.getHp());
        this.setUserMaxHp(user.getMaxHp());

        // init Array list for actions dialog
        ArrayList<String> actions = GameController.getInstance().getActions();

        //if PP = 0 then you cannot use this skill
        if (this.getPp() <= 0) {
            System.out.println("No PP left for this move!");
            return;
        }

        //if this skill is not a status move or a buff/debuff move (skill attack ธรรมดา)
        if (this.getCategory() != Category.STATUS && this.getCategory() != Category.BUFF) {
            boolean sup = false;
            boolean not = false;
            boolean nope = false;

            //check paralysis (25% chance to not move)
            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    actions.add("But it failed!");
                    actions.add(user.getName() + " is fully paralyzed!");
                    GameController.getInstance().setActions(actions);
                    return;
                }
            }

            //คำนวน damage
            int damage;
            if (this.getCategory() == Category.PHYSICAL) {
                damage = (int) Math.round((1.3 * this.getPower() * ((double) this.getUserAtk() / opponent.getDef())) + 2);
            } else if (this.getCategory() == Category.SPECIAL) {
                damage = (int) Math.round((1.3 * this.getPower() * ((double) this.getUserSpa() / opponent.getSpDef())) + 2);
            } else {
                damage = 0;
            }

            if(Objects.equals(this.getType(),user.getType())){
                damage = (int) Math.round(damage * 1.5);
            }

            //check accuracy (if the rolled number is more than accuracy it will miss)
            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                actions.add("But it missed...!");
                GameController.getInstance().setActions(actions);
                return;
            }

            //type effectiveness line 78-1084
            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ICE) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.NORMAL) || (Objects.equals(opponent.getType2(), Type.NORMAL))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIGHTING) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.POISON) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GROUND) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FLYING) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.PSYCHIC) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.BUG) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FLYING) || (Objects.equals(opponent.getType2(), Type.FLYING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.ROCK) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GHOST) && (Objects.equals(opponent.getType(), Type.NORMAL) || (Objects.equals(opponent.getType2(), Type.NORMAL))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DRAGON) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {
                damage = 0;
                if (sup) {
                    sup = false;
                    nope = true;
                } else if (not) {
                    not = false;
                    nope = true;
                } else {
                    nope = true;
                }
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.PSYCHIC) || (Objects.equals(opponent.getType2(), Type.PSYCHIC))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.DARK) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.FAIRY) || (Objects.equals(opponent.getType2(), Type.FAIRY))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.STEEL) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.FIGHTING) || (Objects.equals(opponent.getType2(), Type.FIGHTING))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.DARK) || (Objects.equals(opponent.getType2(), Type.DARK))) && (!nope)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FAIRY) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON))) && (!nope)) {

                damage = damage / 2;
                if (sup) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            opponent.setHp(opponent.getHp() - damage);

            //print out effective message
            if (sup) {
                actions.add("It's super effective!");
            }

            if (not) {
                actions.add("It's not very effective...");
            }

            if (nope) {
                actions.add("But it had no effect...!");
            }

            //if this move can inflict status AND damage then calculate here
            int gacha2 = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha2 <= this.getStatusChance()) {
                opponent.setStatus(this.getStatus());
                if (Objects.equals(opponent.getStatus(), Status.POISON)) {
                    actions.add(opponent.getName() + " was poisoned!");
                } else if (Objects.equals(opponent.getStatus(), Status.PARALYSIS)) {
                    actions.add(opponent.getName() + " was paralyzed!");
                } else if (Objects.equals(opponent.getStatus(), Status.BURN)) {
                    actions.add(opponent.getName() + " was burned!");
                } else if (Objects.equals(opponent.getStatus(), Status.FREEZE)) {
                    actions.add(opponent.getName() + " was frozen!");
                } else if (Objects.equals(opponent.getStatus(), Status.SLEEP)) {
                    actions.add(opponent.getName() + " was put to sleep!");
                }
            }

            //if this move can inflict status AND buff/debuff then calculate here
            int gacha3 = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha3 <= this.getBuffChance() && !this.selfBuff) {
                for(int i=0;i< this.getBuff().length;i++) {
                    if (Objects.equals(this.getBuff()[i], Buff.ATKUP)) {
                        opponent.setAtk(opponent.getAtk() * 2);
                        actions.add(opponent.getName() + "'s attack rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKUP2)) {
                        opponent.setAtk(opponent.getAtk() * 4);
                        actions.add(opponent.getName() + "'s attack rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFUP)) {
                        opponent.setDef(opponent.getDef() * 2);
                        actions.add(opponent.getName() + "'s defense rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFUP2)) {
                        opponent.setDef(opponent.getDef() * 4);
                        actions.add(opponent.getName() + "'s defense rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPAUP)) {
                        opponent.setSpAtk(opponent.getSpAtk() * 2);
                        actions.add(opponent.getName() + "'s special attack rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPAUP2)) {
                        opponent.setSpAtk(opponent.getSpAtk() * 4);
                        actions.add(opponent.getName() + "'s special attack rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP)) {
                        opponent.setSpDef(opponent.getSpDef() * 2);
                        actions.add(opponent.getName() + "'s special defense rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP2)) {
                        opponent.setSpDef(opponent.getSpDef() * 4);
                        actions.add(opponent.getName() + "'s special defense rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP)) {
                        opponent.setSpd(opponent.getSpd() * 2);
                        actions.add(opponent.getName() + "'s speed rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP2)) {
                        opponent.setSpd(opponent.getSpd() * 4);
                        actions.add(opponent.getName() + "'s speed rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN)) {
                        opponent.setAtk(opponent.getAtk() / 2);
                        actions.add(opponent.getName() + "'s attack fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN2)) {
                        opponent.setAtk(opponent.getAtk() / 4);
                        actions.add(opponent.getName() + "'s attack fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN)) {
                        opponent.setDef(opponent.getDef() / 2);
                        actions.add(opponent.getName() + "'s defense fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN2)) {
                        opponent.setDef(opponent.getDef() / 4);
                        actions.add(opponent.getName() + "'s defense fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPADOWN)) {
                        opponent.setSpAtk(opponent.getSpAtk() / 2);
                        actions.add(opponent.getName() + "'s special attack fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPADOWN2)) {
                        opponent.setSpAtk(opponent.getSpAtk() / 4);
                        actions.add(opponent.getName() + "'s special attack fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN)) {
                        opponent.setSpDef(opponent.getSpDef() / 2);
                        actions.add(opponent.getName() + "'s special defense fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN2)) {
                        opponent.setSpDef(opponent.getSpDef() / 4);
                        actions.add(opponent.getName() + "'s special defense fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN)) {
                        opponent.setSpd(opponent.getSpd() / 2);
                        actions.add(opponent.getName() + "'s speed fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN2)) {
                        opponent.setSpd(opponent.getSpd() / 4);
                        actions.add(opponent.getName() + "'s speed fell harshly!");
                    }
                    if(Objects.equals(this.getBuff()[i], Buff.RECOIL)){
                        this.setUserHp(this.getUserHp()-(damage/4));
                        actions.add(name + " took recoil damage!");
                    }

                }
            }else if(gacha3 <= this.getBuffChance() && this.selfBuff){
                for(int i=0;i< this.getBuff().length;i++) {
                    if (Objects.equals(this.getBuff()[i], Buff.ATKUP)) {
                        this.setUserAtk(this.getUserAtk() * 2);
                        actions.add(name + "'s attack rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKUP2)) {
                        this.setUserAtk(this.getUserAtk() * 4);
                        actions.add(name + "'s attack rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFUP)) {
                        this.setUserDef(this.getUserDef() * 2);
                        actions.add(name + "'s defense rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFUP2)) {
                        this.setUserDef(this.getUserDef() * 4);
                        actions.add(name + "'s defense rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPAUP)) {
                        this.setUserSpa(this.getUserSpa() * 2);
                        actions.add(name + "'s special attack rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPAUP2)) {
                        this.setUserSpa(this.getUserSpa() * 4);
                        actions.add(name + "'s special attack rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP)) {
                        this.setUserSpd(this.getUserSpd() * 2);
                        actions.add(name + "'s special defense rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP2)) {
                        this.setUserSpd(this.getUserSpd() * 4);
                        actions.add(name + "'s special defense rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP)) {
                        this.setUserSpe(this.getUserSpe() * 2);
                        actions.add(name + "'s speed rose!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP2)) {
                        this.setUserSpe(this.getUserSpe() * 4);
                        actions.add(name + "'s speed rose sharply!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN)) {
                        this.setUserAtk(this.getUserAtk() / 2);
                        actions.add(name + "'s attack fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN2)) {
                        this.setUserAtk(this.getUserAtk() / 4);
                        actions.add(name + "'s attack fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN)) {
                        this.setUserDef(this.getUserDef() / 2);
                        actions.add(name + "'s defense fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN2)) {
                        this.setUserDef(this.getUserDef() / 4);
                        actions.add(name + "'s defense fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPADOWN)) {
                        this.setUserSpa(this.getUserSpa() / 2);
                        actions.add(name + "'s special attack fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPADOWN2)) {
                        this.setUserSpa(this.getUserSpa() / 4);
                        actions.add(name + "'s special attack fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN)) {
                        this.setUserSpd(this.getUserSpd() / 2);
                        actions.add(name + "'s special defense fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN2)) {
                        this.setUserSpd(this.getUserSpd() / 4);
                        actions.add(name + "'s special defense fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN)) {
                        this.setUserSpe(this.getUserSpe() / 2);
                        actions.add(name + "'s speed fell!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN2)) {
                        this.setUserSpe(this.getUserSpe() / 4);
                        actions.add(name + "'s speed fell harshly!");
                    }
                    if (Objects.equals(this.getBuff()[i], Buff.HEAL)) {
                        this.setUserHp(this.getUserMaxHp()*50/100);
                        actions.add(name + "'s HP recovered!");
                    }
                    if(Objects.equals(this.getBuff()[i], Buff.RECOIL)){
                        this.setUserHp(this.getUserHp()-(damage/4));
                        actions.add(name + " took recoil damage!");
                    }
                }
            }

            //if this move is a status move (ให้ status อย่างเดียว ไม่ damage)
        } else if(this.getCategory() == Category.STATUS) {
            //check paralysis
            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    actions.add(" is fully paralyzed!");
                    return;
                }
            }

            //check accuracy
            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                actions.add("But it missed...!");
                return;
            }

            //Toxic does not work on Poison and steel types
            if (Objects.equals(this.getStatus(), Status.POISON) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL)))) {
                actions.add("But it had no effect...!");
                return;
            }
            if (Objects.equals(this.getStatus(), Status.POISON) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON)))) {
                actions.add("But it had no effect...!");
                return;
            }

            //if attack is electric and opponent is ground type, cannot paralyze
            if (Objects.equals(this.getType(), Type.ELECTRIC) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND)))) {
                actions.add("But it had no effect...!");
                return;
            }

            //electric pokemon cannot be paralyzed
            if (Objects.equals(this.getStatus(), Status.PARALYSIS) && (Objects.equals(opponent.getType(), Type.ELECTRIC) || (Objects.equals(opponent.getType2(), Type.ELECTRIC)))) {
                actions.add("But it had no effect...!");
                return;
            }


            //set opponent's status to the move's status
            opponent.setStatus(this.getStatus());
            if (Objects.equals(opponent.getStatus(), Status.POISON)) {
                actions.add(opponent.getName() + " was poisoned!");
            } else if (Objects.equals(opponent.getStatus(), Status.PARALYSIS)) {
                actions.add(opponent.getName() + " was paralyzed!");
            } else if (Objects.equals(opponent.getStatus(), Status.BURN)) {
                actions.add(opponent.getName() + " was burned!");
            } else if (Objects.equals(opponent.getStatus(), Status.FREEZE)) {
                actions.add(opponent.getName() + " was frozen!");
            } else if (Objects.equals(opponent.getStatus(), Status.SLEEP)) {
                actions.add(opponent.getName() + " was put to sleep!");
            }

            //Only buff/debuff the opponent with no damage
        } else if(this.getCategory() == Category.BUFF && !this.selfBuff){
            //check paralysis
            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    actions.add(" is fully paralyzed!");
                    return;
                }
            }

            //check accuracy
            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                actions.add("But it missed...!");
                return;
            }

            //calculate buff and debuff based on the buffs array
            for(int i=0;i< this.getBuff().length;i++) {
                if (Objects.equals(this.getBuff()[i], Buff.ATKUP)) {
                    opponent.setAtk(opponent.getAtk() * 2);
                    actions.add(opponent.getName() + "'s attack rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKUP2)) {
                    opponent.setAtk(opponent.getAtk() * 4);
                    actions.add(opponent.getName() + "'s attack rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFUP)) {
                    opponent.setDef(opponent.getDef() * 2);
                    actions.add(opponent.getName() + "'s defense rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFUP2)) {
                    opponent.setDef(opponent.getDef() * 4);
                    actions.add(opponent.getName() + "'s defense rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPAUP)) {
                    opponent.setSpAtk(opponent.getSpAtk() * 2);
                    actions.add(opponent.getName() + "'s special attack rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPAUP2)) {
                    opponent.setSpAtk(opponent.getSpAtk() * 4);
                    actions.add(opponent.getName() + "'s special attack rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP)) {
                    opponent.setSpDef(opponent.getSpDef() * 2);
                    actions.add(opponent.getName() + "'s special defense rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP2)) {
                    opponent.setSpDef(opponent.getSpDef() * 4);
                    actions.add(opponent.getName() + "'s special defense rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP)) {
                    opponent.setSpd(opponent.getSpd() * 2);
                    actions.add(opponent.getName() + "'s speed rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP2)) {
                    opponent.setSpd(opponent.getSpd() * 4);
                    actions.add(opponent.getName() + "'s speed rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN)) {
                    opponent.setAtk(opponent.getAtk() / 2);
                    actions.add(opponent.getName() + "'s attack fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN2)) {
                    opponent.setAtk(opponent.getAtk() / 4);
                    actions.add(opponent.getName() + "'s attack fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN)) {
                    opponent.setDef(opponent.getDef() / 2);
                    actions.add(opponent.getName() + "'s defense fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN2)) {
                    opponent.setDef(opponent.getDef() / 4);
                    actions.add(opponent.getName() + "'s defense fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPADOWN)) {
                    opponent.setSpAtk(opponent.getSpAtk() / 2);
                    actions.add(opponent.getName() + "'s special attack fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPADOWN2)) {
                    opponent.setSpAtk(opponent.getSpAtk() / 4);
                    actions.add(opponent.getName() + "'s special attack fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN)) {
                    opponent.setSpDef(opponent.getSpDef() / 2);
                    actions.add(opponent.getName() + "'s special defense fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN2)) {
                    opponent.setSpDef(opponent.getSpDef() / 4);
                    actions.add(opponent.getName() + "'s special defense fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN)) {
                    opponent.setSpd(opponent.getSpd() / 2);
                    actions.add(opponent.getName() + "'s speed fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN2)) {
                    opponent.setSpd(opponent.getSpd() / 4);
                    actions.add(opponent.getName() + "'s speed fell harshly!");
                }
            }

            //Only buff AND Self buff
        }else if(this.getCategory() == Category.BUFF && this.selfBuff){
            //check paralysis
            if (Objects.equals(this.getStatus(), Status.PARALYSIS)) {
                int min = 0;
                int max = 100;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                if (gacha > 75) {
                    actions.add(" is fully paralyzed! ");
                    return;
                }
            }

            //check accuracy
            int min = 0;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if (gacha > this.getAccuracy()) {
                actions.add("But it failed..!");
                return;
            }

            //UserAtk and stuff explanation: After the user uses useSkill please set the user's stats to the user stat variables
            //(ไปเซต atk ของ user เป็น skillname.getUserAtk และทุกอย่าง หลังใช้ useSkill )
            //ยังไม่ได้ลอง test แต่น่าจะได้ (มั้ง)  หาวิธีอื่นไม่ได้ละ
            for(int i=0;i< this.getBuff().length;i++) {
                if (Objects.equals(this.getBuff()[i], Buff.ATKUP)) {
                    this.setUserAtk(this.getUserAtk() * 2);
                    actions.add(name + "'s attack rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKUP2)) {
                    this.setUserAtk(this.getUserAtk() * 4);
                    actions.add(name + "'s attack rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFUP)) {
                    this.setUserDef(this.getUserDef() * 2);
                    actions.add(name + "'s defense rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFUP2)) {
                    this.setUserDef(this.getUserDef() * 4);
                    actions.add(name + "'s defense rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPAUP)) {
                    this.setUserSpa(this.getUserSpa() * 2);
                    actions.add(name + "'s special attack rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPAUP2)) {
                    this.setUserSpa(this.getUserSpa() * 4);
                    actions.add(name + "'s special attack rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP)) {
                    this.setUserSpd(this.getUserSpd() * 2);
                    actions.add(name + "'s special defense rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFUP2)) {
                    this.setUserSpd(this.getUserSpd() * 4);
                    actions.add(name + "'s special defense rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP)) {
                    this.setUserSpe(this.getUserSpe() * 2);
                    actions.add(name + "'s speed rose!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDUP2)) {
                    this.setUserSpe(this.getUserSpe() * 4);
                    actions.add(name + "'s speed rose sharply!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN)) {
                    this.setUserAtk(this.getUserAtk() / 2);
                    actions.add(name + "'s attack fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.ATKDOWN2)) {
                    this.setUserAtk(this.getUserAtk() / 4);
                    actions.add(name + "'s attack fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN)) {
                    this.setUserDef(this.getUserDef() / 2);
                    actions.add(name + "'s defense fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.DEFDOWN2)) {
                    this.setUserDef(this.getUserDef() / 4);
                    actions.add(name + "'s defense fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPADOWN)) {
                    this.setUserSpa(this.getUserSpa() / 2);
                    actions.add(name + "'s special attack fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPADOWN2)) {
                    this.setUserSpa(this.getUserSpa() / 4);
                    actions.add(name + "'s special attack fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN)) {
                    this.setUserSpd(this.getUserSpd() / 2);
                    actions.add(name + "'s special defense fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPDEFDOWN2)) {
                    this.setUserSpd(this.getUserSpd() / 4);
                    actions.add(name + "'s special defense fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN)) {
                    this.setUserSpe(this.getUserSpe() / 2);
                    actions.add(name + "'s speed fell!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.SPEEDDOWN2)) {
                    this.setUserSpe(this.getUserSpe() / 4);
                    actions.add(name + "'s speed fell harshly!");
                }
                if (Objects.equals(this.getBuff()[i], Buff.HEAL)) {
                    this.setUserHp(this.getUserHp() + (this.getUserMaxHp()*50/100));
                    actions.add(name + "'s HP recovered!");
                }
            }
        }

        //Deplete pp by 1
        this.setPp(this.getPp() - 1);
        user.setAtk(this.getUserAtk());
        user.setDef(this.getUserDef());
        user.setSpAtk(this.getUserSpa());
        user.setSpDef(this.getUserSpd());
        user.setSpd(this.getUserSpe());
        user.setHp(this.getUserHp());

        GameController.getInstance().setActions(actions);
    }

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

    public Buff[] getBuff() {
        return buff;
    }

    public void setBuff(Buff[] buff) {
        this.buff = buff;
    }

    public int getBuffChance() {
        return buffChance;
    }

    public void setBuffChance(int buffChance) {
        this.buffChance = buffChance;
    }

    public boolean isSelfBuff() {
        return selfBuff;
    }

    public void setSelfBuff(boolean selfBuff) {
        this.selfBuff = selfBuff;
    }

    public int getUserAtk() {
        return userAtk;
    }

    public void setUserAtk(int userAtk) {
        this.userAtk = userAtk;
    }

    public int getUserSpa() {
        return userSpa;
    }

    public void setUserSpa(int userSpa) {
        this.userSpa = userSpa;
    }

    public int getUserDef() {
        return userDef;
    }

    public void setUserDef(int userDef) {
        this.userDef = userDef;
    }

    public int getUserSpd() {
        return userSpd;
    }

    public void setUserSpd(int userSpd) {
        this.userSpd = userSpd;
    }

    public int getUserSpe() {
        return userSpe;
    }

    public void setUserSpe(int userSpe) {
        this.userSpe = userSpe;
    }

    public int getUserHp() {
        return userHp;
    }

    public void setUserHp(int userHp) {
        this.userHp = userHp;
    }

    public int getUserMaxHp() {
        return userMaxHp;
    }

    public void setUserMaxHp(int userMaxHp) {
        this.userMaxHp = userMaxHp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSkill baseSkill = (BaseSkill) o;
        return Objects.equals(name, baseSkill.name);
    }
}
