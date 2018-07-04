import java.util.HashMap;

/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Player {
    //int numPlayers;
    static boolean isPlaying;
    static String password;
    static int pID;
    String playerName;
    //static int numDecks;
    static HashMap<String, Integer> playerHand = new HashMap<String, Integer>();

    public Player(){

        setID(Counter.count());

        //setName(name);

        //setPassword(pw);

        setFalse();

    }

    public static void setID(int ID){
        pID = ID;
    }

    public static int getID(){

        return pID;
    }

    /*public static void setDecks(int num){
        numDecks = num;
    }*/

    public void setName(String name){
        playerName = name;
    }

    public String getName(){
        return playerName;
    }

    public static void setTrue(){
        isPlaying = true;
        //return isPlaying;
    }

    public static void setFalse(){
        isPlaying = false;
        //return isPlaying;
    }

    public static boolean getPlayerStatus(){
        return isPlaying;
    }

    public static void setPassword(String pw){
        password = pw;
    }

    public static void showHand(){
        System.out.println(playerHand);
    }
    public static void dealCards(int numCards){
        for(int i=0; i<numCards; i++){
            //playerHand.put(Deck.cardGen(),)
        }
    }

    public void welcomeMessage(){
        System.out.println("Welcome " + getName() + "! Your player ID is: " + getID());
    }
}
