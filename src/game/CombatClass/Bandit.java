package game.CombatClass;

import edu.monash.fit2099.engine.actors.Actor;
import game.Enum.Status;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Uchigatana;
import game.reset.ResetManager;
/**
 * Class that sets up an archeologist class
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class Bandit implements ClassHandler{
    /**
     * Sets up the Bandit class
     * @param actor
     * @param choice
     * @return
     */
    @Override
    public int chooseClass(Actor actor, int choice) {
        if (choice == 2){
            GreatKnife myKnife = new GreatKnife();
            actor.addWeaponToInventory(myKnife);
            actor.addCapability(Status.SUMMONED);
            actor.resetMaxHp(414);
            ResetManager resetManager = ResetManager.getInstance();
            resetManager.setMax_hp(414);
        }
        return 414;

    }

    /**
     * Simple return message
     * @return
     */
    @Override
    public String chosenClass() {
        return "Bandit";
    }
}
