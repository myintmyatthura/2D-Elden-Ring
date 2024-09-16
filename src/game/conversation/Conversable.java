package game.conversation;

/**
 * Interface that stores methods in defining content of a dialogue
 * @author Wong Heng Yew
 */
public interface Conversable {

    /**
     * Method that intends to process the majority of the dialogue
     * @return a numbered response
     */
    int talk();

    /**
     * Method that intends to process the response dialogue
     * @param choice numbered response from previous talk
     */
    void respond(int choice);
}
