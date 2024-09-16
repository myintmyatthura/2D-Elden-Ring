package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Consumables.Runes;
import game.Enum.Status;
import game.Player;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Uchigatana;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;
import game.weaponSkill.Quickstep;
import game.weaponSkill.Unsheathe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/***
 * This abstract class represents base detail of enemy in the game
 * The hostile creature is an actor and can be reset
 * @author Wong Heng Yew
 */
public abstract class HostileCreature extends Actor implements Resettable {

    /***
     * Map of behaviours for play turn
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /***
     * Integer representation of spawning chance
     */
    private int chance;

    /***
     * Runes object
     */
    private Runes runes = new Runes("Runes",'r',true);
    /**
     * Constructor of a hostile creature
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param chance      the Actor's chance of spawning
     */
    public HostileCreature(String name, char displayChar, int hitPoints, int chance) {
        super(name, displayChar, hitPoints);
        setSpawningChance(chance);
        this.addCapability(Status.DOES_NOT_FOLLOW_PLAYER);
        this.addCapability(Status.HOSTILE_CREATURE);
        this.behaviours.put(WanderBehaviour.BEHAVIOUR_PRIORITY, new WanderBehaviour());
        this.behaviours.put(AttackBehaviour.BEHAVIOUR_PRIORITY, new AttackBehaviour());

    }

    /***
     * Method that loop through hostile creature behaviours
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return appropriate action from behaviour or DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        reset();
        for (Behaviour behaviour : this.getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /***
     * ActionList of attack action that can be performed on the hostile creature
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return action list of allowable actions on the hostile creature
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) { // includes player only
            if (otherActor.getDisplayChar() == '@'&& this.getDisplayChar() != 'A') { // if it is a player Tarnished and not Ally
                this.behaviours.put(FollowBehaviour.BEHAVIOUR_PRIORITY, new FollowBehaviour(otherActor));
            }
            if (otherActor.getWeaponInventory().size() != 0) {
                for (WeaponItem weapon: otherActor.getWeaponInventory()){
                    actions.add(new AttackAction(this, direction, weapon));
                }
            }
            actions.add(new AttackAction(this, direction));
            Player player = Player.getInstance();
            if (Objects.equals(player.getChosenClass(), "Samurai") || Objects.equals(player.getChosenClass(), "Archeologist")) {
                actions.add(new Unsheathe(this, direction, new Uchigatana()));
            }
            else if (player.getChosenClass() == "Bandit")
                actions.add(new Quickstep(this, direction, new GreatKnife()));
        }

        return actions;
    }

    /***
     * Get runes
     * @return runes
     */
    public Runes getRunes(){
        return this.runes;
    }

    /***
     * Set Runes amount based on lower and upper bound
     * @param lowerBound inclusive lower bound
     * @param upperBound inclusive upper bound
     */
    public void setRunes(int lowerBound,int upperBound){
        int randNum = RandomNumberGenerator.getRandomInt(lowerBound,upperBound);
        runes.setRunes(randNum);
    }

    /***
     * Get spawning chance of hostile creature
     * @return spawning chance in int
     */
    public int getSpawningChance() {
        return this.chance;
    }

    /***
     * Set spawning chance of the hostile creature
     * @param chance spawning chance in int
     */
    public void setSpawningChance(int chance){
        this.chance = chance;
    }
    public abstract void reset();

    /***
     * Get hash map of behaviours
     * @return map of behaviours
     */
    public Map<Integer, Behaviour> getBehaviours(){
        return this.behaviours;
    }


}
