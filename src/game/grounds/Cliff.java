package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Status;
import game.Player;
import game.actions.DeathAction;

/**
 * A class that represents Cliff
 * @author Wong Heng Yew
 */
public class Cliff extends Ground {

    /**
     * Constructor for Cliff class
     *
     */
    public Cliff() {
        super('+');
    }

    /**
     * Tick whatever is on Cliff
     * Calls isTherePlayer method to check for player presence
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (location.containsAnActor()){
            isTherePlayer(location);
        }
    }

    /**
     * Method that returns boolean value if actor with MAIN_CHARACTER capability can enter
     * @param actor the Actor to check
     * @return a boolean value if actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.MAIN_CHARACTER);
    }

    /**
     * method checks if Player is on Cliff
     * returns a death action or empty string
     * @param location Location of the Cliff
     * @return a String of the outcome whether Player is on Cliff
     */
    public String isTherePlayer(Location location){
        String result="";
        if (location.getActor().getDisplayChar() == '@'){
            Player player = Player.getInstance();
            player.hurt(player.getHp());
            result += new DeathAction(location.getActor()).execute(location.getActor(), location.map());
        }
        return result;

    }}
