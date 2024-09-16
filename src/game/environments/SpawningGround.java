package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Enum.Status;
import game.Player;
import game.enemies.HeavySkeletonSwordsman;
import game.enemies.HostileCreature;
import game.enemies.SkeletalBandit;
import game.reset.ResetManager;
import game.utils.RandomNumberGenerator;

/***
 * This abstract class represents a ground that handle spawning lifetime of enemies
 * @author Wong Heng Yew
 */
public abstract class SpawningGround extends Ground{

    /**
     * hit count by (player/enemy) and tick count in int for tracking progression of pile of bones
     */
    private int hitCountByPlayer,hitCountByEnemy,tickCount;
    /**
     * Constructor for a spawning ground
     * Initialise the counts as 0
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
        this.hitCountByPlayer = 0;
        this.hitCountByEnemy = 0;
        this.tickCount = 0;
    }

    /***
     * Method that spawns a hostile creature if within spawning chance
     * Add approproate hostile to map if can spawn and make it resettable
     * @param location location of the spanwning ground
     */
    public void spawnHostileCreature(Location location ,HostileCreature hostileCreature) {
        // use utils to count the probability of spawning
        int randomNum = RandomNumberGenerator.getRandomInt(0, 100);

        if (randomNum <= hostileCreature.getSpawningChance()) {
            location.addActor(hostileCreature);
            hostileCreature.addCapability(Status.DOES_NOT_FOLLOW_PLAYER);
            ResetManager resetManager = ResetManager.getInstance();
            resetManager.registerResettable(hostileCreature);
        }
    }

    /***
     * Method that despawns a hostile creature from spawning groundif within chance of despawning
     * Remove the hostile creature from map if can despawn
     * @param location location of spawning ground
     */
    public void despawnHostileCreature(Location location) {
        int randomNum = RandomNumberGenerator.getRandomInt(0, 100);
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            if (actor.hasCapability(Status.DOES_NOT_FOLLOW_PLAYER) && !actor.hasCapability(Status.SUMMONED_ACTOR)) {
                if (randomNum <= 10) {
                    location.map().removeActor(location.getActor());
                }
            }
        }
    }

    /***
     * Method that navigates the lifetime of pile of bones
     * Tick count would track the progress and hit count would determine if player has
     * successfully hit the pile of bones three times
     * Remove pile of bones from map if possible or replace it with prev graveyard type enemy
     * @param location location of spawning ground
     */
    public void pileOfBonesProcess(Location location) {
        if (location.containsAnActor()){
            if (location.getActor().hasCapability(Status.PILE_OF_BONES)){
                tickCount+=1;
                if (location.getActor().hasCapability(Status.HIT_BY_PLAYER)){
                    hitCountByPlayer +=1;
                    location.getActor().removeCapability(Status.HIT_BY_PLAYER);
                }
                if (location.getActor().hasCapability(Status.HIT_BY_ENEMY)){
                    hitCountByEnemy +=1;
                    location.getActor().removeCapability(Status.HIT_BY_ENEMY);
                }

                if (tickCount == 4) {
                    if (hitCountByPlayer == tickCount){
                        for (Item item : location.getActor().getItemInventory()) {
                            if (location.getActor().getItemInventory().size() != 0){ //if inventory is not empty
                                Player player = Player.getInstance();
                                Location playerLocation = location.map().locationOf(player);
                                location.getActor().getItemInventory().get(0).tick(playerLocation,location.getActor()); // runes from enemy
                            }
                        }
                    }
                    int totalCount = hitCountByEnemy + hitCountByPlayer;
                    if (totalCount == tickCount){
                        for (WeaponItem weapon : location.getActor().getWeaponInventory())
                            location.addItem(weapon);
                        location.map().removeActor(location.getActor());

                    } else {
                        if (location.getActor().hasCapability(Status.PREV_SWORDSMAN)){
                            location.map().removeActor(location.getActor());
                            location.addActor(new HeavySkeletonSwordsman());
                        } else if (location.getActor().hasCapability(Status.PREV_BANDIT)){
                            location.map().removeActor(location.getActor());
                            location.addActor(new SkeletalBandit());
                        }
                    }
                    tickCount = 0;
                    hitCountByPlayer = 0;
                    hitCountByEnemy = 0;
                }
            }
        }
    }
}


