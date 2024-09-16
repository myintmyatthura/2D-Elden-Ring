package game.Consumables;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;
import game.Enum.Status;
import game.Player;
/**
 * Class that creates Runes
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class Runes extends Item implements RuneHandler {
    String name;
    int amount;
    char displayChar;
    boolean portable;
    /***
     * Constructor.
//     * @param amount the amount of runes to set
//     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Runes(String name, char displayChar,boolean portable) {
        super(name,displayChar,portable);
        this.name = name;
        this.displayChar = displayChar;
        this.portable = portable;
        this.addCapability(Status.RUNE);

    }

    /**
     * Returns the number of runes
     * @return
     */
    public int getRunes(){
        return amount;
    }

    /**
     * Sets the number of runes to start with
     * @param amount_r
     */
    public void setRunes(int amount_r){
        this.amount = amount_r;
    }

    /**
     * Gets display character
     * @return
     */
    @Override
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Simple tick method
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (currentLocation.getActor().hasCapability(Status.MAIN_CHARACTER)){ // aka the player
            Player player = Player.getInstance();
                RuneManager manage = RuneManager.getInstance();
                manage.registerRunes(this);


            //runes+=1;
        }
        //super.tick(currentLocation, actor);
    }

    /**
     * Adds the rune amount to inventory then removes the object
     * @param player
     * @param clarification
     */
    @Override
    public void action(Player player, String clarification) {
        if (clarification.equals("rune")){
            player.addRunes(this.getRunes());
            this.setRunes(0);
            player.removeItemFromInventory(this);
        }

    }



}
