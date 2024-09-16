package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Myint Myat Thura
 * @version 2.0
 * @see ResetManager,GameMap,GameReset
 *
 */
public interface Resettable {
    /**
     * Simple reset method to be implemented by actor classes
     */
    void reset();

    /**
     * Initializes the reset action
     * @param map
     */
    void start_reset(GameMap map);
}
