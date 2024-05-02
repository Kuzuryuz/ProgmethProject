package pokemon;

import game.GameController;
import skill.BaseSkill;
import usage.Status;
import usage.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Pokemon{
    private String name;
    private Type type;
    private Type type2;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spa;
    private int spd;
    private int spe;
    private boolean wasPara;
    private boolean wasBurn;
    private boolean isSleep;
    private boolean notSleep;
    private int sleepTurns;
    private int currentSleep = 0;
    private int special = 0;
    private Status status;
    private String imgsrc;
    private BaseSkill[] moves;

    public Pokemon(String name, Type type,Type type2, int hp, int atk, int def, int spa, int spd, int spe, String imgsrc, BaseSkill[] moves) {
        this.setName(name);
        this.setType(type);
        this.setType2(type2);
        this.setMaxHp(hp);
        this.setHp(hp);
        this.setAtk(atk);
        this.setDef(def);
        this.setSpa(spa);
        this.setSpd(spd);
        this.setSpe(spe);
        this.setImgsrc(imgsrc);
        this.setMoves(moves);
        this.setStatus(Status.NONE);
    }

    public Pokemon(Pokemon pokemon) {
        this.setName(pokemon.getName());
        this.setType(pokemon.getType());
        this.setType2(pokemon.getType2());
        this.setMaxHp(pokemon.getMaxHp());
        this.setHp(pokemon.getHp());
        this.setAtk(pokemon.getAtk());
        this.setDef(pokemon.getDef());
        this.setSpa(pokemon.getSpa());
        this.setSpd(pokemon.getSpd());
        this.setSpe(pokemon.getSpe());
        this.setImgsrc(pokemon.getImgsrc());
        this.setMoves(Arrays.stream(pokemon.getMoves())
            .map(skill -> new BaseSkill(skill.getName(), skill.getType(), skill.getCategory(), skill.getStatus(), skill.getBuff(), skill.getPp(), skill.getPower(), skill.getBuffChance(), skill.getStatusChance(), skill.getAccuracy(), skill.isSelfBuff() ))
            .toArray(BaseSkill[]::new));
        this.setStatus(pokemon.getStatus());
    }

    public void checkStatus(){
        ArrayList<String> actions = GameController.getInstance().getActions();

        //For Poison, takes damage every turn when checkstatus is activated
        if(Objects.equals(this.getStatus(), Status.POISON)){
            actions.add(this.getName() + " was hurt by Poison!");
            this.setHp(this.getHp()-(this.getMaxHp()/8));
        }

        //Half the speed when inflicted paralysis
        if(Objects.equals(this.getStatus(), Status.PARALYSIS)){
            if(!wasPara) {
                this.setSpe(this.getSpe() / 2);
                this.setWasPara(true);
            }
        }

        //Half attack and damage every turn when inflicted burn
        if(Objects.equals(this.getStatus(), Status.BURN)){
            if(!wasBurn){
                this.setAtk(this.getAtk()/2);
                this.setWasBurn(true);
            }
            actions.add(this.getName() + " was hurt by burn!");
            this.setHp(this.getHp()-(this.getMaxHp()/16));
        }

        //if wasPara or wasBurn is true, if the pokemon is healed of its status this will return its attack and speed back to normal
        if(Objects.equals(this.getStatus(),Status.NONE)){
            if(wasPara){
                this.setSpe(this.getSpe()*2);
                this.setWasPara(false);
            }
            if(wasBurn){
                this.setAtk(this.getAtk()*2);
                this.setWasBurn(false);
            }
        }
    }

    //(ทำแยกออกมาเพราะว่าต้องเอาไว้ก่อน pokemon attack) randomise sleep turns (1-7) and immobilizes the pokemon until the counter reaches the turn
    public boolean checkSleep(){
        if(Objects.equals(this.getStatus(), Status.SLEEP)){
            if(!isSleep) {
                int min = 1;
                int max = 7;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                this.setSleepTurns(gacha);
                this.setSleep(true);
                this.setNotSleep(false);
            }

            //special is used to determine if the pokemon will move
            currentSleep++;
            if(currentSleep<sleepTurns){
                System.out.println(this.getName()+ " is fast asleep! ");
                special = 1;
                return true;
            }else{
                System.out.println(this.getName()+ " woke up! ");
                special = 0;
                this.setSleepTurns(0);
                this.setCurrentSleep(0);
                this.setSleep(false);
                this.setNotSleep(true);
            }
            if(this.isNotSleep()){
                this.setStatus(Status.NONE);
            }
        }
        return false;
    }

    //similar to checkSleep but the Pokemon has a 20% chance to be able to move again each check instead of a predetermined amount of turns
    public boolean checkFrozen(){
        ArrayList<String> actions = GameController.getInstance().getActions();

        if (Objects.equals(this.getStatus(), Status.FREEZE)){
            int min = 1;
            int max = 100;
            int gacha = (int) (Math.random() * (max - min + 1)) + min;
            if(gacha<=60){
                actions.add(this.getName()+" is frozen solid!");
                special=1;
                return true;
            } else {
                actions.add(this.getName()+" thawed out!");
                this.setStatus(Status.NONE);
                special=0;
                return false;
            }
        }

        GameController.getInstance().setActions(actions);
        return false;
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

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(Math.max(0, hp), getMaxHp());
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public BaseSkill[] getMoves() {
        return moves;
    }

    public void setMoves(BaseSkill[] moves) {
        this.moves = moves;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getSpe() {
        return spe;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public boolean isWasPara() {
        return wasPara;
    }

    public void setWasPara(boolean wasPara) {
        this.wasPara = wasPara;
    }

    public boolean isWasBurn() {
        return wasBurn;
    }

    public void setWasBurn(boolean wasBurn) {
        this.wasBurn = wasBurn;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }

    public boolean isNotSleep() {
        return notSleep;
    }

    public void setNotSleep(boolean notSleep) {
        this.notSleep = notSleep;
    }

    public int getSleepTurns() {
        return sleepTurns;
    }

    public void setSleepTurns(int sleepTurns) {
        this.sleepTurns = sleepTurns;
    }

    public int getCurrentSleep() {
        return currentSleep;
    }

    public void setCurrentSleep(int currentSleep) {
        this.currentSleep = currentSleep;
    }

}

