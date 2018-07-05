import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Player {

    private int indexPosition; //NOT A UNIVERSAL VARIABLE, THIS IS SPECIFIC TO VAIBHAV SHARMA'S GAME
    private boolean isPlaying;
    private String password;
    private int pID;
    private String playerName;
    private double balance;
    private boolean winner;
    private double bettingAmount = 0;
    HashMap<String, Integer> playerHand = new HashMap<String, Integer>();
    List<String> cardHolder = new ArrayList<String>();

    public Player(){
        setID(Counter.count());
        setFalse();
        balance = 100;
    }

    public void setIndexPosition(int pos){ //NOT A UNIVERSAL METHOD, THIS IS SPECIFIC TO VAIBHAV SHARMA'S GAME
        indexPosition = pos;
    }

    public int getIndexPosition(){ //NOT A UNIVERSAL METHOD, THIS IS SPECIFIC TO VAIBHAV SHARMA'S GAME
        return indexPosition;
    }

    public void bet(int amount){
        bettingAmount = bettingAmount + amount;
    }

    public double getBettingAmount(){
        return bettingAmount;
    }

    public List<String> getCardHolder(){
        return cardHolder;
    }

    public void setWinner(){
        winner = true;
    }

    public void resetWinner(){
        winner = false;
    }

    public boolean checkWinner(){
        return winner;
    }

    public void withdraw(double amount){
        balance = balance - amount;
        System.out.println(amount + " was withdrawn from your account, " + getName() + ". Your new balance is: " + getBalance());
    }

    public void deposit(double amount){
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

    public void setTrue(){
        isPlaying = true;
    }

    public void setFalse(){
        isPlaying = false;
    }

    public boolean getPlayerStatus(){
        return isPlaying;
    }

    public void setPassword(String pw){
        password = pw;
    }

    public String getPassword(){
        return password;
    }

    public void showHand(){
        System.out.println(playerHand);
    }

    public void dealCards(int numCards){
        String card = " ";
        for(int i=0; i<numCards; i++){
            card = Deck.cardGen();
            if(playerHand.get(card) == null){
                playerHand.put(card, 1);
                cardHolder.add(card);
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
                        cardHolder.add(card);
                        answerValidation = true;
                    }
                }

                //continue;
            }
            else if(playerHand.get(card)>=1){
                playerHand.put(card, playerHand.get(card)+1);
                cardHolder.add(card);
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
