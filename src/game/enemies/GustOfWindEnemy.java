package game.enemies;

import game.Enum.Status;

/***
 * This class represents enemy from gust of wind
 * @author Wong Heng Yew
 */
public abstract class GustOfWindEnemy extends HostileCreature{
    /**
     * Constructor for gust of wind enemy
     * Add GUST_OF_WIND status
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public GustOfWindEnemy(String name, char displayChar, int hitPoints,int chance) {
        super(name, displayChar, hitPoints,chance);
        this.addCapability(Status.GUST_OF_WIND_ENEMY);

    }
}
