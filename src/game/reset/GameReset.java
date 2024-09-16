package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Consumables.Runes;
import game.Enum.Status;
import game.TradingSystem.Trader;
import game.TradingSystem.TradingManager;

/**
 * Class that performs the reset action
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see GameReset,ResetManager,Resettable
 *
 */
public class GameReset extends Action {

    private Actor target;
    Runes my_runes;
    int runes;
    String direction;
    String weapon_to_buy;


    /**
     * The actor performing the reset action, only player
     * @param target
     * @param direction
     */
    public GameReset(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }


    /**
     * Will run the reset action if the interator is a player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(actor.hasCapability(Status.HOSTILE_CREATURE))) {
            ResetManager instance = ResetManager.getInstance();
            instance.run();




        }

        return "";
    }

    /**
     * Simple menu description
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at Site of Grace?";
    }
}

