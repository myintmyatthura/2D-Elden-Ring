package game.Consumables;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;
import game.Enum.Status;

public class FlaskOfCrimsonTears extends Item{
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
    public FlaskOfCrimsonTears(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.name = name;
        this.displayChar = displayChar;
        this.portable = portable;
        this.addCapability(Status.HEALING);
    }

    public int getFlasks(){
        return amount;
    }
    public void decreaseFlasks(){
        amount -=1;
    }
    public void increaseFlasks(){
        amount += 1;
    }
    public void setFlask(int amount){
        this.amount = amount;
    }
    public void resetFlask(){
        this.amount = 2;
    }

    public char getDisplayChar() {
        return displayChar;
    }
    public boolean isPortable(){
        return portable;
    }


}
