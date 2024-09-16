package game.enemies;

import game.Enum.Status;

/***
 * This class represents enemy from graveyard
 * @author Wong Heng Yew
 */
public abstract class GraveyardEnemy extends HostileCreature {
    /**
     * Constructor for graveyard enemy.
     * Add GRAVEYARD_ENEMY status
     * Set runes amount for graveyard enemy type as two current graveyard enemies have same range
     * Add the runes to item inventory
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public GraveyardEnemy(String name, char displayChar, int hitPoints, int chance) {
        super(name, displayChar, hitPoints,chance);
        this.addCapability(Status.GRAVEYARD_ENEMY);
        super.setRunes(35,892);
        addItemToInventory(getRunes());

    }

}
