package game.Consumables;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Enum.Status;

import game.Player;
import game.utils.RandomNumberGenerator;
/**
 * Class that creates Golden Runes
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,RuneHandler,Item
 *
 */
public class GoldenRunes extends Item implements RuneHandler {
    String name;
    int amount;
    char displayChar;
    boolean portable;

    /***
     * Constructor.
     * @param name the amount of runes to set
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public GoldenRunes(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.name = name;
        this.displayChar = displayChar;
        this.portable = portable;
        this.addCapability(Status.BUFF);
        RuneManager manage = RuneManager.getInstance();
        manage.registerRunes(this);
    }


    /**
     * Returns simple display char
     * @return
     */
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Generating simple random runes from a given range
     * @param player
     * @param clarification
     */
    @Override
    public void action(Player player, String clarification) {
        // getting player's inventory
        if (clarification.equals("gold")){
            System.out.println("You have consumed a Golden Rune");
            int randNum = RandomNumberGenerator.getRandomInt(200, 100000);
            player.addRunes(randNum);
            player.removeItemFromInventory(this);

        }


    }
}
