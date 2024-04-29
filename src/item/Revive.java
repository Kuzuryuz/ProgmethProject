package item;

import pokemon.Pokemon;
import usage.Curable;
import usage.Healable;
import usage.Revivable;
import usage.Status;

public class Revive extends Item implements Revivable, Curable {
    public Revive(int amount){
        super("Revive", amount);
    }

    @Override
    public void useCure(Pokemon target) {
        target.setStatus(Status.NONE);
    }

    @Override
    public void useRevive(Pokemon target) {
        if(target.getHp()>0){
            return;
        }else{
            target.setHp(target.getMaxHp()/2);
        }
    }
}
