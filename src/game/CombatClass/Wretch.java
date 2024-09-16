package game.CombatClass;

import edu.monash.fit2099.engine.actors.Actor;
import game.Enum.Status;
import game.gameWeapon.Club;
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
public class Wretch implements ClassHandler{
    /**
     * Sets up a wretch class
     * @param actor
     * @param choice
     * @return
     */
    @Override
    public int chooseClass(Actor actor, int choice) {
        if (choice == 3){
            Club myClub = new Club();
            actor.addWeaponToInventory(myClub);
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
        return "Wretch";
    }
}
