package Base.Enums;

/**
 * Martijn
 */
public enum ColorType
{
    // using the constructor defined below
    NOCOLOR("Normale kleur"),
    BLACKWHITE("Zwart-wit"),
    SEPIA("Sepia"),
    HEX("Eigen gekozen kleur");

    // Member to hold the name
    private String string;

    // constructor to set the string
    ColorType(String name)
    {
        string = name;
    }

    // the toString just returns the given name
    @Override
    public String toString()
    {
        return string;
    }

    public static ColorType getTypeFromString(String string)
    {
        if (string == null)
        {
            return NOCOLOR;
        }
        if (string.contains("#"))
        {
            return ColorType.HEX;
        }
        //voordat je gaat huilen over if-statements, denk er dan aan dat 
        //je met JavaEE niet hoger dan Source level 6 kan 
        //en voor een switch statement in een string moet je Source level hoger dan 7 hebben
        if (string.equals("norml"))
        {
            return ColorType.NOCOLOR;
        }
        if (string.equals("gree"))
        {
            return ColorType.BLACKWHITE;
        }
        if (string.equals("sepia"))
        {
            return ColorType.SEPIA;
        }
        if (string.equals("hex"))
        {
            return ColorType.HEX;
        }
        return ColorType.NOCOLOR;

    }
}
