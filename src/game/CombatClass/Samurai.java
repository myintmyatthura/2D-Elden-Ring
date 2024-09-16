package game.CombatClass;

import edu.monash.fit2099.engine.actors.Actor;
import game.Enum.Status;
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
public class Samurai implements ClassHandler{
    /**
     * Sets up the samurai class
     * @param actor
     * @param choice
     * @return
     */
    @Override
    public int chooseClass(Actor actor, int choice) {
        if (choice == 1){
            Uchigatana myKatana = new Uchigatana();
            actor.addWeaponToInventory(myKatana);
            actor.addCapability(Status.SUMMONED);
            actor.resetMaxHp(455);
            ResetManager resetManager = ResetManager.getInstance();
            resetManager.setMax_hp(455);

        }
        return 455;
    }

    /**
     * Returns a simple message
     * @return
     */

    @Override
    public String chosenClass() {
        return "Samurai";
    }
}
