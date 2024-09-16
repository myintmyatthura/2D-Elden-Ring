package game.Consumables;

import edu.monash.fit2099.engine.actors.Actor;
import game.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all the runes
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player
 *
 */
public class RuneManager {
    private List<RuneHandler> items;
    private static RuneManager instance;

    /**
     * Rune manager that implements singleton
     */
    private RuneManager() {
        this.items = new ArrayList<>();
    }

    /**
     * Returns an instance of the class
     * @return
     */
    public static RuneManager getInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Adding runes to the player and remembrance
     * @param player
     */
    public void runeAdd(Player player){
        for (RuneHandler i:items){
            i.action(player,"rune");
            }



        }
        public void rem(Player player){
        for (RuneHandler i:items){
            i.action(player,"rem");
        }
        }

    /**
     * Consuming runes
     * @param player
     */
    public void consumeRunes(Player player){
        for (RuneHandler i:items){
            i.action(player,"gold");
            break;
        }

    }


    /**
     * Registers every item
     *
     */
    public void registerRunes(RuneHandler rune_1) {
        items.add(rune_1);
    }

}