package item;

import pokemon.Pokemon;
import usage.Curable;
import usage.Healable;
import usage.Revivable;

public class Revive extends Item implements Revivable, Healable, Curable {
    private int restoreHP;

    public Revive(int restoreHP,int amount){
        super("Revive", amount);
        setRestoreHP(restoreHP);
    }

    @Override
    public void useHeal(Pokemon target) {
        if(target.getHp()>0){
            return;
        }else{
            target.setHp(target.getMaxHp()/2);
        }
    }

    @Override
    public void useCure(Pokemon target) {

    }

    @Override
    public void useRevive(Pokemon target) {

    }

    public int getRestoreHP() {
        return restoreHP;
    }

    public void setRestoreHP(int restoreHP) {
        this.restoreHP = restoreHP;
    }
}
