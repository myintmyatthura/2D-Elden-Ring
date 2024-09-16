package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.CombatClass.ClassManager;
import game.Enum.Status;
import game.Summonings.Ally;
import game.Summonings.Invader;
import game.reset.ResetManager;
import game.utils.RandomNumberGenerator;
/**
 * Class that creates Enia trader
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class SummonAction extends Action {
    private Actor target;
    String direction;
    public SummonAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }

    /**
     * Making sure that the ally/invader is spawned at the available exits
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(actor.hasCapability(Status.HOSTILE_CREATURE))) {
            int randNum = RandomNumberGenerator.getRandomInt(1, 2);
            Location playerLocation = map.locationOf(actor);
            int pX = playerLocation.x();
            int pY = playerLocation.y();
            int[][] spawnLocations = {{pX - 1, pY + 1}, {pX, pY + 1}, {pX + 1, pY + 1}, {pX - 1, pY}, {pX - 1, pY - 1}, {pX, pY - 1}, {pX + 1, pY - 1}, {pX + 1, pY}};
            for (int[] loc : spawnLocations) {
                if (!(map.at(loc[0],loc[1]).containsAnActor())) {
                    if (randNum == 1) {
                        Ally ally = new Ally();
                        map.at(loc[0],loc[1]).addActor(ally);
                        //map.addActor(ally, new Location(map, loc[0], loc[1]));
                        ClassManager classManager = ClassManager.getInstance();
                        classManager.chooseRandomClass(ally);
                        break;
                    } else {
                        Invader invader = new Invader();
                        map.at(loc[0],loc[1]).addActor(invader);
                        //map.addActor(invader, new Location(map, loc[0], loc[1]));
                        ClassManager classManager = ClassManager.getInstance();
                        classManager.chooseRandomClass(invader);
                        break;
                    }

                }
            }
        }
        return "Something has arrived in your world";
    }


    /**
     * Simple menu description
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Summon a friend or foe";
    }
}

