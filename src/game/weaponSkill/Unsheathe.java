package game.weaponSkill;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Enum.Status;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.enemies.HostileCreature;
import game.gameWeapon.Uchigatana;
import java.util.Random;
import java.util.List;

public class Unsheathe extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Uchigatana uchigatana;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    private Weapon weapon;

    /**
     * The chance to activate this skill
     */
    private static final int chanceToHit = 60;


    /**
     * Constructor.
     *
     * @param target     the Actor to attack
     * @param direction  the direction where the attack should be performed (only used for display purposes)
     * @param uchigatana the uchigatana used
     */
    public Unsheathe(Actor target, String direction, Uchigatana uchigatana) {
        this.target = target;
        this.direction = direction;
        this.uchigatana = uchigatana;
    }



    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.HOSTILE_CREATURE)){

        }

        if(actor.hasCapability(Status.HOSTILE_CREATURE)){
        }
        // Use the uchigatana weapon to deal damage
        if (target.hasCapability(Status.HIT_BY_ENEMY) || target.hasCapability(Status.HIT_BY_PLAYER)){
            return "successfully hit!";
        }
        if (!(rand.nextInt(100) <= uchigatana.chanceToHit())) {
            return actor + " misses " + target + ".";
        }
        int damage = uchigatana.damage();
        String result = actor + " " + uchigatana.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }
        return result;
    }




    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses Unsheathe " + "on " + target;
    }
}