package item;

import usage.Curable;
import usage.Healable;

public abstract class FullRestore extends Item implements Healable, Curable {
    private String name;
    private int restoreHP;
    private int amount;

    public FullRestore(String name,int restoreHP,int amount) {
        super(name, restoreHP, amount);
    }

    @Override
    public void useHeal(int healAmount) {

    }

    @Override
    public void useCure() {

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
}
