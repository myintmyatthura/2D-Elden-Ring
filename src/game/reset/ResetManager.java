package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.enemies.HostileCreature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Myint Myat Thura
 * @version 2.0
 * @see Resettable,ResetManager,GameMap
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private List<Player> player;
    private static ResetManager instance;
    static GameMap map;
    static Location location;
    Location actor_location;
    int max_hp;
    Set<HostileCreature> set1 = new HashSet<>();

    /**
     * Constructor to implement singleton
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Getting the instance to implement singleton
     *
     * @return
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Adding the map to save the starting location of player and respawn
     * location if new sites of grace were to be visited
     *
     * @param map_1
     */
    public void addMap(GameMap map_1) {
        map = map_1;
    }

    /**
     * This is the site of grace reset where everything is the same as death
     * Except we do not drop runes and move the actor to other places
     */
    public void run() {
        // if the instance is a player, then we reset maxHP and reset flask
        Player n_player = Player.getInstance();
        actor_location = map.locationOf(n_player);
        n_player.setStartingLocation(actor_location.x(), actor_location.y());
        n_player.resetMaxHp(max_hp);
        n_player.returnFlask().resetFlask();


        for (Resettable i : resettables) {
            if (i != null) {
                i.start_reset(map);
            }


        }


    }
    public void deathResetforAll(){
        for (Resettable i : resettables) {
            if (i != null) {
                i.start_reset(map);
            }


        }
    }

    /**
     * Does not drop anything
     */
    public void death() {
        Player n_player = Player.getInstance();
        n_player.resetMaxHp(max_hp);
        n_player.returnFlask().resetFlask();
    }

    /**
     * Setting max Hp for player after game reset
     *
     * @param max
     */

    public void setMax_hp(int max) {
        this.max_hp = max;
    }


    /**
     * Adding location of the player
     *
     * @param loc
     */
    public void addLoc(Location loc) {
        location = loc;
    }


    /**
     * Registering resettables
     *
     * @param resettable
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }
}


