package game.environments;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Status;
import game.FancyMessage;
import game.reset.GameReset;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * Site of Lost Grace
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Ground,game.Player
 *
 */
public class SiteOfLostGrace extends Ground {

    private boolean isDiscovered;
    /**
     * Constructor.
     *
     *  character to display for this type of terrain
     */
    public SiteOfLostGrace() {
        super('U');
        addCapability(Status.MAIN_CHARACTER);
        this.isDiscovered = false;

    }

    /**
     * Allows the player to reset the game with a welcome message
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if(actor.getDisplayChar() == '@'){
            if (!isDiscovered){
                this.isDiscovered = true;
                for (String line : FancyMessage.LOST_GRACE_DISCOVERED.split("\n")) {
                    new Display().println(line);
                    try {
                        Thread.sleep(200);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            actions.add(new GameReset(actor,direction));
            System.out.println("Welcome to site of Grace");

        }
        return actions;
    }

}
