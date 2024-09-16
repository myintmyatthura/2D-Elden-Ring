package game.gameWeapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.Consumables.Runes;
import game.TradingSystem.TradingManager;
import game.TradingSystem.WeaponTrading;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem implements WeaponTrading {

    String name;
    Runes my_runes;
    /**
     * Constructor
     */
    public Club() {

        super("Club", '!', 103, "bonks", 80);
        TradingManager trader = TradingManager.getInstance();
        trader.registerTradables(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
    }

    public String getName() {
        return name;
    }

    @Override
    public void purchase(Player player, int choice) {

            if (choice == 3 && player.returnTotalRunes() >= 600) {
//                player.addWeaponToInventory(new Club());
                player.playerDecreaseRunes(600);
                System.out.println("Tarnished currently has " + player.returnTotalRunes() + " left.");
                player.addWeaponToInventory(this);
            }
        }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 3){
            player.playerIncreaseRunes(100);
            System.out.println(player + " sold " + "Club" + " to Trader" + " for 100");
            player.removeWeaponFromInventory(this);

        }
        return false;
    }
}
