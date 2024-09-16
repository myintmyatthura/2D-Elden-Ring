package game.weaponSkill;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.actions.AttackAction;
import game.utils.RandomNumberGenerator;
import game.gameWeapon.GreatKnife;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

import java.util.ArrayList;
import java.util.List;

public class Quickstep extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private GreatKnife greatKnife;

    /**
     * Constructor.
     *
     * @param target     the Actor to attack
     * @param direction  the direction where the attack should be performed (only used for display purposes)
     * @param greatKnife the Great Knife used for attakcing
     */
    public Quickstep(Actor target, String direction, GreatKnife greatKnife) {
        this.target = target;
        this.direction = direction;
        this.greatKnife = greatKnife;
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // Attack the target
        AttackAction aAttackAction = new AttackAction(this.target, this.direction, this.greatKnife);
        result += aAttackAction.execute(actor, map);

        // Find all possible exits
        Location actorLocation = map.locationOf(actor);
        List<Exit> possibleExits = actorLocation.getExits();
        ArrayList<Location> possibleLocations = new ArrayList<>();
        ArrayList<Exit> newPossibleLocations = new ArrayList<>();
        for (Exit possibleExit : possibleExits) {
            Location exitDestination = possibleExit.getDestination();
            if (exitDestination.canActorEnter(actor)) {
                newPossibleLocations.add(possibleExit);
                possibleLocations.add(exitDestination);
            }
        }

        // If there are possible exits, then randomly move to a Location
        if (!(possibleLocations == null)) {
            int randomIndex = RandomNumberGenerator.getRandomInt(possibleLocations.size() - 1);
            Exit randomExit = newPossibleLocations.get(randomIndex);
            Location randomLocation = possibleLocations.get(randomIndex);
            MoveActorAction aMoveActorAction = new MoveActorAction(randomLocation, randomExit.getName());
            result += aMoveActorAction.execute(actor, map);
        }

        return result;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses Quickstep " + "on " + target;

    }
}