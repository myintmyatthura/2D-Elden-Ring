package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that represents Golden Fog Door
 * @author Wong Heng Yew
 */
public class GoldenFogDoor extends Ground {

    /**
     * Location of destination
     */
    private Location destination;

    /**
     * Direction of the move
     */
    private String direction;

    /**
     * Constructor for Golden Fog Door with pre-set display char input and additional inputs
     * @param destination Location of destination where the door will lead to
     * @param direction Direction of the move for the actor
     */
    public GoldenFogDoor(Location destination, String direction) {
        super('D');
        this.destination = destination;
        this.direction = direction;
    }

    /**
     * Method that gives actor possible move action to carry out using Golden Fog Door
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an Action List of possible actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if(actor.getDisplayChar() == '@'){
            actions.add(new MoveActorAction(destination,this.direction));

        }
        return actions;
    }
}
