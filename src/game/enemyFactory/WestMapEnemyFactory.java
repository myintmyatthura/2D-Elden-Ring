package game.enemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.*;

/***
 * This represents enemy factory on the west side of the map
 * @author Wong Heng Yew
 */
public class WestMapEnemyFactory implements EnemyFactory {

    /**
     * Method that returns new instance of Heavy Skeleton Swordsman
     * @param location spawning ground location
     * @return a new instance of Heavy Skeleton Swordsman
     */
    @Override
    public HostileCreature newGraveyardEnemy(Location location) {
        return new HeavySkeletonSwordsman();
    }

    /**
     * Method that returns new instance of Giant Crab
     * @param location spawning ground location
     * @return a new instance of Giant Crab
     */
    @Override
    public HostileCreature newPuddleOfWaterEnemy(Location location) {
        return new GiantCrab();
    }

    /**
     * Method that returns new instance of Lone Wolf
     * @param location spawning ground location
     * @return a new instance of Lone Wolf
     */
    @Override
    public HostileCreature newGustOfWindEnemy(Location location) {
        return new LoneWolf();
    }
}
