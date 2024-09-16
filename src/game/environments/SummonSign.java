package game.environments;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Status;
import game.FancyMessage;
import game.actions.SummonAction;
import game.reset.GameReset;
/**
 * Class that creates a summon sign ground
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class SummonSign extends Ground{
    public SummonSign(){
        super('=');
        addCapability(Status.MAIN_CHARACTER);
    }

    /**
     * Adding allowable actions if the interactor is a player
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if(actor.getDisplayChar() =='@'){
            //actions.add(new GameReset(actor,direction));
            actions.add(new SummonAction(actor,direction));

        }

        return actions;
    }
}
