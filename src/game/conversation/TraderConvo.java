package game.conversation;

import edu.monash.fit2099.engine.actors.Actor;
import game.Player;
import java.util.Scanner;

/**
 * Class that represents TraderConvo
 * @author Wong Heng Yew
 */
public class TraderConvo extends Conversation implements Conversable {

    /**
     * Constructor for TraderConvo
     *
     * @param speaker speaker of the trader convo
     */
    public TraderConvo(Actor speaker) {
        super(speaker);
    }

    /**
     * Method that does the narrative part from the speaker
     * @return a numbered response
     */
    @Override
    public int talk() {
        int response_choice = 0;

        System.out.println("Trader: For 5 Runes, I will tell you the very history of the land.");
        System.out.println("1. What is a Demigod?\n2. Maybe next time.");
        Scanner scanner = new Scanner(System.in);
        int story_choice = scanner.nextInt();

        if (story_choice == 1) {
            Player player = Player.getInstance();
            System.out.println(player.returnTotalRunes());
            if (player.returnTotalRunes() >= 5) {
                player.playerDecreaseRunes(5);
                System.out.println("Trader: Ah,the Demigod Starscourge Radahn is an embodiment of the darkest fears and nightmares that plague the mortal realm.\nIt is a terrifying force, if He ever comes across your path,I suggest to look the other way.");
                System.out.println("1. How may I defeat this Demigod?");
                System.out.println("2. I fear nothing.");
                response_choice = scanner.nextInt();
            } else {
                System.out.println("Trader: I don't talk to broke people");
            }

        }
        if (story_choice == 2) {
            System.out.println("Trader: *chuckles* You're a strong and fearless warrior but you will meet your match one day.");
        }
        return response_choice;
    }
    public void conversation2(){
        System.out.println("1) Are there more Demigods?\n2) What will happen if I were to fight him without magic?");
        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        if (response == 1) {
            String message2 = "There is one more...\nHer name is Malenia..\nor....Blade of Miquella. She was the one who last battled with General Radahn. They both suffered...\nheavy loss... in the battle\nShe controls a powerful force called the Scarlet Rot, it consumes the life force of everyone around it...\nit consumed General Radahn too..\n" +
                    "but if not for his strength, it would have killed him too...\nbut he suffers a far worse curse now, he is cursed to walk the lands as the Disgraced one..";
            String[] lines2 = message2.split("\n");
            for (String line : lines2) {
                System.out.println(line);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("1) Is she stronger than General Radahn?\n2) How may I defeat her?");
            int response2 = scanner.nextInt();
            if (response2 == 1) {
                String message3 = "It is difficult to say dear tarnished...\nThey have destroyed each other to their core... they are...\nmatched evenly...\nthat is all i can say for now dear tarnished...";
                String[] lines3 = message3.split("\n");
                for (String line : lines3) {
                    System.out.println(line);
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (response2 == 2) {
                String message5 = "Malenia wears an armour that is lighter than feather...\nyet is can withstand the impact of a crashing star...\nit is truly a gem that was made by the Demigod herself...\nto defeat her... you must find a weapon forged by a god..\n" +
                        "but....\nGeneral Radahn and Malenia are the only demigods left in this land....\nWithin general radahn's body, the Blade of Malenia resides....\nunable to be budged for it is tied to General Radahn's soul itself...\n" +
                        "you must defeat General Radahn first...\nthen you must obtain the godly weapon, then you may defeat her.....\nbeware dear Tarnished....she is very strong...\nyou will not beat her at your current strength...\n" +
                        "i have said enough.... it is not wise to plan the deaths of the demigods...";
                String[] lines5 = message5.split("\n");
                for (String line : lines5) {
                    System.out.println(line);
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            conversation3();
        }

    }
    public void conversation3(){
            String message4 = "Oh Dear Tarnished... you possibily couldn't think you could defeat him without magic..\nOh...\nDear Tarnished, Radahn once fought uncountable monsters upon a dying star...\nthe extreme heat of the star couldn't even make him sweat...\n" +
                    "there is no weapon on this land that could harm him...\nnot even if it was forged out of a Demigod's soul..my dear Tarnished\nYou must find the Crimson Moon relic, and I will show you the power to move the very universe itself...\n" +
                    "come back when you are ready.....\n dear tarnished..";
            String[] lines4 = message4.split("\n");
            for (String line : lines4) {
                System.out.println(line);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    /**
     * Method that selects a response from the numbered response chosen from previous talk
     * @param choice numbered response from previous talk
     */
    @Override
    public void respond(int choice) {

        if (choice == 0) {
            System.out.println("Trader: Begone,stranger.");
        }
        if (choice == 1) {
            String message = "Trader: Starscourge Radahn was a general of the order who had the ability to control gravity..\nIt is his very might that holds the stars back from crashing into our land...\nHowever, he has lost his wits of lately...\nTo defeat him, you must first master the art of Magic, you must explore the lands...\nand find the Crimson Moon relic, bring it back to me and I shall...\ngive you a weapon worthy of defeating General Radahn. Farewell for now Dear Tarnished...";
            String[] lines = message.split("\n");
            for (String line : lines) {
                System.out.println(line);
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            conversation2();


            if (choice == 2) {
                System.out.println("Trader: .... Good luck with whatever foes you face ahead.");
            }

        }
    }
}
