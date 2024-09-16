package game.enemies;

import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Enum.Status;
import game.reset.ResetManager;

/***
 * This class represents Godrick Soldier: a hostile creature from Stormveil Castle
 * It can be reset
 * @author Wong Heng Yew
 */
public class GodrickSoldier extends HostileCreature{

    /**
     * Constructor of Godrick Soldier with predefined values
     * Set runes amount in a range
     * Has a Stormveil Castle status
     *
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, 45);
        super.setRunes(38,70);
        addItemToInventory(getRunes());
        this.addCapability(Status.STORMVEIL_CASTLE);
    }

    /**
     * Getter for Godrick Soldier intrinsic weapon
     * @return a new instance of Godrick intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(67, "beats", 84);
    }

    /**
     * Register a reset for Godrick Soldier
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Godrick Soldier added");
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
