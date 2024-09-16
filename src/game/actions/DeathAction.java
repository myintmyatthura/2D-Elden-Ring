package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.*;
import game.Consumables.Runes;
import game.Enum.Status;
import game.reset.ResetManager;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Wong Heng Yew
 * Myint Myat Thura
 *
 */
public class DeathAction extends Action {
    private Actor attacker;
    int smart_death_tracker = 0;
    Location location;
    List<Location> locationList = new ArrayList<>();
    List<Runes> rune_drop = new ArrayList<>();

    /***
     * Constructor for DeathAction
     * @param actor The actor who is carrying out the attack
     */

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        //map.locationOf(target).
        Player player = Player.getInstance();
        String result = "";
        ActionList dropActions = new ActionList();
        if (target.hasCapability(Status.MAIN_CHARACTER) || target.getDisplayChar() == '@') {
            Runes dRune = new Runes("Dropped Runes",'$',true);

            dRune.setRunes(player.returnTotalRunes());
            player.setDropped_runes(dRune);

            Location prev_location = player.getPrev_location();
            prev_location.addItem(dRune);

            ResetManager resetManager = ResetManager.getInstance();
            resetManager.deathResetforAll();

            if (player.returnCounter() >= 2) {
                for (Object r : player.getDropped_runes()) {
                        map.at(player.getDeathLocation().x(), player.getDeathLocation().y()).removeItem((Item) r);

                }
                map.at(player.getDeathLocation().x(), player.getDeathLocation().y()).removeItem(dRune);
                player.resetDeathCounter();
                player.setDeathLocation(prev_location);
                Runes runes = new Runes("Runes", '$', true);
                player.addRunes(runes);

            } else {
                player.resetDeathCounter();
                player.setDropped_runes(dRune);
                if (player.getSmart_death_tracker()<1){
                    //location = map.locationOf(target);
                    //player.setDeathLocation(location);
                    player.setDeathLocation(prev_location);
                    player.setSmartDeathTracker();

                }

            }


            // remove actor
        }


        if (target.hasCapability(Status.GRAVEYARD_ENEMY)){ //skeleton or bandit
            int randNum = RandomNumberGenerator.getRandomInt(0,100);
            if (randNum <= 50){
                String name;
                // keep the name for later identification
                if (target.getDisplayChar() == 'q'){
                    name = "Heavy Skeleton Swordsman";
                } else {
                    name = "Skeleton Bandit";
                }
                List<Item> items = target.getItemInventory();
                List<WeaponItem> weapons = target.getWeaponInventory();
                Location currentLocation = map.locationOf(target);
                map.removeActor(target); // once collected all the item and weapon from the target, remove it from map
                map.addActor(new PileOfBones(items,weapons),currentLocation);
                if (attacker.hasCapability(Status.MAIN_CHARACTER)){
                    map.getActorAt(currentLocation).addCapability(Status.HIT_BY_PLAYER); // others won't hit again
                } else {
                    map.getActorAt(currentLocation).addCapability(Status.HIT_BY_ENEMY); // others won't hit again
                }
                if (name.equals("Heavy Skeleton Swordsman")){
                    map.getActorAt(currentLocation).addCapability(Status.PREV_SWORDSMAN);
                } else {
                    map.getActorAt(currentLocation).addCapability(Status.PREV_BANDIT);
                }
                result +=  name + " turned to pile of bones";
                return result;
            }

        }
        if (target.getDisplayChar() == 'X'){ // when pile of bones is hit successfully
            if (attacker.hasCapability(Status.MAIN_CHARACTER)){
                target.addCapability(Status.HIT_BY_PLAYER); // others won't hit again
            } else {
                target.addCapability(Status.HIT_BY_ENEMY); // others won't hit again
            }
            result += "Tarnished hit pile of bones at this turn";
            return result;
        }


        // drop all items
        if(!(target.hasCapability(Status.MAIN_CHARACTER))) {
            if (attacker.hasCapability(Status.MAIN_CHARACTER)){
                if (target.getItemInventory().size() != 0){ //if inventory is not empty
                    // update runes amount from enemy to player runes amount
                    target.getItemInventory().get(0).tick(map.locationOf(attacker),target);
                    System.out.println("ENEMY IS NOW DEAD--------@@!@#!@#!@#!@#");
                }
            }
            for (WeaponItem weapon : target.getWeaponInventory()) // drop weapons
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            if (target.hasCapability(Status.HOSTILE_CREATURE) || target.hasCapability(Status.SUMMONED_ACTOR)) {
                map.removeActor(target);
            }
        }

        if(target.hasCapability(Status.MAIN_CHARACTER)){
            map.moveActor(target,map.at(player.getX(),player.getY()));

        }




        result += System.lineSeparator() + menuDescription(target);
        return result;

    }

    /***
     * Description of actor state
     * @param actor The target being killed
     * @return a String of target is killed message
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
    public void setLocation(Location loc){
        location = loc;
    }

}
