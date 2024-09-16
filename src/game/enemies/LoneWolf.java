package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Wong Heng Yew
 *
 */
public class LoneWolf extends GustOfWindEnemy implements Resettable {

    /***
     * Constructor for LoneWolf with defined values
     * Set runes amount and add it to item inventory
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 33);
        super.setRunes(55,60); // 55 to 1470
        addItemToInventory(getRunes());

    }

    /***
     * Get intrinsic weapon of LoneWolf
     * @return a new instance of intrinsic weapon with defined values
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * Register a reset for Lone Wolf
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Lone wolf");
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

