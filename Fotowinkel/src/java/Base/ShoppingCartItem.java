package Base;

import Base.Enums.ColorType;

/**
 *
 * @author Martijn
 */
public class ShoppingCartItem {

    private final String Code;
    private final String ColorHex;
    private final ColorType ColorType;

    public ShoppingCartItem(String Code, String ColorHex, ColorType ColorType) {
        this.Code = Code;
        this.ColorHex = ColorHex;
        this.ColorType = ColorType;
    }

    public ColorType getColorType() {
        return ColorType;
    }

    public String getCode() {
        return Code;
    }

    public String getColorHex() {
        return ColorHex;
    }

    public String getColourName() {
        if (this.ColorType == ColorType.HEX) {
            return ColorHex;
        } else {
            return this.ColorType.toString();
        }

    }

}
