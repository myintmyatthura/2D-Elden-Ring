package game.gameWeapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.Consumables.Runes;
import game.TradingSystem.TradingManager;
import game.TradingSystem.WeaponTrading;

public class Uchigatana extends WeaponItem implements WeaponTrading {

    String name;
    Runes my_runes;
    /**
     * Constructor
     */
    public Uchigatana() {


        super("Uchigatana", '!', 103, "shrings", 80);
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
            if (choice == 1 && player.returnTotalRunes() >= 5000) {
//                player.addWeaponToInventory(new Club());
                player.playerDecreaseRunes(5000);
                System.out.println("Tarnished currently has " + player.returnTotalRunes() + " left.");
                player.addWeaponToInventory(this);
            }
        }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 1){
            player.playerIncreaseRunes(500);
            System.out.println(player + " sold " + "Uchigatana" + " to Trader" + " for 500");
            player.removeWeaponFromInventory(this);
            return true;

        }
        return false;
    }
}

