package pokemon;

import skill.BaseSkill;
import usage.Status;
import usage.Type;

import java.util.ArrayList;
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
    private ArrayList<BaseSkill> moves;



    public Pokemon(String name, Type type,Type type2, int hp, int atk, int def, int spa, int spd, int spe, String imgsrc) {
        this.setName(name);
        this.setType(type);
        this.setType2(type2);
        this.setHp(hp);
        this.setMaxHp(hp);
        this.setAtk(atk);
        this.setDef(def);
        this.setSpa(spa);
        this.setSpd(spd);
        this.setSpe(spe);
        this.setImgsrc(imgsrc);
    }

    public void checkStatus(){
        if(Objects.equals(this.getStatus(), Status.POISON)){
            System.out.println(this.getName() + " was hurt by Poison!");
            this.setHp(this.getHp()-(this.getMaxHp()/16));
        }

        if(Objects.equals(this.getStatus(), Status.PARALYSIS)){
            if(!wasPara) {
                this.setSpe(this.getSpe() / 2);
                this.setWasPara(true);
            }
        }
        if(Objects.equals(this.getStatus(), Status.BURN)){
            if(!wasBurn){
                this.setAtk(this.getAtk()/2);
                this.setWasBurn(true);
            }
            System.out.println(this.getName() + " was hurt by burn!");
            this.setHp(this.getHp()-(this.getMaxHp()/16));
        }

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

    public void checkSleep(){
        if(Objects.equals(this.getStatus(), Status.SLEEP)){
            if(!isSleep) {
                int min = 1;
                int max = 7;
                int gacha = (int) (Math.random() * (max - min + 1)) + min;
                this.setSleepTurns(gacha);
                this.setSleep(true);
                this.setNotSleep(false);
            }

            currentSleep++;
            if(currentSleep<sleepTurns){
                System.out.println(this.getName()+ " is fast asleep! ");
                special = 1;
                return;
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
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public ArrayList<BaseSkill> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<BaseSkill> moves) {
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

