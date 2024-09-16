package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Enum.Status;
import game.Player;
import game.Consumables.Runes;

import java.util.List;

public class ConsumeFlask extends Action {

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
    public ConsumeFlask(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }


    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !(actor.hasCapability(Status.HOSTILE_CREATURE))) {
            Player player = Player.getInstance();
            // getting player's inventory
            List<Item> inventory = player.getItemInventory();
            if (player.returnFlask().getFlasks() >0){
                System.out.println("You have consumed a flask");
                player.increaseHP(250);
                player.returnFlask().decreaseFlasks();
                System.out.println("You have "+player.returnFlask().getFlasks()+ " flasks left.");
            }
            else{
                System.out.println("No more flasks");
            }
        }
        else{
            return "No flasks was consumed";
        }
        return "";
    }


    @Override
    public String menuDescription(Actor actor) {
        return "Consume Flask";
    }

}

