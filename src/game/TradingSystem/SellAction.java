package game.TradingSystem;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Enum.Status;
import game.Consumables.Runes;
import game.gameWeapon.Club;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Uchigatana;

import java.util.Scanner;
/**
 * Class that performs the trading action
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Trader,game.Player,TradingManager
 *
 */
public class SellAction extends Action {


    /**
     * The Actor that is to be attacked
     */
    private Actor target;
    Runes my_runes;
    int runes;
    String direction;
    String weapon_to_buy;
    Uchigatana uchigatana;
    GreatKnife greatKnife;
    Club club;


    /**
     * Constructor.
     *
     * @param target the Actor to attack
     *               the weapon to be sold
     */
    public SellAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }


    /**
     * We will see which weapon that we would like to sell, then we will call the appropriate
     * action which in this case is sell action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(actor.hasCapability(Status.HOSTILE_CREATURE))) {

            // getting player's inventory
            // adding weapons to trader inv
            System.out.println("What would you like to sell");
            System.out.println("1. Uchigatana\n2. Great Knife\n3. Club\n4. Scimitar\n5. Axe of Godrick\n6. Grafted Dragon\n7. Remembrance");
            Scanner scanner = new Scanner(System.in);
            int itemChoice = scanner.nextInt();



            TradingManager trader = TradingManager.getInstance();
            trader.sell(itemChoice);



            }

        return "No sales was made";
    }

    /**
     * Simple display menu for the actor
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sell to trader:";
    }
}


