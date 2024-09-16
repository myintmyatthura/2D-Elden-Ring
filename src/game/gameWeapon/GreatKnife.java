package game.gameWeapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.Consumables.Runes;
import game.TradingSystem.TradingManager;
import game.TradingSystem.WeaponTrading;

public class GreatKnife extends WeaponItem implements WeaponTrading {

    String name;

    Runes my_runes;
    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '!', 103, "slashes", 80);
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

            if (choice == 2 && player.returnTotalRunes() >= 3500) {
//                player.addWeaponToInventory(new Club());
                player.playerDecreaseRunes(3500);
                System.out.println("Tarnished currently has " + player.returnTotalRunes() + " left.");
                player.addWeaponToInventory(this);
            }
        }


    @Override
    public boolean sell(Player player, int amount) {
        if (amount == 2){
            player.playerIncreaseRunes(350);
            System.out.println(player + " sold " + "Great Knife" + " to Trader" + " for 350");
            player.removeWeaponFromInventory(this);

        }
        return false;
    }
}



