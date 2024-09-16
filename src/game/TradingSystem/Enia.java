package game.TradingSystem;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Enum.Status;
import game.TradingSystem.ExchangeAction;
import game.TradingSystem.PurchaseAction;
import game.TradingSystem.SellAction;
import game.gameWeapon.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Class that creates Enia trader
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class Enia extends Actor {


    /**
     * Setting up Enia, she will have these new weapons
     *
     */
    public Enia() {
        super("Enia", 'E',100);
        this.addCapability(Status.RESTING);
        AxeOfGodrick axe = new AxeOfGodrick();
        GraftedDragon dragon = new GraftedDragon();
        this.addWeaponToInventory(axe);
        this.addWeaponToInventory(dragon);


    }

    /**
     * Trader does not do anything
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return new DoNothingAction();
    }

    /**
     * Trader will have a multitude of things that they can perform such as purchase
     * and selling.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new ExchangeAction(this, direction));
            actions.add(new SellAction(this,direction));
            // HINT 1: The AttackAction above allows you to attak the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
        }
        return actions;
    }

}
