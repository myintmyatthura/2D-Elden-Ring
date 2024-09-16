package game.Consumables;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;
import game.Enum.Status;
import game.Player;
/**
 * Class that creates Remembrance
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class Remembrance extends Item implements RuneHandler {
    String name;
    int amount;
    char displayChar;
    boolean portable;

    /**
     * Constructor
     */
    public Remembrance() {
        super("Rememberance",'O',true);

    }
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (currentLocation.getActor().hasCapability(Status.MAIN_CHARACTER)){ // aka the player
            RuneManager manage = RuneManager.getInstance();
            manage.registerRunes(this);


            //runes+=1;
        }
        //super.tick(currentLocation, actor);
    }

    /**
     * After every use, it must be removed from the inventory of the player
     * @param player
     * @param clarification
     */
    @Override
    public void action(Player player, String clarification) {
            if (clarification.equals("rem")){
                player.removeItemFromInventory(this);
            }
        }


}
