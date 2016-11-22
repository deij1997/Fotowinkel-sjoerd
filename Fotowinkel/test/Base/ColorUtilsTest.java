/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.NotAColorException;
import Helpers.ColorUtils;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class ColorUtilsTest
{
    private final static String[] WORKING_TEST_COLOURS = new String[]
    {
        "#aaa", "aaa", "#aaaaaa", "aaaaaa",
        "#AAA", "AAA", "#AAAAAA", "AAAAAA"
    };
    private final static String[] NOTWORKING_TEST_COLOURS = new String[]
    {
        "#aaaa", "aaaa", "#aaaaaaa", "aaaaaaa",
        "#aa", "aa", "#aaaaa", "aaaaa",
        "#AAG", "AAG", "#AAAAAG", "AAAAAG",
        "#aag", "aag", "#aaaaag", "aaaaag"
    };

    public ColorUtilsTest()
    {
    }

    @Test
    public void TestColor()
    {
        Color expected = new Color(150, 100, 50);
        String hexa = "#966432";
        
        try
        {
            Assert.assertEquals(ColorUtils.getColor(hexa), expected);
        }
        catch (NotAColorException ex)
        {
            assert false;
        }
    }
    
    @Test
    public void TestNameColor()
    {
        Color sepia = new Color(70, 50, 5);
        String in = "sepia";
        
        try
        {
            Assert.assertEquals(ColorUtils.getColor(in), sepia);
        }
        catch (NotAColorException ex)
        {
            assert false;
        }
    }

    @Test
    public void TestHexadecimal()
    {
        for (String s : WORKING_TEST_COLOURS)
        {
            Assert.assertTrue(s + " was not a hexidecimal color!", ColorUtils.isColor(s));
        }
    }

    @Test
    public void TestNotWorkingHexadecimal()
    {
        for (String s : NOTWORKING_TEST_COLOURS)
        {
            Assert.assertFalse(s + " was a hexidecimal color!", ColorUtils.isColor(s));
        }
    }
}
