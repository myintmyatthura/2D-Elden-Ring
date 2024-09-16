package game.gameWeapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Consumables.RuneManager;
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
public class AxeOfGodrick extends WeaponItem implements WeaponTrading {

    String name;
    Runes my_runes;
    /**
     * Constructor
     */
    public AxeOfGodrick() {

        super("Axe Of Godrick", '!', 103, "bonks", 80);
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

        if (choice == 5&& player.getItemInventory().size()>=1) {
//                player.addWeaponToInventory(new Club());
            player.addWeaponToInventory(this);
            RuneManager manage = RuneManager.getInstance();
            manage.rem(player);

        }
    }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 5){
            player.playerIncreaseRunes(1000);
            System.out.println(player + " sold " + "Axe of Godrick" + " to Trader" + " for 100");
            player.removeWeaponFromInventory(this);

        }
        return false;
    }
}
