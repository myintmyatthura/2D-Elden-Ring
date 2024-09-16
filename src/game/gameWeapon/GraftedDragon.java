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
public class GraftedDragon extends WeaponItem implements WeaponTrading {

    String name;
    Runes my_runes;
    /**
     * Constructor
     */
    public GraftedDragon() {

        super("Grafted Dragon", '!', 103, "bonks", 80);
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

        if (choice == 6 && player.getItemInventory().size()>=1) {
//                player.addWeaponToInventory(new Club());
            player.addWeaponToInventory(this);

        }
    }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 5){
            player.playerIncreaseRunes(1000);
            System.out.println(player + " sold " + "Grafted Dragon" + " to Trader" + " for 100");
            player.removeWeaponFromInventory(this);

        }
        if (amount == 7&& player.getItemInventory().size()>=2){
            player.playerIncreaseRunes(10000);
        }
        return false;
    }
}
