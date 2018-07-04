import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Driver {
    public static void main(String[] args) {

        //System.out.println(Deck.cardGen(2));
        /*for(int i=0; i<103; i++){
            Deck.cardGen();
            //Deck.printMap();
        }*/

        int enteredNumber = 0;
        Scanner myScanner = new Scanner(System.in);
        boolean numberError = false;
        String enteredString = "";
        do {
            try {
                System.out.print("Enter number of players: ");
                enteredString = myScanner.next();  //Read into a string
                enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
                numberError = false;  //if we haven't bailed out, then the number must be valid.
            } catch (Exception e) {
                System.out.println("Your entry: \"" +
                        enteredString + "\" is invalid...Please try again");
                numberError = true;  //Uh-Oh...We have a problem.
            }
        } while (numberError == true);  //Keep asking the user until the correct number is entered.
        //System.out.println("\nThat was a valid Number.");
        System.out.println("There will be " + enteredNumber + " players!");

        List<Player> playerList = new ArrayList<Player>();
        Player tmpPlayer = null;
        int playersLeft = enteredNumber;
        for(int i=0; i<enteredNumber; i++){
            tmpPlayer = new Player();
            //System.out.println(playersLeft);
            System.out.println("Enter player name: ");
            Scanner psc = new Scanner(System.in);
            String playerName = psc.nextLine();
            tmpPlayer.setName(playerName);
            playerList.add(tmpPlayer);
            playerList.get(i).welcomeMessage();
            playersLeft--;
            if (playersLeft > 1) {
                System.out.println("There are " + playersLeft + " player names left to enter");
            } else if (playersLeft == 1) {
                System.out.println("There is " + playersLeft + " player name left to enter");
            } else if (playersLeft == 0) {
                System.out.println("All player names have been entered!");
            }

            //System.out.println(playerList.get(i).getName());

        }

        for(int i=0; i<playerList.size(); i++){
            System.out.println(playerList.get(i).getName());
        }


    }
}





 /*     int enteredNumber = 0;
        Scanner myScanner = new Scanner(System.in);
        boolean numberError = false;
        String enteredString = "";
        do {
            try {
                System.out.print("Enter number of players: ");
                enteredString = myScanner.next();  //Read into a string
                enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
                numberError = false;  //if we haven't bailed out, then the number must be valid.
            } catch (Exception e) {
                System.out.println("Your entry: \"" +
                        enteredString + "\" is invalid...Please try again");
                numberError = true;  //Uh-Oh...We have a problem.
            }
        } while (numberError == true);  //Keep asking the user until the correct number is entered.
        //System.out.println("\nThat was a valid Number.");
        System.out.println("There will be " + enteredNumber + " players!");
        Player[] playerTracker = new Player[enteredNumber];
        for (int i = 0; i < playerTracker.length; i++) {
            int playersLeft = (playerTracker.length - 1) - i;
            System.out.println("Enter player name: ");
            Scanner psc = new Scanner(System.in);
            String playerName = psc.nextLine();
            playerTracker[i] = new Player(playerName, "PW");
            if (playersLeft > 1) {
                System.out.println("There are " + playersLeft + " player names left to enter");
            } else if (playersLeft == 1) {
                System.out.println("There is " + playersLeft + " player name left to enter");
            } else if (playersLeft == 0) {
                System.out.println("All player names have been entered!");
            }
        }

        for (int i = 0; i < playerTracker.length; i++) {
            System.out.println(playerTracker[i].getName());
        }*/
