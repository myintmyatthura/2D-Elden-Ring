package game.conversation;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import java.util.Scanner;

/**
 * Class that handles the progress of a conversation
 * @author Wong Heng Yew
 */
public class ConvoAction extends Action {

    /**
     * Speaker of the conversation
     */
    private Actor speaker;

    /**
     * Direction of speaker facing
     */
    private String direction;

    /**
     * Constructor for ConvoAction
     * @param speaker speaker of the conversation
     * @param direction direction of the speaker facing
     */
    public ConvoAction(Actor speaker, String direction){
        this.speaker = speaker;
        this.direction = direction;
    }

    /**
     * Method that handles the progress of a conversation.
     * It consists of talk and respond
     * Speaker talks and Player responds with numbered response
     * Speaker ends with final response
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String stating conversation has ended
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        ConvoManager convoManager = ConvoManager.getInstance();

        if (convoManager.getConvoClass(speaker) != null){

            // start convo from chosen speaker
            Conversation convo = convoManager.getConvoClass(speaker);
            int response = convo.talk();

            // get response from speaker
            convo.respond(response);
        }

        System.out.println("Convo ended. Type anything to end convo");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        return "Conversation ended";
    }

    /**
     * Method that describes conversation with speaker name
     *
     * @param actor The actor performing the action.
     * @return String about conversation with chosen speaker
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Chat with " + speaker ;
    }
}
