package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Direction;
import game.enemies.HostileCreature;
import game.enemyFactory.EastMapEnemyFactory;
import game.enemyFactory.EnemyFactory;
import game.enemyFactory.WestMapEnemyFactory;
import game.utils.Utils;

/***
 * This class represents graveyard as a spawning ground for enemy
 * @author Wong Heng Yew
 */
public class Graveyard extends SpawningGround{

    //private EnemyFactory factory;

    /**
     * Constructor for graveyard
     *
     */
    public Graveyard() {

        super('n');
        //factory.newGraveyardEnemy(this.);
    }

    /***
     * Tick the graveyard for possible spawn/despawn activity.
     * It also tracks progress of pile of bones if it appears on top of graveyard
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
                hostileCreature = eastMap.newGraveyardEnemy(location);
            } else {
                WestMapEnemyFactory westMap = new WestMapEnemyFactory();
                hostileCreature = westMap.newGraveyardEnemy(location);
            }

            spawnHostileCreature(location,hostileCreature);
        }
    }


}
