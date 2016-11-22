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
}
