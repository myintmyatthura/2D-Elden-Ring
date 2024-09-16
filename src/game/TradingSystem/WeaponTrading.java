package game.TradingSystem;

import game.Player;
/**
 * interface to handle trading action
 * Created by:
 * @author Myint Myat Thura
 * @version 2.0
 * @see Trader,game.Player,TradingManager
 *
 */
public interface WeaponTrading {
    void purchase(Player player, int amount);
    boolean sell(Player player, int amount);
}
