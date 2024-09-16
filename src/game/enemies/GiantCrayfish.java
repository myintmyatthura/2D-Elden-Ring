package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents GiantCrayfish: a hostile creature from east side of puddle of water
 * It can be reset
 * @author Wong Heng Yew
 */
public class GiantCrayfish extends PuddleOfWaterEnemy implements Resettable {
    /**
     * Constructor for Giant Crayfish with defined values
     * Set runes amount and add it to item inventory
     *
     */
    public GiantCrayfish() {

        super("Giant Crayfish", 'R', 4803,1);
        super.setRunes(500,2374);
        addItemToInventory(getRunes());
    }

    /***
     * Get Intrinsic weapon of Giant Crayfish
     * @return a new instance of Giant Crayfish intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "pinches", 100);
    }

    /**
     * Register reset for Giant Crayfish
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Giant Crayfish added");
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
