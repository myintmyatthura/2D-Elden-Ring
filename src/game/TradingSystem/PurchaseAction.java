package game.TradingSystem;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Enum.Status;
import game.Consumables.Runes;

import java.util.Scanner;
/**
 * Class that performs the trading action
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Trader,game.Player,TradingManager
 *
 */
public class PurchaseAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;
    Runes my_runes;
    int runes;
    String direction;
    String weapon_to_buy;


    /**
     * Constructor.
     *
     * @param target the Actor to attack
     *               the weapon to be bought
     */
    public PurchaseAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }


    /**
     * We will see which weapon that we would like to buy, then we will call the appropriate
     * action which in this case is purchase action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(actor.hasCapability(Status.HOSTILE_CREATURE))) {

            // adding weapons to trader inv
            System.out.println("What would you like to buy from trader?");
            System.out.println("1. Uchigatana\n2. Great Knife\n3. Club\n4. Scimitar");
            Scanner scanner = new Scanner(System.in);
            int itemChoice = scanner.nextInt();
            // finding rune object in player inventory


            // setting starting runes for player, can be done elsewhere


            TradingManager trader = TradingManager.getInstance();
            trader.purchase(itemChoice);



        }

        return "No purchase was made";
    }

    /**
     * Simple menu description for the actor
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy from trader:";
    }
}

