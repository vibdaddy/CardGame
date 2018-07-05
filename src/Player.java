import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Player {

    static boolean isPlaying;
    String password;
    int pID;
    String playerName;
    double balance;
    HashMap<String, Integer> playerHand = new HashMap<String, Integer>();

    public Player(){
        setID(Counter.count());
        setFalse();
        balance = 100;
    }

    public void withdraw(int amount){
        balance = balance - amount;
        System.out.println(amount + " was withdrawn from your account, " + getName() + ". Your new balance is: " + getBalance());
    }

    public void deposit(int amount){
        balance = balance + amount;
        System.out.println(amount + " was deposited to your account, " + getName() + ". Your new balance is: " + getBalance());
    }

    public double getBalance(){
        return balance;
    }

    public void setID(int ID){
        pID = ID;
    }

    public int getID(){return pID;}


    public void setName(String name){
        playerName = name;
    }

    public String getName(){
        return playerName;
    }

    public static void setTrue(){
        isPlaying = true;
    }

    public static void setFalse(){
        isPlaying = false;
    }

    public static boolean getPlayerStatus(){
        return isPlaying;
    }

    public void setPassword(String pw){
        password = pw;
    }

    public void showHand(){
        System.out.println(playerHand);
    }

    public void dealCards(int numCards){
        for(int i=0; i<numCards; i++){
            String card = Deck.cardGen();
            if(playerHand.get(card) == null){
                playerHand.put(card, 1);
                //playerHand = true;
            }
            else if(playerHand.get(card)>= Deck.getNumDecks()){
                System.out.println("MAX LIMIT OF CARDS IN DECK HAS BEEN REACHED");
                System.out.println("Would you like to add more decks? Answer Y/N");
                Scanner psc = new Scanner(System.in);
                String answer = psc.nextLine();
                if(answer.equals("YES") || answer.equals("yes") || answer.equals("y") || answer.equals("Y")){
                    boolean answerValidation = false;
                    while(answerValidation==false){
                        int enteredNumber = 0;
                        Scanner myScanner = new Scanner(System.in);
                        boolean numberError = false;
                        String enteredString = "";
                        do {
                            try {
                                System.out.print("Enter number of decks you would like to add");
                                enteredString = myScanner.next();  //Read into a string
                                enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
                                numberError = false;  //if we haven't bailed out, then the number must be valid.
                            } catch (Exception e) {
                                System.out.println("Your entry: \"" +
                                        enteredString + "\" is invalid...Please try again");
                                numberError = true;  //Uh-Oh...We have a problem.
                            }
                        } while (numberError == true);  //Keep asking the user until the correct number is entered.
                        Deck.addDecks(enteredNumber);
                        playerHand.put(card, playerHand.get(card)+1);
                        answerValidation = true;
                    }
                }

                //continue;
            }
            else if(playerHand.get(card)>=1){
                playerHand.put(card, playerHand.get(card)+1);
                //conditionsMet = true;
            }
            //playerHand.put(Deck.cardGen(),)
        }
    }

    public void welcomeMessage(){
        System.out.println("Welcome " + getName() + "! Your player ID is: " + getID());
    }

    public void overview(){
        System.out.println("Player name: " + playerName);
        System.out.println("Player ID: " + pID);
        System.out.println("Password: " + password);
        System.out.println("Balance: " + getBalance());
        System.out.println("Hand: ");
        showHand();
    }
}
