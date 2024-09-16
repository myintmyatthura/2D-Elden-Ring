package game.TradingSystem;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Consumables.Runes;
import game.Consumables.RuneManager;
import game.enemies.HostileCreature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Trading manager that handles the trading action using interfaces and polymorphism
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Trader,game.Player,TradingManager
 *
 */
public class TradingManager {
    private List<WeaponTrading> tradables;

    private static TradingManager instance;


    /**
     * Trading manager that follows the same logic as ResetManager
     */
    private TradingManager() {
        this.tradables = new ArrayList<>();
    }

    public static TradingManager getInstance() {
        if (instance == null) {
            instance = new TradingManager();
        }
        return instance;
    }

    /**
     * Performs purchase action on all the tradable objects
     * @param choice
     */
    public void purchase(int choice) {

        Player player = Player.getInstance();
        for (WeaponTrading i:tradables){
            i.purchase(player,choice);
            if (choice == 4 || choice == 5){
                RuneManager rune = RuneManager.getInstance();
            }
        }



    }

    /**
     * Performs sell action on every tradable
     * @param choice
     */
    public void sell(int choice) {
        Player player = Player.getInstance();
        for (WeaponTrading i:tradables){
                if(i.sell(player, choice)){
                    if (choice >=4){
                        RuneManager rune = RuneManager.getInstance();
                    }
                    break;


            }

        }



    }

    /**
     * Registers every weapon as tradables
     * @param tradables_1
     */
    public void registerTradables(WeaponTrading tradables_1) {
        tradables.add(tradables_1);
    }
}