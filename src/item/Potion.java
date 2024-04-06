package item;

import pokemon.Pokemon;
import usage.Healable;

public class Potion extends Item implements Healable{
    public Potion(int restoreHP,int amount) {
        super("Potion", restoreHP, amount);
    }

    @Override
    public void useHeal(Pokemon target) {

    }
}
