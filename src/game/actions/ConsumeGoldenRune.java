package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.Consumables.Runes;
import game.Consumables.RuneManager;
/**
 * Class that consumes a golden rune
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class ConsumeGoldenRune extends Action {

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
    public ConsumeGoldenRune(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }


    /**
     * When executed, it will add random number of runes to player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    public String execute(Actor actor, GameMap map) {
        Player player = Player.getInstance();
        RuneManager manage = RuneManager.getInstance();

        manage.consumeRunes(player);
        return"";
    }


    /**
     * Simple menu description
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume Golden Runes";
    }

}

