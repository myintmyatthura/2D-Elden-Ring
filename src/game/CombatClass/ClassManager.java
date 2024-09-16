package game.CombatClass;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Consumables.Runes;
import game.Consumables.RuneManager;
import game.enemies.HostileCreature;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that handles all the classes using singleton
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see game.Player,Actor,game.reset.Resettable
 *
 */
public class ClassManager {
    private List<ClassHandler> classList;
    private List<Player> player;
    private static ClassManager instance;

    /**
     * Private constructor
     */
    private ClassManager() {
        this.classList = new ArrayList<>();
    }

    /**
     * Returns an instance of the class
     * @return
     */
    public static ClassManager getInstance() {
        if (instance == null) {
            instance = new ClassManager();
        }
        return instance;
    }

    /**
     * Choosing a random class for those like ally and trader
     * @param actor
     */
    public void chooseRandomClass(Actor actor){
        int randNum = RandomNumberGenerator.getRandomInt(1,4);
        for (ClassHandler i:classList){
            i.chooseClass(actor,randNum);
        }
    }

    /**
     * Choosing a class for the player
     * @param player
     * @param choice
     */
    public void choosePlayerClass(Player player, int choice){
        for (ClassHandler i:classList){
            int maxHP = i.chooseClass(player,choice);
            String chosen_class = i.chosenClass();
            player.max_hp = maxHP;
            player.chosen = true;
            player.chosenClass = chosen_class;

        }
    }

    /**
     * Registering the classes into the arraylist
     * @param classes
     */
    public void registerClasses(ClassHandler classes) {
        classList.add(classes);
    }
}