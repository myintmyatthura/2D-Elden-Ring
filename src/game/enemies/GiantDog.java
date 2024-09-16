package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents GiantDog: a hostile creature from west side of gust of wind
 * It can be reset
 * @author Wong Heng Yew
 */
public class GiantDog extends GustOfWindEnemy implements Resettable {
    /**
     * Constructor for Giant Dog with defined values
     * Set runes amount and add it to item inventory
     *
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 4);
        super.setRunes(313,1808);
        addItemToInventory(getRunes());
    }

    /***
     * Get Intrinsic weapon of Giant Dog
     * @return a new instance of Giant Dog intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }

    /**
     * Register reset for Giant Dog
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Giant Dog Added");
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
