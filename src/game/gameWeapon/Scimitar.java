package game.gameWeapon;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.Consumables.Runes;
import game.TradingSystem.TradingManager;
import game.TradingSystem.WeaponTrading;

/***
 * This represents Scimitar as weapon item
 * @author Wong Heng Yew
 */
public class Scimitar extends WeaponItem implements WeaponTrading {

    Runes my_runes;

    /***
     * Constructor for Scimitar with defined values
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "smithes", 88);
        TradingManager trader = TradingManager.getInstance();
        trader.registerTradables(this);
    }
    @Override
    public void purchase(Player player, int choice) {

            if (choice == 4 && player.returnTotalRunes() >= 600) {
//                player.addWeaponToInventory(new Club());
                player.playerDecreaseRunes(600);
                System.out.println("Tarnished currently has " + player.returnTotalRunes() + " left.");
                player.addWeaponToInventory(this);
            }
        }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 4){
            player.playerIncreaseRunes(100);
            System.out.println(player + " sold " + "Scimitar" + " to Trader" + " for 100");
            player.removeWeaponFromInventory(this);

        }
        return false;
    }
}
