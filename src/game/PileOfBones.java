package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Enum.Status;
import game.actions.AttackAction;
import java.util.List;

/***
 * This represents Pile of Bones: an actor after the death of heavy skeleton swordsman
 * @author Wong Heng Yew
 */
public class PileOfBones extends Actor {

    /***
     * List of item
     */
    private List<Item> itemInventory;

    /***
     * List of weapon item
     */
    private List<WeaponItem> weaponInventory;

    /***
     * Constructor for Pile of Bones
     *
     * @param items list of item from previous graveyard enemy
     * @param weapons list of weapon item from previous graveyard enemy
     */
    public PileOfBones(List<Item> items, List<WeaponItem> weapons) {

        super("Pile of Bones",'X',0);
        itemInventory = items;
        weaponInventory = weapons;
        addItemsAndWeapons();
        this.addCapability(Status.PILE_OF_BONES);


    }

    /***
     * Method that returns an action every time pile pf bones is called
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a new instance of DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /***
     * Method that gives attacker possible actions to be performed on the pile of bones
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of possible actions for attacker
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();

        if (otherActor.getWeaponInventory().size() != 0) {
            actions.add(new AttackAction(this, direction, otherActor.getWeaponInventory().get(0)));
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /***
     * Method that store item and weapons to inventory through iterating the given lists
     */
    public void addItemsAndWeapons(){
        for (Item item: itemInventory){
            this.addItemToInventory(item);
        }
        for (WeaponItem weapon:weaponInventory){
            this.addWeaponToInventory(weapon);
        }
    }
}
