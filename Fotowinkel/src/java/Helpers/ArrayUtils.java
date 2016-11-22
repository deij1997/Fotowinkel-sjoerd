/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.lang.reflect.Array;

/**
 *
 * @author Rowan
 */
public class ArrayUtils
{
    
    /**
     * Counts the amount of objects in an array
     *
     * @param haystack The array to browse through
     * @param needle The object to find
     * @return The amount of objects found
     * @throws IllegalArgumentException if the value was no array (haystack)
     */
    public static int count(Object haystack, Object needle) throws IllegalArgumentException
    {
        return count(getArray(haystack), needle, 0);
    }

    /**
     * Counts the amount of objects in an array
     *
     * @param haystack The array to browse through
     * @param needle The object to find
     * @return The amount of objects found
     */
    public static int count(Object[] haystack, Object needle)
    {        
        return count(haystack, needle, 0);
    }

    /**
     * Counts the amount of objects in an array
     *
     * @param <T> The object type
     * @param haystack The array to browse through
     * @param needle The object to find
     * @param start The starting index
     * @return The amount of objects found
     */
    public static <T> int count(T[] haystack, T needle, int start)
    {
        return (start < haystack.length ? (haystack[start].equals(needle) ? 1 : 0) + count(haystack, needle, ++start) : 0);
    }

    /**
     * Finds the first index of an object
     *
     * @param <T> The object type
     * @param needle The object to find
     * @param haystack The array to go through
     * @return The first index
     */
    public static <T> int indexOf(T needle, T[] haystack)
    {
        return indexOf(needle, haystack, 0);
    }

    /**
     * Finds the next index of an object
     *
     * @param <T> The object type
     * @param needle The object to find
     * @param haystack The array to go through
     * @param start The starting index
     * @return The first index
     */
    public static <T> int indexOf(T needle, T[] haystack, int start)
    {
        for (int i = start; i < haystack.length; i++)
        {
            if (haystack[i] != null && haystack[i].equals(needle)
                || needle == null && haystack[i] == null)
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Checks if an object exists in an array
     * 
     * @param <T> The object type
     * @param needle the object to verify
     * @param haystack the array to loop through
     * @return whether the object is in there or not
     */
    public static <T> boolean exists(T needle, T[] haystack)
    {
        return indexOf(needle, haystack) != -1;
    }
    
    /**
     * Checks if an object exists in an array
     * 
     * @param <T> The object type
     * @param needle the object to verify
     * @param haystack the array to loop through
     * @param start the starting index
     * @return whether the object is in there or not
     */
    public static <T> boolean exists(T needle, T[] haystack, int start)
    {
        return indexOf(needle, haystack, start) != -1;
    }

    /**
     * Returns all indices of an object (fast method)
     *
     * @param <T> The object type
     * @param needle The object to find
     * @param haystack The array to go through
     * @return All the indices
     */
    public static <T> int[] indicesOf(T needle, T[] haystack)
    {
        return indicesOf(needle, haystack, true);
    }

    /**
     * Returns all indices of an object (selection method)
     *
     * @param <T> The object type
     * @param needle The object to find
     * @param haystack The array to go through
     * @param FMM Fast but More memory usage (only if true)
     * @return All the indices
     */
    public static <T> int[] indicesOf(T needle, T[] haystack, boolean FMM)
    {
        int[] ret = new int[FMM ? count(haystack, needle) : haystack.length];
        int index = indexOf(needle, haystack, 0), i = 0;
        while (index >= 0)
        {
            ret[i++] = indexOf(needle, haystack, ++index);
        }
        return ret;
    }

    /**
     * Turns an array into an array of objects
     * 
     * @param val The array to convert to an object array 
     * @return An object array
     * @throws IllegalArgumentException if the value was no array
     */
    public static Object[] getArray(Object val) throws IllegalArgumentException
    {
        if (val == null)
        {
            throw new NullPointerException("Given convertable array object was null!");
        }
        if (!(val.getClass().isArray() || val instanceof Object[]))
        {
            throw new IllegalArgumentException("Unable to convert non-array object to array-object!");
        }
        if (val instanceof Object[])
        {
            return (Object[])val;
        }
        
        int arrlength = Array.getLength(val);
        Object[] outputArray = new Object[arrlength];
        for (int i = 0; i < arrlength; ++i)
        {
            outputArray[i] = Array.get(val, i);
        }
        return outputArray;
    }
}
