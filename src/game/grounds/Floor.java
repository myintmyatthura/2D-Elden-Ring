package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Enum.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * Wong Heng Yew
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	/***
	 * Method that checks if the actor is hostile creature
	 * @param actor the Actor to check
	 * @return boolean value if the actor is hostile creature
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.HOSTILE_CREATURE);
	}
}
