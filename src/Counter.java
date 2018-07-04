/**
 * Created by vaibhavsharma on 7/3/18.
 */
public class Counter {
    private static int value = 0;

    /*public counter(){
        value = 0;
    }*/

    public static int count(){
        value = value + 1;
        return value;
    }
}
