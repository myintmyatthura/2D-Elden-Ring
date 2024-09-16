package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Enum.Status;
import game.Player;
import game.enemies.HeavySkeletonSwordsman;
import game.enemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents bare dirt.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Wong Heng yew
 *
 */
public class Dirt extends Ground {

	/**
	 * hit count by (player/enemy) and tick count in int for tracking progression of pile of bones
	 */
	private int hitCountByPlayer,hitCountByEnemy,tickCount;

	/***
	 * Constructor for Dirt
	 */
	public Dirt() {

		super('.');
		this.hitCountByEnemy = 0;
		this.hitCountByPlayer = 0;
		this.tickCount = 0;
	}

	/***
	 * Ticks whatever is on top of Dirt
	 * This can pile of bones and this will proceed to handling progress of pile of bones
	 * It also can be hostile creature and it will determine by chance if it will despawn
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);


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
					System.out.println("hi");
					if (hitCountByPlayer == tickCount ){ // player defeat pile of bones
						//System.out.println("hit by player 4 times");
						for (Item item : location.getActor().getItemInventory()) {
							if (location.getActor().getItemInventory().size() != 0){ //if it is runes for hostile enemies
								//System.out.println("update rune");
								Player player = Player.getInstance();
								Location playerLocation = location.map().locationOf(player);
								//System.out.println(location.getActor().getItemInventory().get(0).getDisplayChar());
								location.getActor().getItemInventory().get(0).tick(playerLocation,location.getActor()); // runes from enemy
								//target.getItemInventory().get(0).tick(map.locationOf(attacker),target); // runes from enemy
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
}
