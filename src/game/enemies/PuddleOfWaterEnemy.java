package game.enemies;

import game.Enum.Status;

/***
 * This class represents enemy from puddle of water
 * @author Wong Heng Yew
 */
public abstract class PuddleOfWaterEnemy extends HostileCreature{
    /**
     * Constructor for enemy from puddle of water
     * Add PUDDLE_OF_WATER status
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public PuddleOfWaterEnemy(String name, char displayChar, int hitPoints,int chance) {
        super(name, displayChar, hitPoints,chance);
        this.addCapability(Status.PUDDLE_OF_WATER_ENEMY);
    }

}
