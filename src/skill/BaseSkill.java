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
        this.setMaxPP(pp*160/100);
    }

    public void useSkill(Pokemon opponent, int atk, int spa){
        if(this.getCategory()!=Category.STATUS) {
            boolean sup = false;
            boolean not = false;
            boolean nope = false;

            int damage;
            if (this.getCategory() == Category.PHYSICAL) {
                damage = (int) Math.round((1.3 * this.getPower() * (atk / opponent.getDef())) + 2);
            } else if (this.getCategory() == Category.SPECIAL) {
                damage = (int) Math.round((1.3 * this.getPower() * (spa / opponent.getSpd())) + 2);
            } else {
                damage = 0;
            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL))) && (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.NORMAL) && (Objects.equals(opponent.getType(), Type.GHOST) || (Objects.equals(opponent.getType2(), Type.GHOST)))&& (nope!=true)) {
                damage = 0;
                if(sup==true){
                    sup=false;
                    nope=true;
                }else if(not==true){
                    not=false;
                    nope=true;
                }else{
                    nope=true;
                }
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ICE) || (Objects.equals(opponent.getType2(), Type.ICE)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.BUG) || (Objects.equals(opponent.getType2(), Type.BUG)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.STEEL) || (Objects.equals(opponent.getType2(), Type.STEEL)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.FIRE) && (Objects.equals(opponent.getType(), Type.DRAGON) || (Objects.equals(opponent.getType2(), Type.DRAGON)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.WATER) || (Objects.equals(opponent.getType2(), Type.WATER)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GROUND) || (Objects.equals(opponent.getType2(), Type.GROUND)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.ROCK) || (Objects.equals(opponent.getType2(), Type.ROCK)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.GRASS) || (Objects.equals(opponent.getType2(), Type.GRASS)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.GRASS) && (Objects.equals(opponent.getType(), Type.POISON) || (Objects.equals(opponent.getType2(), Type.POISON)))&& (nope!=true)) {
                damage = damage / 2;
                if (sup == true) {
                    sup = false;
                } else {
                    not = true;
                }

            }

            if (Objects.equals(this.getType(), Type.WATER) && (Objects.equals(opponent.getType(), Type.FIRE) || (Objects.equals(opponent.getType2(), Type.FIRE)))&& (nope!=true)) {
                damage = damage * 2;
                sup = true;
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
        }else{

        }

        this.setPp(this.getPp()-1);
    };

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
