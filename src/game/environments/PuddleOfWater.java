package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Direction;
import game.enemies.HostileCreature;
import game.enemyFactory.EastMapEnemyFactory;
import game.enemyFactory.WestMapEnemyFactory;
import game.utils.Utils;

/***
 * This represents Puddle of Water spawning ground
 * @author Wong Heng Yew
 */
public class PuddleOfWater extends SpawningGround{

    /**
     * Constructor for puddle of water
     *
     */
    public PuddleOfWater() {
        super('~');
    }

    /***
     * Tick the puddle of water for possible spawn/desapwn activity
     * Also checks for pile of bones if it exists
     * @param location The location of the Ground
     */
    public void tick(Location location) {

        super.tick(location);

        pileOfBonesProcess(location);

        // despawning
        despawnHostileCreature(location);

        if (!location.containsAnActor()){

            HostileCreature hostileCreature;

            if (Utils.direction(location) == Direction.EAST){
                EastMapEnemyFactory eastMap = new EastMapEnemyFactory();
                hostileCreature = eastMap.newPuddleOfWaterEnemy(location);
            } else {
                WestMapEnemyFactory westMap = new WestMapEnemyFactory();
                hostileCreature = westMap.newPuddleOfWaterEnemy(location);
            }
            spawnHostileCreature(location,hostileCreature);
        }
    }
}
