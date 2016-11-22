/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Exceptions.NotAColorException;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Rowan
 */
public class ColorUtils
{
    private final static HashMap<String, String> COLOR_CONVERTER = new HashMap<String, String>();

    // <editor-fold desc="huge ass list" defaultstate="collapsed">
    static
    {
        COLOR_CONVERTER.put("sepia", "#463205");
        COLOR_CONVERTER.put("rose", "8C2828");
        COLOR_CONVERTER.put("White", "FFFFFF");
        COLOR_CONVERTER.put("Ivory", "FFFFF0");
        COLOR_CONVERTER.put("LightYellow", "FFFFE0");
        COLOR_CONVERTER.put("Yellow", "FFFF00");
        COLOR_CONVERTER.put("Snow", "FFFAFA");
        COLOR_CONVERTER.put("FloralWhite", "FFFAF0");
        COLOR_CONVERTER.put("LemonChiffon", "FFFACD");
        COLOR_CONVERTER.put("Cornsilk", "FFF8DC");
        COLOR_CONVERTER.put("Seashell", "FFF5EE");
        COLOR_CONVERTER.put("LavenderBlush", "FFF0F5");
        COLOR_CONVERTER.put("PapayaWhip", "FFEFD5");
        COLOR_CONVERTER.put("BlanchedAlmond", "FFEBCD");
        COLOR_CONVERTER.put("MistyRose", "FFE4E1");
        COLOR_CONVERTER.put("Bisque", "FFE4C4");
        COLOR_CONVERTER.put("Moccasin", "FFE4B5");
        COLOR_CONVERTER.put("NavajoWhite", "FFDEAD");
        COLOR_CONVERTER.put("PeachPuff", "FFDAB9");
        COLOR_CONVERTER.put("Gold", "FFD700");
        COLOR_CONVERTER.put("Pink", "FFC0CB");
        COLOR_CONVERTER.put("LightPink", "FFB6C1");
        COLOR_CONVERTER.put("Orange", "FFA500");
        COLOR_CONVERTER.put("LightSalmon", "FFA07A");
        COLOR_CONVERTER.put("DarkOrange", "FF8C00");
        COLOR_CONVERTER.put("Coral", "FF7F50");
        COLOR_CONVERTER.put("HotPink", "FF69B4");
        COLOR_CONVERTER.put("Tomato", "FF6347");
        COLOR_CONVERTER.put("OrangeRed", "FF4500");
        COLOR_CONVERTER.put("DeepPink", "FF1493");
        COLOR_CONVERTER.put("Fuchsia", "FF00FF");
        COLOR_CONVERTER.put("Magenta", "FF00FF");
        COLOR_CONVERTER.put("Red", "FF0000");
        COLOR_CONVERTER.put("OldLace", "FDF5E6");
        COLOR_CONVERTER.put("LightGoldenrodYellow", "FAFAD2");
        COLOR_CONVERTER.put("Linen", "FAF0E6");
        COLOR_CONVERTER.put("AntiqueWhite", "FAEBD7");
        COLOR_CONVERTER.put("Salmon", "FA8072");
        COLOR_CONVERTER.put("GhostWhite", "F8F8FF");
        COLOR_CONVERTER.put("MintCream", "F5FFFA");
        COLOR_CONVERTER.put("WhiteSmoke", "F5F5F5");
        COLOR_CONVERTER.put("Beige", "F5F5DC");
        COLOR_CONVERTER.put("Wheat", "F5DEB3");
        COLOR_CONVERTER.put("SandyBrown", "F4A460");
        COLOR_CONVERTER.put("Azure", "F0FFFF");
        COLOR_CONVERTER.put("Honeydew", "F0FFF0");
        COLOR_CONVERTER.put("AliceBlue", "F0F8FF");
        COLOR_CONVERTER.put("Khaki", "F0E68C");
        COLOR_CONVERTER.put("LightCoral", "F08080");
        COLOR_CONVERTER.put("PaleGoldenrod", "EEE8AA");
        COLOR_CONVERTER.put("Violet", "EE82EE");
        COLOR_CONVERTER.put("DarkSalmon", "E9967A");
        COLOR_CONVERTER.put("Lavender", "E6E6FA");
        COLOR_CONVERTER.put("LightCyan", "E0FFFF");
        COLOR_CONVERTER.put("BurlyWood", "DEB887");
        COLOR_CONVERTER.put("Plum", "DDA0DD");
        COLOR_CONVERTER.put("Gainsboro", "DCDCDC");
        COLOR_CONVERTER.put("Crimson", "DC143C");
        COLOR_CONVERTER.put("PaleVioletRed", "DB7093");
        COLOR_CONVERTER.put("Goldenrod", "DAA520");
        COLOR_CONVERTER.put("Orchid", "DA70D6");
        COLOR_CONVERTER.put("Thistle", "D8BFD8");
        COLOR_CONVERTER.put("LightGrey", "D3D3D3");
        COLOR_CONVERTER.put("Tan", "D2B48C");
        COLOR_CONVERTER.put("Chocolate", "D2691E");
        COLOR_CONVERTER.put("Peru", "CD853F");
        COLOR_CONVERTER.put("IndianRed", "CD5C5C");
        COLOR_CONVERTER.put("MediumVioletRed", "C71585");
        COLOR_CONVERTER.put("Silver", "C0C0C0");
        COLOR_CONVERTER.put("DarkKhaki", "BDB76B");
        COLOR_CONVERTER.put("RosyBrown", "BC8F8F");
        COLOR_CONVERTER.put("MediumOrchid", "BA55D3");
        COLOR_CONVERTER.put("DarkGoldenrod", "B8860B");
        COLOR_CONVERTER.put("FireBrick", "B22222");
        COLOR_CONVERTER.put("PowderBlue", "B0E0E6");
        COLOR_CONVERTER.put("LightSteelBlue", "B0C4DE");
        COLOR_CONVERTER.put("PaleTurquoise", "AFEEEE");
        COLOR_CONVERTER.put("GreenYellow", "ADFF2F");
        COLOR_CONVERTER.put("LightBlue", "ADD8E6");
        COLOR_CONVERTER.put("DarkGray", "A9A9A9");
        COLOR_CONVERTER.put("Brown", "A52A2A");
        COLOR_CONVERTER.put("Sienna", "A0522D");
        COLOR_CONVERTER.put("YellowGreen", "9ACD32");
        COLOR_CONVERTER.put("DarkOrchid", "9932CC");
        COLOR_CONVERTER.put("PaleGreen", "98FB98");
        COLOR_CONVERTER.put("DarkViolet", "9400D3");
        COLOR_CONVERTER.put("MediumPurple", "9370DB");
        COLOR_CONVERTER.put("LightGreen", "90EE90");
        COLOR_CONVERTER.put("DarkSeaGreen", "8FBC8F");
        COLOR_CONVERTER.put("SaddleBrown", "8B4513");
        COLOR_CONVERTER.put("DarkMagenta", "8B008B");
        COLOR_CONVERTER.put("DarkRed", "8B0000");
        COLOR_CONVERTER.put("BlueViolet", "8A2BE2");
        COLOR_CONVERTER.put("LightSkyBlue", "87CEFA");
        COLOR_CONVERTER.put("SkyBlue", "87CEEB");
        COLOR_CONVERTER.put("Gray", "808080");
        COLOR_CONVERTER.put("Olive", "808000");
        COLOR_CONVERTER.put("Purple", "800080");
        COLOR_CONVERTER.put("Maroon", "800000");
        COLOR_CONVERTER.put("Aquamarine", "7FFFD4");
        COLOR_CONVERTER.put("Chartreuse", "7FFF00");
        COLOR_CONVERTER.put("LawnGreen", "7CFC00");
        COLOR_CONVERTER.put("MediumSlateBlue", "7B68EE");
        COLOR_CONVERTER.put("LightSlateGray", "778899");
        COLOR_CONVERTER.put("SlateGray", "708090");
        COLOR_CONVERTER.put("OliveDrab", "6B8E23");
        COLOR_CONVERTER.put("SlateBlue", "6A5ACD");
        COLOR_CONVERTER.put("DimGray", "696969");
        COLOR_CONVERTER.put("MediumAquamarine", "66CDAA");
        COLOR_CONVERTER.put("CornflowerBlue", "6495ED");
        COLOR_CONVERTER.put("CadetBlue", "5F9EA0");
        COLOR_CONVERTER.put("DarkOliveGreen", "556B2F");
        COLOR_CONVERTER.put("Indigo", "4B0082");
        COLOR_CONVERTER.put("MediumTurquoise", "48D1CC");
        COLOR_CONVERTER.put("DarkSlateBlue", "483D8B");
        COLOR_CONVERTER.put("SteelBlue", "4682B4");
        COLOR_CONVERTER.put("RoyalBlue", "4169E1");
        COLOR_CONVERTER.put("Turquoise", "40E0D0");
        COLOR_CONVERTER.put("MediumSeaGreen", "3CB371");
        COLOR_CONVERTER.put("LimeGreen", "32CD32");
        COLOR_CONVERTER.put("DarkSlateGray", "2F4F4F");
        COLOR_CONVERTER.put("SeaGreen", "2E8B57");
        COLOR_CONVERTER.put("ForestGreen", "228B22");
        COLOR_CONVERTER.put("LightSeaGreen", "20B2AA");
        COLOR_CONVERTER.put("DodgerBlue", "1E90FF");
        COLOR_CONVERTER.put("MidnightBlue", "191970");
        COLOR_CONVERTER.put("Aqua", "00FFFF");
        COLOR_CONVERTER.put("Cyan", "00FFFF");
        COLOR_CONVERTER.put("SpringGreen", "00FF7F");
        COLOR_CONVERTER.put("Lime", "00FF00");
        COLOR_CONVERTER.put("MediumSpringGreen", "00FA9A");
        COLOR_CONVERTER.put("DarkTurquoise", "00CED1");
        COLOR_CONVERTER.put("DeepSkyBlue", "00BFFF");
        COLOR_CONVERTER.put("DarkCyan", "008B8B");
        COLOR_CONVERTER.put("Teal", "008080");
        COLOR_CONVERTER.put("Green", "008000");
        COLOR_CONVERTER.put("DarkGreen", "006400");
        COLOR_CONVERTER.put("Blue", "0000FF");
        COLOR_CONVERTER.put("MediumBlue", "0000CD");
        COLOR_CONVERTER.put("DarkBlue", "00008B");
        COLOR_CONVERTER.put("Navy", "000080");
        COLOR_CONVERTER.put("Black", "000000");

    }
    ;
    // </editor-fold>
    
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
        in = in.replace("#", "").trim().toLowerCase();
        for (String s : COLOR_CONVERTER.keySet())
        {
            if (s.toLowerCase().equals(in))
            {
                return hexToColor(COLOR_CONVERTER.get(s));
            }
        }

        if (isColor(in))
        {
            return hexToColor(in);
        }
        throw new NotAColorException();
    }

    private static Color hexToColor(String in)
    {
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
}
