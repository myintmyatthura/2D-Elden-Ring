package game.CombatClass;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Efficient interface for handling the assigning of classes
 */
public interface ClassHandler {
    int chooseClass(Actor actor, int choice);
    String chosenClass();
}
