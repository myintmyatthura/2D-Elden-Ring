package game.enemyFactory;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.HostileCreature;

/***
 * Interface for enemy factory
 * Open for new method relating to generating enemy
 * @author Wong Heng Yew
 */
public interface EnemyFactory {

    /***
     * Method that intends to return a graveyard enemy with given location of spawning ground
     * @param location spawning ground location
     * @return a graveyard enemy
     */
    HostileCreature newGraveyardEnemy(Location location);

    /***
     * Method that intends to return a puddle of water enemy with given location of spawning ground
     * @param location spawning ground location
     * @return a puddle of water enemy
     */
    HostileCreature newPuddleOfWaterEnemy(Location location);

    /***
     * Method that intends to return a gust of wind enemy with given location of spawning ground
     * @param location spawning ground location
     * @return a gust of wind enemy
     */
    HostileCreature newGustOfWindEnemy(Location location);
}
