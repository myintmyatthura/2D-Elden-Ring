package game.Summonings;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.CombatClass.ClassManager;
import game.Enum.Status;
import game.Player;
import game.TradingSystem.PurchaseAction;
import game.TradingSystem.SellAction;
import game.TradingSystem.Trader;
import game.TradingSystem.TradingManager;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.enemies.HostileCreature;
import game.gameWeapon.Club;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Scimitar;
import game.gameWeapon.Uchigatana;
import game.reset.ResetManager;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;
/**
 * Class that creates an Ally
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Actor,ResetManager,Resettable
 *
 */
public class Ally extends Actor implements Resettable {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public Ally() {
        super("Ally", 'A', 100);
        this.addCapability(Status.SUMMONED_ACTOR);
        this.addCapability(Status.FRIENDLY_SUMMON);
        this.addCapability(Status.DOES_NOT_FOLLOW_PLAYER);
        //this.addCapability(Status.HOSTILE_CREATURE);
        this.behaviours.put(WanderBehaviour.BEHAVIOUR_PRIORITY, new WanderBehaviour());
        this.behaviours.put(AttackBehaviour.BEHAVIOUR_PRIORITY, new AttackBehaviour());
        this.reset();
    }

    /**
     * Behavior of the ally which will attack everything as well as the player
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //reset();
        for (Behaviour behaviour : this.behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Getting the intrinsic weapon
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(77, "did friendly attack", 77);
    }
    /**
     * Registering the resettable to the class
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Ally added````````````````````````````````````````````");
    }

    /**
     * Starting the reset for when the player either dies or rests
     * @param map
     */
    @Override
    public void start_reset(GameMap map) {
        Player player = Player.getInstance();
            if (player.getHp() <= 0){
                ActorLocationsIterator locations = new ActorLocationsIterator();
                locations.locationOf(this);
                map.removeActor(this);
            }



    }

}

