package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.gameWeapon.Scimitar;
import game.reset.ResetManager;
import game.reset.Resettable;

/***
 * This class represents Skeletal Bandit: a hostile creature from east side of graveyard
 * It can be rest
 * @author Wong Heng Yew
 */
public class SkeletalBandit extends GraveyardEnemy implements Resettable {
    /**
     * Constructor for Skeletal Bandit with defined values
     * Set runes amount and add it to item inventory
     * Add a new Scimitar to the weapon inventory
     *
     */
    public SkeletalBandit() {
        super("Skeleton Bandit", 'b', 184,27);
        Scimitar scimitar = new Scimitar();
        addWeaponToInventory(scimitar);
        super.setRunes(35,892);
        addItemToInventory(getRunes());
    }

    /***
     * Get intrinsic weapon of Skeletal Bandit
     * @return a new intrinsic weapon with defined values
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        // given set values not from spec
        return new IntrinsicWeapon(85, "giggles", 70);
    }

    /**
     * Register a reset for Skeleton Bandit
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Skeletal Bandit Added");
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
