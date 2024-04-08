package pokemon;

import skill.BaseSkill;
import usage.Status;
import usage.Type;

import java.util.ArrayList;

public class Pokemon{

    private String name;
    private Type type;

    private Type type2;
    private int hp;

    private int atk;

    private int def;

    private int spa;

    private int spd;

    private int spe;

    private Status status;

    private String imgsrc;
    private ArrayList<BaseSkill> moves;



    public Pokemon(String name, Type type,Type type2, int hp, int atk, int def, int spa, int spd, int spe, String imgsrc) {
        this.setName(name);
        this.setType(type);
        this.setType2(type2);
        this.setHp(hp);
        this.setAtk(atk);
        this.setDef(def);
        this.setSpa(spa);
        this.setSpd(spd);
        this.setSpe(spe);
        this.setImgsrc(imgsrc);
    }

    public void checkStatus(){

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
}

