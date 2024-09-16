package game.enemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.*;

/***
 * This class represents enemy factory on the east side of the map
 * @author Wong Heng Yew
 */
public class EastMapEnemyFactory implements EnemyFactory {

    /**
     * Method that returns new instance of Skeleton Bandit
     * @param location spawning ground location
     * @return a new instance of Skeleton Bandit
     */
    @Override
    public HostileCreature newGraveyardEnemy(Location location) {
        return new SkeletalBandit();
    }

    /**
     * Method that returns new instance of Giant Crayfish
     * @param location spawning ground location
     * @return a new instance of Giant Crayfish
     */
    @Override
    public HostileCreature newPuddleOfWaterEnemy(Location location) {
        return new GiantCrayfish();
    }

    /**
     * Method that returns new instance of Ginat Dog
     * @param location spawning ground location
     * @return a new instance of Giant Dog
     */
    @Override
    public HostileCreature newGustOfWindEnemy(Location location) {
        return new GiantDog();
    }

}
