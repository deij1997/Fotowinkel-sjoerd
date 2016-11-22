package Base.Enums;

/**
 * Martijn
 */
public enum ColorType {
    NOCOLOUR {
        public String ToString() {
            return "No Colour";
        }
    }, HEX {
        public String ToString() {
            return "hex";
        }
    }, SEPIA {
        public String ToString() {
            return "Sepia";
        }
    }, BLACKWHITE {
        public String ToString() {
            return "Black & white";
        }
    };
}
