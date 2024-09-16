package game.TradingSystem;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.conversation.ConvoAction;
import game.Enum.Status;
import game.gameWeapon.Club;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Scimitar;
import game.gameWeapon.Uchigatana;

/**
 * Class that performs the trading action
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Trader,game.Player,TradingManager
 *
 */
public class Trader extends Actor{


    /**
     * Setting up a trader, for this case this can be left as Kale because we
     * only have one trader, in the future this can be removed and Trader can be
     * set indirectly.
     */
    public Trader() {
        super("Kale", 'K',100);
        this.addCapability(Status.RESTING);
        Club club = new Club();

        GreatKnife greatKnife = new GreatKnife();

        Uchigatana uchigatana = new Uchigatana();

        Scimitar scimitar = new Scimitar();

        this.addWeaponToInventory(scimitar);
        this.addWeaponToInventory(uchigatana);
        this.addWeaponToInventory(greatKnife);
        this.addWeaponToInventory(club);

    }

    /**
     * Trader does not do anything
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return returns an action
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
     * @return an action list with possible actions to be carried out
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new PurchaseAction(this, direction));
            actions.add(new SellAction(this,direction));
        }

        if(otherActor.hasCapability(Status.TALKING)){
            actions.add(new ConvoAction(this,direction));
        }

        return actions;
    }

}
