package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Status;
import game.utils.RandomNumberGenerator;
import game.actions.AttackAction;
import game.actions.SlamAttackAction;
import game.actions.SpinningAttackAction;

/***
 * The class that represents Attack Action for NPC hostile creatures.
 * Since NPC cannot choose which attack action, this class determines which attack to perform.
 * @author Wong Heng Yew
 */
public class AttackBehaviour implements Behaviour {

    /***
     * Priority rank for attack behaviour
     */
    public static final int BEHAVIOUR_PRIORITY = 1;


    /**
     * Checks surrounding and return various form of attack action or nothing
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return appropriate action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);      // current actor location

        boolean weaponLegit = false;

        for (Exit exit : here.getExits()) {

            Location destination = exit.getDestination();

            // checks if an actor is in exit
            if (map.isAnActorAt(destination)) {

                // pile of bones first
                Actor possiblePileOfBones = map.getActorAt(destination);

                if (possiblePileOfBones.hasCapability(Status.PILE_OF_BONES)){
                    if (actor.getWeaponInventory().size() == 0) {
                        return new AttackAction(possiblePileOfBones, exit.getName(), actor.getIntrinsicWeapon());
                    }else{
                        return new AttackAction(possiblePileOfBones, exit.getName(), actor.getWeaponInventory().get(0));
                    }

                }

                // the rest
                Actor otherActor = map.getActorAt(destination);

                char actorChar = actor.getDisplayChar();
                int randNum = RandomNumberGenerator.getRandomInt(0,100);

                // graveyard        : q(skeleton) & b(bandit)
                // gust of wind     : h(lone wolf) & G(giant dog)
                // puddle of water  : C(giant crab) & R(giant crayfish)
                // stormveil castle : a(Dog) & p(Godrick Soldier)

                if (actor.hasCapability(Status.GRAVEYARD_ENEMY)){
                    weaponLegit = false;

                    if (actor.getWeaponInventory().size() != 0) {
                        if ((actor.getWeaponInventory().get(0).getDisplayChar() == '?') && actorChar == 'q') {
                            weaponLegit = true;
                        }
                        if ((actor.getWeaponInventory().get(0).getDisplayChar() == 's') && actorChar == 'b') {
                            weaponLegit = true;
                        }
                        if (randNum <= 50 && weaponLegit) {
                            return new SpinningAttackAction();

                        } else {
                            return new AttackAction(otherActor, exit.getName(), actor.getWeaponInventory().get(0));
                        }
                    } else { // no weapon
                        if (!otherActor.hasCapability(Status.GRAVEYARD_ENEMY)){
                            return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());
                        }
                    }
                }
                if (actor.hasCapability(Status.GUST_OF_WIND_ENEMY)){

                    // lonewolf does attack only
                    // giantdog does slam (single) or slam area attack

                    if (actorChar == 'h' && !otherActor.hasCapability(Status.GUST_OF_WIND_ENEMY)) {
                        return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());

                    }
                    if (actorChar == 'G') {
                        if (randNum <= 50){
                            return new SlamAttackAction();
                        }
                        else if (!otherActor.hasCapability(Status.GUST_OF_WIND_ENEMY)){
                            return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());
                        }
                    }

                }

                if (actor.hasCapability(Status.PUDDLE_OF_WATER_ENEMY)){

                    // giant crab does slam (single) or slam area attack
                    // giant crayfish does slam (single0 or slam area attack

                    if (randNum <= 50){
                        return new SlamAttackAction();
                    }
                    else if (!otherActor.hasCapability(Status.PUDDLE_OF_WATER_ENEMY)){
                        return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());
                    }
                }

                if (actor.hasCapability(Status.STORMVEIL_CASTLE)){

                    // Dog and Godrick Soldier cannot attack each other
                    // Godrick Soldier does not have weapon at the moment

                    if (!otherActor.hasCapability(Status.STORMVEIL_CASTLE)){
                        return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());
                    }
                }

                if (actor.hasCapability(Status.SUMMONED_ACTOR)){

                    boolean attack = false;
                    weaponLegit = false;

                    // Ally cannot player but any other hostile enemies
                    // Invader can attack any hostile enemies

                    if (actor.getWeaponInventory().size() != 0){
                        weaponLegit = true;
                    }

                    if (actor.hasCapability(Status.FRIENDLY_SUMMON)){
                        if (!otherActor.hasCapability(Status.FRIENDLY_SUMMON) && !otherActor.hasCapability(Status.MAIN_CHARACTER)){ // if it isnt ally or player
                            attack = true;
                        }
                    }

                    if (actor.hasCapability(Status.EVIL_SUMMON)){
                        if (!otherActor.hasCapability(Status.EVIL_SUMMON)){ // if it is not invader
                            attack = true;
                        }
                    }

                    if (attack){
                        if (weaponLegit){
                            return new AttackAction(otherActor, exit.getName(), actor.getWeaponInventory().get(0));
                        } else {
                            return new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon());
                        }
                    }


                }
            }
        }
        return null;
    }

}
