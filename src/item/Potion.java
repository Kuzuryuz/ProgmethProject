package item;

import pokemon.Pokemon;
import usage.Healable;

public class Potion extends Item implements Healable{
    private int restoreHP;

    public Potion(int restoreHP,int amount) {
        super("Potion", amount);
        setRestoreHP(restoreHP);
    }

    @Override
    public void useHeal(Pokemon target) {
        target.setHp(target.getHp()+this.getRestoreHP());
    }

    public int getRestoreHP() {
        return restoreHP;
    }

    public void setRestoreHP(int restoreHP) {
        this.restoreHP = restoreHP;
    }
}
