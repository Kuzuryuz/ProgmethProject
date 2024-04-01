package item;

import usage.Healable;

public abstract class Potion extends Item implements Healable{
    private String name;
    private int restoreHP;
    private int amount;

    public Potion(String name,int restoreHP,int amount) {
        super(name, restoreHP, amount);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestoreHP() {
        return restoreHP;
    }

    public void setRestoreHP(int restoreHP) {
        this.restoreHP = restoreHP;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void useHeal(int restoreHP) {

    }
}
