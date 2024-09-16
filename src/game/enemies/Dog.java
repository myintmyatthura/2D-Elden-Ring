package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Enum.Status;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents Dog: a hostile creature from Stormveil Castle
 * It can be reset
 * @author Wong Heng Yew
 */
public class Dog extends HostileCreature implements Resettable {
    /**
     * Constructor of Dog with predefined values
     * Set number of runes in a range
     * Has a Stormveil Castle identity
     *
     */
    public Dog() {
        super("Dog", 'a', 104, 37);
        super.setRunes(52,1390);
        addItemToInventory(getRunes());
        this.addCapability(Status.STORMVEIL_CASTLE);
    }

    /**
     * Getter of Dog intrinsic weapon
     * @return a new instance of Dog intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

    /**
     * Register a reset for Dog
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Dog added");

    }

    /**
     * Method that removes actor when a reset is underway
     * @param map Map where reset is happening
     */
    @Override
    public void start_reset(GameMap map) {
        ActorLocationsIterator locations = new ActorLocationsIterator();
        locations.locationOf(this);
        map.removeActor(this);

    }
}
