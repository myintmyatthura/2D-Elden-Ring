package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.CombatClass.ClassManager;
import game.Consumables.FlaskOfCrimsonTears;
import game.Consumables.RuneManager;
import game.Consumables.Runes;
import game.Enum.Status;
import game.actions.ConsumeFlask;
import game.actions.ConsumeGoldenRune;
import game.gameWeapon.Club;
import game.gameWeapon.GreatKnife;
import game.gameWeapon.Uchigatana;
import game.reset.ResetManager;
import game.weaponSkill.Unsheathe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Myint Myat Thura
 *
 */
public class Player extends Actor{
	private final Menu menu = new Menu();
	int playtime = 0;
	public String chosenClass;
	boolean passed = false;
	int previous_flasks = 0;
	int smart_death_tracker = 0;
	int death_counter = 1;
	int start = 0;
	private static Player instance;
	int runesAmount = 0;
	int my_runes = 0;
	List<Runes> dropped_runes = new ArrayList<>();
	List<Runes> runes_list = new ArrayList<>();
	FlaskOfCrimsonTears flask;
	GameMap gmap;
	List<Location> my_steps = new ArrayList<>();
	Location startingLoc;
	private Location prev_location;
	Location deathLocation;
	boolean caryRem = false;
	int x = 0;
	public int max_hp = 0;
	int y = 0;
	public boolean chosen = false;
	int initial_hitPoints;

	/**
	 * This constructor will create a player and add the necessary runes at start,
	 * since we have implemented the Singleton, we do not have to worry about
	 * the runes adding to the wrong player or at the wrong time.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	private Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.initial_hitPoints = hitPoints;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.MAIN_CHARACTER);
		this.addCapability(Status.TALKING);

		// adding a rune object to the inventory when player has been creaetd
		Runes rune1 = new Runes("Runes", '$', true);
		this.addRunes(rune1);
		rune1.setRunes(25);
		this.addItemToInventory(rune1);



		FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears("Flask", '.', true);
		flask.resetFlask();
		this.flask = flask;

	}

	/**
	 * In this class we are able to choose the class and we will check if the player's
	 * health is low enough to be called Death. If so, we will call death action.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		RuneManager manage = RuneManager.getInstance();
		Player player = Player.getInstance();
		manage.runeAdd(player);
		if (!this.chosen) {
			System.out.println("Choose your combat class:\n1) Samurai\n2) Bandit\n3) Wretch\n4) Archeologist");
			Scanner scanner = new Scanner(System.in);
			int itemChoice = scanner.nextInt();
			// if 1 is chosen, we are samurai
			ClassManager classManage = ClassManager.getInstance();
			classManage.choosePlayerClass(this,itemChoice);
		}
		if (this.isConscious()){
			prev_location = map.locationOf(this);
		}

		if (this.getHp() == 0 || this.getHp() < 0) {
			player.playerDecreaseRunes(player.returnTotalRunes());
			for (String line : FancyMessage.YOU_DIED.split("\n")) {
				new Display().println(line);
				try {
					Thread.sleep(200);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			ResetManager resetManager = ResetManager.getInstance();
			resetManager.run();
			resetManager.death();
			death_counter++;


		}

		if (this.returnFlask() == null){
			this.flask = new FlaskOfCrimsonTears("Flask", '.', true);
			this.flask.setFlask(this.previous_flasks);
		}
		System.out.print(super.name + " has: " + getHp() + "/" + getMaxHp() + " HP, Runes: " + returnTotalRunes() + ", Flasks: ");
		System.out.println(this.returnFlask().getFlasks()+"/2");
		previous_flasks = this.returnFlask().getFlasks();


		ConsumeGoldenRune consume = new ConsumeGoldenRune(this,"null");
		if (player.getItemInventory().size()>0){
			actions.add(consume);
		}
		else{
			actions.remove(consume);
		}

		actions.add(new ConsumeFlask(this, "null"));
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);

	}

	/**
	 * The gamemap will be used to move the player around when necessary or save the
	 * location of the player
	 * @param ggmap
	 */
	public void addGameMap(GameMap ggmap) {
		gmap = ggmap;
	}

