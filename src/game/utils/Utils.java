package game.utils;

import edu.monash.fit2099.engine.positions.Location;
import game.Enum.Direction;

/***
 * This represents static functions that do calculation
 * @author Wong Heng Yew
 */
public class Utils {

    /***
     * Method that calculates the direction of a location based on map width
     * @param location location of a ground
     * @return direction of location in enum Direction
     */
    public static Direction direction(Location location) {
        Direction output;
        location.x();

        int half_width = (location.map().getXRange().max()) / 2; // 7/2 gives 3 8/2 gives 4

        if (location.x() > half_width){
            output = Direction.EAST;
        } else {
            output = Direction.WEST;

        }
        return output;
    }

}
