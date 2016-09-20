/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author Rowan
 */
public class Encoder
{

    /**
     * Generates a code string to distribute
     * @param amount The amount of serial codes to generate
     * @return A series of serial codes
     */
    public static String[] GenerateCodeStrings(int amount)
    {
        String[] ret = new String[amount];
        for (int i = 0; i < amount; i++)
        {
            ret[i] = "555-" + i;
        }
        return ret;
    }
}
