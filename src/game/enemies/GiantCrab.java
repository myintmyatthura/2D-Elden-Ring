package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents GiantCrab: a hostile creature from west side of puddle of water
 * It can be reset
 * @author Wong Heng Yew
 */
public class GiantCrab extends PuddleOfWaterEnemy implements Resettable {

    /**
     * Constructor of GiantCrab with its defined parameters
     * Set runes range and add it to its inventory
     *
     */
    public GiantCrab() {
        super("GiantCrab", 'C', 407,2);
        super.setRunes(318,4961);
        addItemToInventory(getRunes());

    }

    /***
     * Get Intrinsic weapon of Giant Crab
     * @return a new instance of Giant Crab intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        // given set values not from spec
        return new IntrinsicWeapon(208, "claws", 90);
    }

    /**
     * Register a reset for Giant Crab
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Giant crab added");
    }

    /**
     * Method that removes actor when a reset is underway
     * @param map Map where reset is happening
     */
    @Override
    public void start_reset(GameMap map){
        ActorLocationsIterator locations = new ActorLocationsIterator();
        locations.locationOf(this);
        map.removeActor(this);
    }
}
