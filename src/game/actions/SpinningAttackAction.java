package game.actions;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/***
 * The Spinning Attack Action class represents unique area attack carried out by some enemies through a weapon.
 * It follows the structure of Attack Action but does attack at every exit of an actor.
 * @author Wong Heng Yew
 */
public class SpinningAttackAction extends Action {

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /***
     * List of targets from every exit
     */
    private ArrayList<Actor> targets =new ArrayList<>();

    /***
     * List of available enemies direction
     */
    private ArrayList<Location> directions =new ArrayList<>();

    /***
     * Executes the spinning attack from an actor with the spinning weapon.
     * If there is an actor at the exit, attempt target and do damage if successfully.
     * Death action on target is called if target is unconscious.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";
        Location here = map.locationOf(actor);      // current actor location

        for (Exit exit : here.getExits()) {

            Location destination = exit.getDestination();

            // checks if an actor is in exit
            if (map.isAnActorAt(destination)) {

                Actor otherActor = map.getActorAt(destination);

                weapon = actor.getWeaponInventory().get(0);

                if ((rand.nextInt(100) <= weapon.chanceToHit())) {
                    targets.add(otherActor);
                    directions.add(destination);
                    int damage = weapon.damage();
                    result = actor + " " + weapon.verb() + " " + otherActor + " for " + damage + " damage.";
                    otherActor.hurt(damage);
                    if (!otherActor.isConscious()) {
                        result += new DeathAction(actor).execute(otherActor, map);
                    }
                }
            }
        }
        return result;

    }

    /***
     * Description of actor activity in a series of attack
     * @param actor The actor performing the action.
     * @return a String containing series of successful attack detailing target type and weapon used
     */
    @Override
    public String menuDescription(Actor actor) {
        String output = "";
        int i;
        for (i=0; i < targets.size(); i++){
            output += actor + " attack " + targets.get(i) + " at " + directions.get(i) + " with Intrinsic Weapon." + "/n";
        }
        return output;
    }
}
