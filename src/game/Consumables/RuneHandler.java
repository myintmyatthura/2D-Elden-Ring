package game.Consumables;

import edu.monash.fit2099.engine.actors.Actor;
import game.Player;
/**
 * Interface to handle all the runes
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player, Actor ,game.reset.Resettable
 *
 */
public interface RuneHandler {
    void action(Player player, String clarification);
}
