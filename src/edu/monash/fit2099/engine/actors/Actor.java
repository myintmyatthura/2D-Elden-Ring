package edu.monash.fit2099.engine.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.capabilities.Capable;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An entity that is alive by having hit points. It also holds inventory that stores items.
 */
public abstract class Actor implements Capable, Printable {

	/**
	 * Actor's name
	 */
	protected final String name;
	/**
	 * Health
	 */
	protected int hitPoints;
	/**
	 * Maximum health
	 */
	protected int maxHitPoints;
	/**
	 * A character that will be displayed on the terminal/console
	 */
	private char displayChar;
	/**
	 * A bag of items
	 */
	private final List<Item> itemInventory = new ArrayList<>();
	/**
	 * Current capabilities/statuses
	 */
	private final CapabilitySet capabilitySet = new CapabilitySet();
	/**
	 * A weapon inventory, making it possible to have more than two active weapons
	 * (not limited to right-hand and left-hand weapons)
	 */
	private List<WeaponItem> weaponInventory = new ArrayList<>();


	/**
	 * Constructor.
	 * @param name the name of the Actor
	 * @param displayChar the character that will represent the Actor in the display
	 * @param hitPoints the Actor's starting hit points
	 */
	public Actor(String name, char displayChar, int hitPoints) {
		this.name = name;
		this.displayChar = displayChar;
		this.resetMaxHp(hitPoints);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	/**
	 * Replace current display character
	 * @param displayChar one character that will be displayed in the terminal/console
	 */
	protected final void setDisplayChar(char displayChar){
		this.displayChar = displayChar;
	}

	/**
	 *
	 * @return Maximum hit points of this actor
	 */
	protected final int getMaxHp(){
		return maxHitPoints;
	}

	/**
	 *
	 * @return (hitpoints and maximum hitpoints)
	 */
	protected final String printHp(){
		return "(" + hitPoints + "/" + maxHitPoints + ")";
	}

	/**
	 * Add an item to this Actor's inventory.
	 * @param item The Item to add.
	 */
	public void addItemToInventory(Item item) {
		itemInventory.add(item);
	}

	/**
	 * Remove an item from this Actor's inventory.
	 * @param item The Item to remove.
	 */
	public void removeItemFromInventory(Item item) {
		itemInventory.remove(item);
	}

	/**
	 * Get a copy of this Actor's inventory list. 
	 * @return An unmodifiable wrapper of the inventory.
	 */
	public List<Item> getItemInventory() {
		return Collections.unmodifiableList(itemInventory);
	}

	/**
	 * Add a weapon to this Actor's inventory
	 * @param weapon The weapon to be added
	 */
	public void addWeaponToInventory(WeaponItem weapon) {
		weaponInventory.add(weapon);
	}

	/**
	 * Remove a weapon from this Actor's inventory
	 * @param weapon The weapon to be removed
	 */
	public void removeWeaponFromInventory(WeaponItem weapon) {
		weaponInventory.remove(weapon);
	}

	/**
	 * Get a copy of this Actor's weapon list
	 * @return An unmodifiable wrapper of the weapon inventory
	 */
	public List<WeaponItem> getWeaponInventory() {
		return Collections.unmodifiableList(weaponInventory);
	}

	/**
	 * Select and return an action to perform on the current turn. 
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

	/**
	 * Returns a new collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		return new ActionList();
	}

	/**
	 * Is this Actor conscious?
	 * Returns true if the current Actor has positive hit points.
	 * Actors on zero hit points are deemed to be unconscious.
	 * 
	 * Depending on the game client, this status may be interpreted as either 
	 * unconsciousness or death, or inflict some other kind of status.
	 *
	 * @return true if and only if hitPoints is positive.
	 */
	public boolean isConscious() {
		return hitPoints > 0;
	}

	/**
	 * Increases maximum hit points and set current hit points to that new maximum hit points.
	 *
	 * @param points number of points that is added to maximum health
	 */
	public void increaseMaxHp(int points){
		maxHitPoints += points;
		hitPoints = maxHitPoints;
	}

	/**
	 * reset maximum hit points and set current hit points to that new max hit points.
	 * @param hitPoints integer value of new maximum hit points
	 */
	public void resetMaxHp(int hitPoints){
		this.maxHitPoints = hitPoints;
		this.hitPoints = hitPoints;
	}

	/**
	 * Add points to the current Actor's hitpoint total.
	 *
	 * This cannot take the hitpoints over the Actor's maximum. If there is an
	 * overflow, hitpoints are silently capped at the maximum.
	 *
	 * Does not check for consciousness: unconscious Actors can still be healed
	 * if the game client allows.
	 *
	 * @param points number of hitpoints to add.
	 */
	public void heal(int points) {
		hitPoints += points;
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}

	/**
	 * Do some damage to the current Actor.
	 *
	 * If the Actor's hitpoints go down to zero, it will be knocked out.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	public void hurt(int points) {
		hitPoints -= points;
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with more interesting descriptions and/or different damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5, "punches");
	}

	/**
	 * Returns true if and only if the current Actor has the required capability.
	 *
	 * @param capability the capability required
	 * @return true if and only if the current Actor has the required capability
	 */
	public boolean hasCapability(Enum<?> capability) {
		for (Item item : itemInventory) {
			if (item.hasCapability(capability)) {
				return true;
			}
		}
		for (WeaponItem weapon : weaponInventory) {
			if (weapon.hasCapability(capability)) {
				return true;
			}
		}
		return capabilitySet.hasCapability(capability);
	}

	/**
	 * Add a capability to this Actor.
	 * 
	 * @param capability the Capability to add
	 */
	public void addCapability(Enum<?> capability) {
		capabilitySet.addCapability(capability);
	}

	/** Remove a capability from this Actor.
	 * 
	 * @param capability the Capability to remove
	 */
	public void removeCapability(Enum<?> capability) {
		capabilitySet.removeCapability(capability);
	}

	/**
	 * Get unmodifiable capabilities
	 *
	 * @return a list of unmodifiable capabilities
	 */
	public List<Enum<?>> capabilitiesList() { return capabilitySet.capabilitiesList();	}

	/**
	 * Get unmodifiable capabilities by a specific enum type
	 *
	 * @param enumType Class type, to be filtered later
	 * @param <T> any enumeration type
	 * @return list of enums based on type, empty list if type is not in the set.
	 */
	public <T extends Enum<?>> List<T> findCapabilitiesByType(Class<T> enumType){
		return capabilitySet.findCapabilitiesByType(enumType);
	}
}
