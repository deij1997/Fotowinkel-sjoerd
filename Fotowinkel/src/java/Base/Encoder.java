/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;

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
    public static String[] GenerateCodeStrings(int amount) throws RandomiserFail
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] generated = md.digest(GenerateRandomByteArray());
            String number = Arrays.toString(generated).replaceAll("\\D+", "");

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
            throw new RandomiserFail();
        }
    }

    public static String GetHash(String input) throws RandomiserFail
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");

            return Arrays.toString(md.digest(input.getBytes("UTF-8"))).replaceAll("\\D+", "");
        }
        catch (Exception ex)
        {
            throw new RandomiserFail();
        }
    }

    private static byte[] GenerateRandomByteArray()
    {
        byte[] b = new byte[RANDOMISER.nextInt(50) + 50];
        RANDOMISER.nextBytes(b);
        return b;
    }

    public static String HTMLEntityEncode(String input)
    {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++)
        {
            char ch = input.charAt(i);

            if (Character.isLetterOrDigit(ch) || Character.isWhitespace(ch))
            {
                sb.append(ch);
            }
            else
            {
                sb.append("&#").append((int) ch).append(";");
            }
        }

        return sb.toString();

    }
}
