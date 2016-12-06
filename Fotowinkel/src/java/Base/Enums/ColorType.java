package Base.Enums;

/**
 * Martijn
 */
public enum ColorType {
   // using the constructor defined below
   NOCOLOR("Normale kleur"),
   BLACKWHITE("Zwart-wit"),
   SEPIA("Sepia"),
   HEX("hex");

   // Member to hold the name
   private String string;

   // constructor to set the string
   ColorType(String name){string = name;}

   // the toString just returns the given name
   @Override
   public String toString() {
       return string;
   }
   
   public static ColorType getTypeFromString(String string)
   {

       //voordat je gaat huilen over if-statements, denk er dan aan dat 
       //je met JavaEE niet hoger dan Source level 6 kan 
       //en voor een switch statement in een string moet je Source level hoger dan 7 hebben
       if(string.equals("NoColor")) return ColorType.NOCOLOR;
       if(string.equals("BlackWhite")) return ColorType.BLACKWHITE;
       if(string.equals("Sepia")) return ColorType.SEPIA;
       if(string.equals("Hex")) return ColorType.HEX;
       return ColorType.NOCOLOR;
       
   }
}
