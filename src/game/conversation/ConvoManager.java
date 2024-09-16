package game.conversation;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class that acts as conversation manager which can helps manage right conversation channel
 * @author Wong Heng Yew
 */
public class ConvoManager {

    /**
     * ConvoManager instance
     */
    private static ConvoManager instance;

    /**
     * Getter for ConvoManager instance. There can be only one ConvoManager
     * @return a new or existing instance of ConvoManager
     */
    public static ConvoManager getInstance() {
        if (instance == null) {
            instance = new ConvoManager();
        }
        return instance;
    }

    /**
     * Method that selects the right convo type based on given speaker
     *
     * @param speaker speaker of the conversation
     * @return a Conversation of right speaker type
     */
    public Conversation getConvoClass(Actor speaker){
        if (speaker.getDisplayChar() == 'K'){
            return new TraderConvo(speaker);
        } else {
            return null;
        }

    }
}
