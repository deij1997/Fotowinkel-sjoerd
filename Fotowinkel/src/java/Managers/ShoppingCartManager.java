package Managers;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Rowan
 */
public class ShoppingCartManager {

    HashMap carts = new HashMap();

    public static String getRandomCartID(int bytes) {
        return randID(bytes);
    }

    static String randID(int bytes) {
        byte[] r;
        boolean correct = true;
        String s;
        r = new byte[bytes];
        Random ran = new Random();
        ran.nextBytes(r);
        s = new String(r);
        if (!correct) { //obviously we will replace this check with one that 
                        // checks if the ID already exists
            s = randID(bytes);
        }
        return s;
    }
}
