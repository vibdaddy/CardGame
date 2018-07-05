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

        Deck deck = new Deck();

        int enteredDeckNumber = 0;
        Scanner d = new Scanner(System.in);
        boolean numberError = false;
        String enteredDeckString = "";
        do {
            try {
                System.out.print("Enter number of decks: ");
                enteredDeckString = d.next();  //Read into a string
                enteredDeckNumber = Integer.parseInt(enteredDeckString.trim());  //then cast as a integer
                numberError = false;  //if we haven't bailed out, then the number must be valid.
            } catch (Exception e) {
                System.out.println("Your entry: \"" +
                        enteredDeckString + "\" is invalid...Please try again");
                numberError = true;  //Uh-Oh...We have a problem.
            }
        } while (numberError == true);  //Keep asking the user until the correct number is entered.

        Deck.setDecks(enteredDeckNumber);

        int enteredNumber = 0;
        Scanner myScanner = new Scanner(System.in);
        numberError = false;
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

            boolean passwordValidation = false;
            while(passwordValidation==false){
                System.out.println("Please create your password");
                Scanner psc1 = new Scanner(System.in);
                String password = psc1.nextLine();
                System.out.println("Please re-enter your password");
                Scanner psc2 = new Scanner(System.in);
                String rePassword = psc2.nextLine();
                if(password.equals(rePassword)){
                    tmpPlayer.setPassword(password);
                    System.out.println("You have successfully entered in a valid password");
                    passwordValidation = true;
                }
                else{
                    System.out.println("Your passwords did not match, please try again");
                }
            }

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

        /*for(int i=0; i<playerList.size(); i++){
            //playerList.get(i).deposit(100);
            //playerList.get(i).withdraw(100);
            playerList.get(i).dealCards(53);
            playerList.get(i).showHand();
        }*/

        //testDeal(playerList);

        highCard(playerList);

    }

    public static void testDeal(List<Player> pList){
        for(int i=0; i<pList.size(); i++){
            System.out.println("Lets deal cards to " + pList.get(i).getName());
            pList.get(i).dealCards(27);
            pList.get(i).showHand();
        }
    }

    public static void highCard(List<Player> pList){
        for(int i=0; i<pList.size(); i++){
            System.out.println("Is " + pList.get(i).getName() + " playing? Y/N");
            Scanner psc3 = new Scanner(System.in);
            String answer = psc3.nextLine();
            if(answer.equals("YES") || answer.equals("yes") || answer.equals("y") || answer.equals("Y")){
                if(passwordAuth(pList, answer, i)==true){
                    System.out.println("Welcome to highCard, " + pList.get(i).getName());
                }
                else{
                    System.out.println("Looks like you weren't able to successfully enter your password, try again next time");
                    continue;
                }
                //pList.get(i).setTrue();
            }
        }
    }

    public static boolean passwordAuth(List<Player> pList, String pw, int index){
        boolean authenticate = false;
        int passwordAttempts = 5;
        boolean passwordValidation = false;
        while(passwordValidation==false){
            if(passwordAttempts==0){
                System.out.println("Looks like you ran out of password attempts, sorry pal.");
                authenticate = false;
                break;
            }
            System.out.println("Enter Password: ");
            Scanner psc4 = new Scanner(System.in);
            String password = psc4.nextLine();
            if(password.equals(pList.get(index).getName())){
                authenticate = true;
                passwordValidation = true;
            }
            else{
                passwordAttempts--;
                System.out.println("You entered an incorrect password, please try again. NOTE: You have " + passwordAttempts + " password attempts left");
            }
        }
        return authenticate;

    }
}
