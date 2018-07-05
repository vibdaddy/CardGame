import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

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
        game2();

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
        //int counter;
        double winnings = 0.0;
        List<Integer> playerRank = new ArrayList<Integer>();
        int max = 0;
        String[] cardRank = {"2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS"};
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
        System.out.println("Betting is an option, however betting will only occur once. There is only one round per player");
        System.out.println("You have the option to bet or pass, if you pass there is no penalty, but you will not have another chance to bet.");
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
            System.out.println(pList.get(i).getName() + " You have: " + pList.get(i).getCardHolder().get(0));
            boolean validAns1 = false;
            while(validAns1 == false){
                System.out.println("Would you like to pass or bet? Answer: pass/bet");
                Scanner psc5 = new Scanner(System.in);
                String playerAnswer = psc5.nextLine();
                if(playerAnswer.equals("pass")||playerAnswer.equals("Pass")||playerAnswer.equals("PASS")){
                    System.out.println("Understood, moving on to the next player");
                    winnings = winnings +1;
                    validAns1 = true;
                }
                else if(playerAnswer.equals("bet")||playerAnswer.equals("Bet")||playerAnswer.equals("BET")){
                    System.out.println("How much would you like to bet?");
                    int enteredNumber = 0;
                    Scanner myScanner = new Scanner(System.in);
                    boolean numberError = false;
                    String enteredString = "";
                    do {
                        try {
                            System.out.print("Enter amount: ");
                            enteredString = myScanner.next();  //Read into a string
                            enteredNumber = Integer.parseInt(enteredString.trim());  //then cast as a integer
                            numberError = false;  //if we haven't bailed out, then the number must be valid.
                        } catch (Exception e) {
                            System.out.println("Your entry: \"" +
                                    enteredString + "\" is invalid...Please try again");
                            numberError = true;  //Uh-Oh...We have a problem.
                        }
                    } while (numberError == true);
                    System.out.println("You will be betting $" + enteredNumber);
                    pList.get(i).bet(enteredNumber + 1);
                    winnings = winnings+enteredNumber+1;
                    System.out.println(pList.get(i).getBettingAmount());
                    validAns1 = true;
                }
                else{
                    System.out.println("That was not a valid input, please try again");
                }

            }
        }

        for(int i=0; i<pList.size(); i++){
            if (pList.get(i).getPlayerStatus() == false) {
                continue;
            }
            int counter = 0;
            System.out.println(pList.get(i).getCardHolder().get(0));
            String playerCard = pList.get(i).getCardHolder().get(0);
            for(String j : cardRank){
                counter++;
                if(j.equals(playerCard)){
                    pList.get(i).setIndexPosition(counter);
                }
                //System.out.println(j);
            }
        }

        for(int i=0; i<pList.size(); i++){
            int playerPosition = pList.get(i).getIndexPosition();
            if(playerPosition > max){
                max = playerPosition;
                playerRank.add(pList.get(i).getID());
            }
        }
        for(int i=0; i<pList.size(); i++){
            if(pList.get(i).getID()==playerRank.get(playerRank.size() - 1)){

                pList.get(i).setWinner();
                break;
            }


        }

        for(int i=0; i<pList.size(); i++){
            if(pList.get(i).checkWinner()==true){
                System.out.println(pList.get(i).getName() + "You have won!");
                System.out.println("$" + winnings + " will be added to your account");
                pList.get(i).deposit(winnings);
            }
            else if(pList.get(i).checkWinner()==false){
                System.out.println(pList.get(i).getName() + "You have lost");
                pList.get(i).withdraw(pList.get(i).getBettingAmount());
                System.out.println(pList.get(i).getName()+", " +pList.get(i).getBettingAmount()+1 + "will be withdrawn from your account");
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


    public static void game2() {


        System.out.println("The Rules:");


        System.out.println("-The basic premise of the game is that you want to have a hand value that is closer to 21 than that of the dealer, without going over 21");


        System.out.println("-An ace is worth 1. \n-The cards from 2 through 9 are valued at their face value.\n-The 10, Jack, Queen, and King are all valued at 10.");


        System.out.println("-Dealer must draw cards until he has a total of 17 or more");


        System.out.println("-If you draw a card that makes your hand total go over 21, your hand is a bust. That is an automatic loser. ");


        System.out.println("-If the dealer busts by going over 21, player wins.");


        System.out.println("-If both a player and the dealer have blackjack, the hand is a tie or push.");


        System.out.println("-If user asks for another card and number is under 21 they can stay or ask for another card until they choose to stop or they bust");


        System.out.println("");


        String anotherCard, playAgain = "y", cont = null;


        int nextCard, card1, card2, dCard1, dCard2;


        int cardTotal = 0, dTotal = 0;


        Scanner keyboard = new Scanner(System.in);


        Random random = new Random();


        boolean answerVal = false;


        while (playAgain == "y")


        {


            dCard1 = random.nextInt(10) + 1;


            dCard2 = random.nextInt(10) + 1;


            card1 = random.nextInt(10) + 1;


            card2 = random.nextInt(10) + 1;


            cardTotal = card1 + card2;


            dTotal = dCard1 + dCard2;


            System.out.println("Dealer shows: " + dCard1);


            System.out.println("First Cards: " + card1 + ", " + card2);


            System.out.println("Yout current total is: " + cardTotal);


            System.out.print("Another Card (y/n)?: ");


            anotherCard = keyboard.nextLine();


            nextCard = random.nextInt(10) + 1;


            cardTotal += nextCard;


            System.out.println("Card: " + nextCard);


            System.out.println("Total: " + cardTotal);


            if (cardTotal > 21)


            {


                System.out.println("You busted, Dealer Wins");


                System.out.println("Do you want to play again? (y/n): ");


                playAgain = keyboard.nextLine();


            }


            if (cardTotal < 21) {


                System.out.print("Another Card (y/n)?: ");


                anotherCard = keyboard.nextLine();


            }


            if (anotherCard == "n") {


                System.out.print("Press c to continue dealers cards");


            }


            cont = keyboard.nextLine();


            while (cont == "c" && dTotal < 17)


            {


                nextCard = random.nextInt(10) + 1;


                dTotal += nextCard;


                if (dTotal > 21)


                {


                    System.out.println("Dealer Busts, You Win!");


                    // Deposit to player account


                    System.out.println("Play Again? (y/n): ");


                    playAgain = keyboard.nextLine();


                    if (playAgain.equalsIgnoreCase("y"))


                        playAgain = "y";


                    else


                        System.exit(0);


                }


            }


        }
    }
}
