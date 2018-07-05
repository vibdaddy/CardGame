import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        //Game made by Vaibhav Sharma
        String[] cardRank = {};
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!WELCOME TO HIGH CARD!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for(int i=0; i<pList.size(); i++){
            System.out.println("Is " + pList.get(i).getName() + " playing? Keep in mind it is required that you put up $1 to play the game. Answer Y/N");
            boolean validAns = false;
            while(validAns == false){
                Scanner psc3 = new Scanner(System.in);
                String answer = psc3.nextLine();
                if(answer.equals("YES") || answer.equals("yes") || answer.equals("y") || answer.equals("Y")){
                    if(passwordAuth(pList, answer, i)==true){
                        System.out.println("Welcome to highCard, " + pList.get(i).getName());
                        pList.get(i).setTrue();
                        validAns = true;
                    }
                    else{
                        System.out.println("Looks like you weren't able to successfully enter your password, try again next game");
                        pList.get(i).setFalse();
                        validAns = true;
                    }
                }
                else if(answer.equals("NO") || answer.equals("no") || answer.equals("n") || answer.equals("N")){
                    pList.get(i).setFalse();
                    validAns=true;
                }
                else{
                    System.out.println("Please enter a valid response Y/N");
                }
            }

        }
        System.out.println("Now that we have sorted out who is playing, let's begin!");
        System.out.println("The rules of the game are very simple.");
        System.out.println("The player with the highest card wins!");
        System.out.println("If both players have identical cards, the suit will determine the winner");
        System.out.println("The ranking of suits in ascending order is as follows: Hearts -> Diamonds -> Clubs -> Spades");
        System.out.println("EXAMPLE: If player one has an Ace of spades and player two has an Ace of hearts, player one will win the hand");
        System.out.println("An Ace is only treated as the high card, not one in any case");
        System.out.println("NOTE, the only time a tie will occur is if players are using more than one deck");
        System.out.println("If there is a tie (for example player 1 has an Ace of Spades and player 2 has the same card) the pot will be split amongst the winners");

        clear();

        System.out.println("Cards are being dealt");

        for(int i=0; i<pList.size(); i++){
            if(pList.get(i).getPlayerStatus()==false){
                continue;
            }
            pList.get(i).dealCards(1);
            //System.out.println(pList.get(i).getCardHolder().get(0));
        }

        for(int i=0; i<pList.size(); i++) {
            if (pList.get(i).getPlayerStatus() == false) {
                continue;
            }
        }
    }

    public static boolean passwordAuth(List<Player> pList, String pw, int index){
        boolean authenticate = false;
        int passwordAttempts = 5;
        boolean passwordValidation = false;
        while(passwordValidation==false){
            if(passwordAttempts==0){
                //System.out.println("Looks like you ran out of password attempts, sorry pal.");
                authenticate = false;
                break;
            }
            System.out.println("Enter Password: ");
            Scanner psc4 = new Scanner(System.in);
            String password = psc4.nextLine();
            if(password.equals(pList.get(index).getPassword())){
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

    public static void clear(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }
}
