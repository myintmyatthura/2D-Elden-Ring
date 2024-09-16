package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Direction;
import game.enemies.HostileCreature;
import game.enemyFactory.EastMapEnemyFactory;
import game.enemyFactory.WestMapEnemyFactory;
import game.utils.Utils;

/***
 * This represents Gust of Wind spawning ground
 * @author Wong Heng Yew
 */
public class GustOfWind extends SpawningGround{

    /**
     * Constructor for gust of wind
     *
     */
    public GustOfWind() {
        super('&');
    }

    /***
     * Tick the gust of wind for possible spawn/despawn activity
     * Also checks for pile of bones activity if it exists
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        super.tick(location);

        pileOfBonesProcess(location);

        // despawning
        despawnHostileCreature(location);

        if (!location.containsAnActor()) {

            HostileCreature hostileCreature;

            if (Utils.direction(location) == Direction.EAST){
                EastMapEnemyFactory eastMap = new EastMapEnemyFactory();
                hostileCreature = eastMap.newGustOfWindEnemy(location);
            } else {
                WestMapEnemyFactory westMap = new WestMapEnemyFactory();
                hostileCreature = westMap.newGustOfWindEnemy(location);
            }
            spawnHostileCreature(location, hostileCreature);
        }
    }
}
