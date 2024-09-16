package game.Summonings;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.CombatClass.ClassManager;
import game.Consumables.Runes;
import game.Enum.Status;
import game.Player;
import game.enemies.HostileCreature;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;
/**
 * Class that creates an Ally
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Actor,ResetManager,Resettable
 *
 */
public class Invader extends HostileCreature implements Resettable {
    public Invader() {
        super("Invader", 'ඞ',100, 0);
        this.addCapability(Status.SUMMONED_ACTOR);
        this.addCapability(Status.EVIL_SUMMON);
        Runes invRune = new Runes("Invader Runes",'$', true);
        int randNum = RandomNumberGenerator.getRandomInt(1358, 5578);
        invRune.setRunes(randNum);
        this.addItemToInventory(invRune);
        this.reset();
        }


    /**
     * Getting the intrinsic weapon
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(44, "invades", 66);
    }

    /**
     * Registering the resettable to the class
     */
    @Override
    public void reset() {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.registerResettable(this);
        System.out.println("Invader added```````````````````````````````");
    }

    /**
     * Starting the reset for when player either dies or rests
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
