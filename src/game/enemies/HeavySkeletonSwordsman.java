package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gameWeapon.Grossmesser;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents Heavy Skeleton Swordsman: a hostile creature from west side of graveyard
 * It can be reset
 * @author Wong Heng Yew
 */
public class HeavySkeletonSwordsman extends GraveyardEnemy implements Resettable {

    /***
     * Constructor for heavy skeleton swordsman with defined values
     * Set runes amount and add it to item inventory
     * Add a new instance of Grossmesser to its weapon inventory
     */
    public HeavySkeletonSwordsman() {
        super("Heavy Skeleton Swordsman", 'q', 153,27);
        super.setRunes(35,892);
        addItemToInventory(getRunes());
        Grossmesser grossmesser = new Grossmesser();
        addWeaponToInventory(grossmesser);

    }

    /***
     * Get intrinsic weapon
     * @return a new instance of intrinsic weapon with defined values
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "shakes", 75);
    }

    /**
     * Register reset for Heavy Skeleton Swordsman
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Heavy Skeleton Swordsman Added");
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
