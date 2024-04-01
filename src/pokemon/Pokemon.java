package pokemon;

import skill.BaseSkill;

import java.util.ArrayList;

public class Pokemon{

    private String name;
    private String type;

    private String type2;
    private int hp;

    private int atk;

    private int def;

    private int spa;

    private int spd;

    private int spe;

    private String imgsrc;
    private ArrayList<BaseSkill> moves;



    public Pokemon(String name, String type,String type2, int hp, int atk, int def, int spa, int spd, int spe, String imgsrc) {
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



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
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

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}

