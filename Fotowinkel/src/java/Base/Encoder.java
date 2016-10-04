/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rowan
 */
public class Encoder
{
    private static final Random RANDOMISER = new Random();

    /**
     * Generates a code string to distribute
     *
     * @param amount The amount of serial codes to generate
     * @return A series of serial codes
     */
    public static String[] GenerateCodeStrings(int amount)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] generated = md.digest(GenerateRandomByteArray());
            String number = Arrays.toString(generated).replaceAll("\\D+","");;

            String[] ret = new String[amount];
            for (int i = 0; i < amount; i++)
            {
                String res = number + "-" + i;
                ret[i] = res;
            }
            return ret;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Encoder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static byte[] GenerateRandomByteArray()
    {
        byte[] b = new byte[RANDOMISER.nextInt(50) + 50];
        RANDOMISER.nextBytes(b);
        return b;
    }
}
