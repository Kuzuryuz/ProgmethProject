package item;

import pokemon.Pokemon;
import usage.Curable;
import usage.Healable;
import usage.Revivable;

public class Revive extends Item implements Revivable, Healable, Curable {
    public Revive(int restoreHP,int amount){
        super("Revive", restoreHP, amount);
    }

    @Override
    public void useHeal(Pokemon target) {

    }

    @Override
    public void useCure(Pokemon target) {

    }

    @Override
    public void useRevive(Pokemon target) {

    }
}