	/**
	 * Implementing singleton for the player
	 * @return
	 */
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player("Tarnished", '@', 300);
		}
		return instance;
	}

	/**
	 * Setting the death location of the player so we may always know where we have to
	 * remove the weapons from the map
	 * @param location
	 */
	public void setDeathLocation(Location location) {
		this.deathLocation = location;
	}

	/**
	 * A getter for death location
	 * @return
	 */
	public Location getDeathLocation() {
		return this.deathLocation;
	}

	/**
	 * Allowable actions for the player
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

		return super.allowableActions(otherActor, direction, map);
	}

	/**
	 * Getting the hitpoints so that we can modify it
	 * @return
	 */
	public int getHp() {
		return this.hitPoints;
	}

	/**
	 * This keeps tarck of the runes that we have dropped
	 * @param dropped_runes
	 */
	public void setDropped_runes(Runes dropped_runes) {
		this.dropped_runes.add(dropped_runes);
	}

	/**
	 * Getting the dropped runes, we will use this in DeathAction
	 * @return
	 */
	public List getDropped_runes() {
		return this.dropped_runes;
	}

	/**
	 * Increasing the HP for when we have consumed flasks
	 * @param amount
	 */
	public void increaseHP(int amount) {
		this.hitPoints += amount;
	}

	/**
	 * Getting the X and Y for the location to be respawned
	 * @return
	 */
	public int getX(){
		return this.x;
	}

	/**
	 * Getting the Y for the location to be respawned
	 * @return
	 */
	public int getY(){
		return this.y;
	}

	/**
	 * Setting the death location so we know where the player should be respawned
	 * @param x
	 * @param y
	 */

	public void setStartingLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * Resetting the death counter to know when to drop and where to delete
	 * items dropped from the map
	 */
	public void resetDeathCounter() {
		this.death_counter = 2;
	}

	/**
	 * Increase DeathCounter
	 */
	public void addDeathCounter() {
		this.death_counter++;
	}

	/**
	 * Return the death counter
	 * @return death_counter
	 */
	public int returnCounter() {
		return this.death_counter;
	}

	/**
	 * Tracks the death in another way to make sure that the deathAction is
	 * fail-safe
	 */
	public void setSmartDeathTracker() {
		smart_death_tracker++;
	}

	/**
	 * Returns the smart death tracker
	 * @return
	 */

	public int getSmart_death_tracker() {
		return smart_death_tracker;
	}

	/**
	 * Adding runes to inventory
	 * @param r
	 */

	public void addRunes(Runes r) {
		runes_list.add(r);
	}

	/**
	 * Returns the list of runes
	 * @return
	 */
	public List<Runes> returnRunesList() {
		return runes_list;
	}

	/**
	 * Returns the number of flasks
	 * @return
	 */
	public FlaskOfCrimsonTears returnFlask() {
		return flask;
	}

	/**
	 * Returns the total number of runes
	 * @return
	 */
	public int returnTotalRunes(){
		return my_runes;
	}

	/**
	 * Decreases the rune number from total number of runes
	 * @param amount
	 */
	public void playerDecreaseRunes(int amount){
		my_runes-=amount;
	}

	/**
	 * Increases the rune number of total runes
	 * @param amount
	 */
	public void playerIncreaseRunes(int amount){
		my_runes+=amount;
	}

	/**
	 * Gets the chosen class so we know which skills that we start with
	 * @return
	 */

	public String getChosenClass(){
		return this.chosenClass;
	}

	public void addRunes(int amount){
		this.my_runes += amount;
	}

	public Location getPrev_location(){
		return this.prev_location;
	}
}






