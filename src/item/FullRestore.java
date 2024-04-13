package item;

import pokemon.Pokemon;
import usage.Curable;
import usage.Healable;

public class FullRestore extends Item implements Healable, Curable {
    public FullRestore(int amount) {
        super("Full Restore", amount);
    }

    @Override
    public void useHeal(Pokemon target) {

    }

    @Override
    public void useCure(Pokemon target) {

    }
}
