package item;

import pokemon.Pokemon;
import usage.Curable;
import usage.Healable;
import usage.Status;

public class FullRestore extends Item implements Healable, Curable {
    public FullRestore(int amount) {
        super("Full Restore", amount);
    }

    @Override
    public void useHeal(Pokemon target) {
        target.setHp(target.getMaxHp());
    }

    @Override
    public void useCure(Pokemon target) {
        target.setStatus(Status.NONE);
    }
}
