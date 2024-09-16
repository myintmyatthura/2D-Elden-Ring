package game.conversation;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Abstract class that implements Conversable methods for override in subclasses
 * @author Wong Heng Yew
 */
public abstract class Conversation implements Conversable{

    /**
     * The speaker of the conversation
     */
    private Actor speaker;

    /**
     * Constructor for COnversation
     *
     * @param speaker speaker of the conversation
     */
    public Conversation(Actor speaker){
        this.speaker = speaker;
    }

    /**
     * Method that does the narration
     * @return numbered response
     */
    @Override
    public int talk() {
        return 0;
    }

    /**
     * Method that respond to previous talk
     * @param choice numbered response from previous talk
     */
    @Override
    public void respond(int choice) {

    }
}
