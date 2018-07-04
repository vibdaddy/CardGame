import java.util.HashMap;
import java.util.Random;

/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Deck {

    /*public Deck() {
        HashMap<String, Integer> cardTracker = new HashMap<String, Integer>();
    }*/

    static HashMap<String, Integer> cardTracker = new HashMap<String, Integer>();

    public static boolean conditionsMet;

    public static String card;

    public static int numDecks;

    public static int counter;

    public static void setDecks(int num){
        numDecks = num;
        System.out.println("The number of decks has been set! We will be using " + numDecks + " deck(s)");
    }

    public static void addDecks(int num){
        numDecks = numDecks + num;
    }

    public static int getNumDecks(){
        return numDecks;
    }

    public static String cardGen() { //this method randomly generates the suit
        while(conditionsMet == false){
            String suit = "HDCS"; //string that contains all of the suits
            Random rand1 = new Random();
            int stringIndexPosition = rand1.nextInt(4); //generates a random index position between 1-4
            char stringStripper = suit.charAt(stringIndexPosition); //suit is generated here as a char stripped from the parent string
            Random rand = new Random();
            int deckNum = rand.nextInt(13) + 1;
            //combiner(stringStripper, deckNum);
            String convertNum = " ";
            String convertChar = " ";
            String finalStringValue = " ";
            if (deckNum == 1) {
                convertNum = "A";
            } else if (deckNum == 11) {
                convertNum = "J";
            } else if (deckNum == 12) {
                convertNum = "Q";
            } else if (deckNum == 13) {
                convertNum = "K";
            }
            else if(deckNum>13){
                throw new IllegalArgumentException("number must be less than 14"); //this is mainly for my own testing purposes
            }
            else {
                convertNum = Integer.toString(deckNum);
            }
            convertChar = String.valueOf(stringStripper);
            finalStringValue = convertNum + convertChar;
            card = finalStringValue;

            if(cardTracker.get(card) == null){
                cardTracker.put(card, 1);
                conditionsMet = true;
            }
            else if(cardTracker.get(card)>= numDecks){
                //cardGen(map, numDecks);
                //System.out.println("INDIVIDUAL CARD LIMIT REACHED");
                counter++;
                //throw new IllegalArgumentException("INDIVIDUAL CARD LIMIT REACHED");
                System.out.println(counter);
                continue;
            }
            else if(cardTracker.get(card)>=1){
                cardTracker.put(card, cardTracker.get(card)+1);
                conditionsMet = true;
            }
            //System.out.println(map);
            //System.out.println(map.size());
            System.out.println(card);
        }
        conditionsMet = false;
        return card;
    }
    void printMap(){
        System.out.println(cardTracker);
    }
}
