/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 *
 * @author Rowan
 */
public class Basics
{
    public static boolean isProperString(String in)
    {
        if (in == null)
        {
            return false;
        }
        return !in.isEmpty();
    }
    
    public static boolean checkForNullValues(String in[])
    {
        for (String i : in)
        {
            if (!isProperString(i))
            {
                return true;
            }
        }
        return false;
    }
}
