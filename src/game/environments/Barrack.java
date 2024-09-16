package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GodrickSoldier;

/**
 * Class that represents Barrack as spawning ground
 * @author Wong Heng Yew
 */
public class Barrack extends SpawningGround{

    /**
     * Constructor for Barrack with predefined display char
     *
     */
    public Barrack() {
        super('B');
    }

    /**
     * Tick cage for possible spawn/despawn activity.
     * It also tracks progress of pile of bones if it appears on top of cage
     * Also spawn a new Godrick soldier if no actor on top of cage
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        pileOfBonesProcess(location);

        despawnHostileCreature(location);

        if (!location.containsAnActor()) {
            spawnHostileCreature(location,new GodrickSoldier());
        }

    }
}
