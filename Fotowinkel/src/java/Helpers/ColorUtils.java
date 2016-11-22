/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Exceptions.NotAColorException;
import java.awt.Color;

/**
 *
 * @author Rowan
 */
public class ColorUtils
{

    private final static Character[] HEXADECIMAL_CHARACTERS = new Character[]
    {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * Checks if a string can be converted to a color
     *
     * @param in The string to check
     * @return whether the string is a parseable color or not
     */
    public static boolean isColor(String in)
    {
        //Remove the # and all spaces surrounding it
        in = in.replace("#", "").trim();
        int length = in.length();

        //Hexes are either 3 or 6 long
        if (!(length == 3 || length == 6))
        {
            return false;
        }
        for (char c : in.toCharArray())
        {
            Character cha = Character.toLowerCase(c);
            if (!ArrayUtils.exists(cha, HEXADECIMAL_CHARACTERS))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a string to a color
     *
     * @param in The string to convert
     * @return
     * @throws Exceptions.NotAColorException
     */
    public static Color getColor(String in) throws NotAColorException
    {
        if (isColor(in))
        {
            in = in.replace("#", "").trim();
            int take = (in.length() == 3) ? 1 : 2,
                    index = 0, loops = 0;
            int[] colors = new int[3];

            while (index < in.length())
            {
                colors[loops] = Integer.parseInt(in.substring(index, index + take), 16);
                loops++;
                index += take;
            }
            return new Color(colors[0], colors[1], colors[2]);
        }
        throw new NotAColorException();
    }
}
