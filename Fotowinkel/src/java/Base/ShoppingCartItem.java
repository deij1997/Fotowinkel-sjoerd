package Base;

import Base.Enums.ColorType;

/**
 *
 * @author Martijn
 */
public class ShoppingCartItem {

    private final Item Product;
    private final String ColorHex;
    private final ColorType ColorType;

    public ShoppingCartItem(Item Product, String ColorHex, ColorType ColorType) {
        this.Product = Product;
        this.ColorHex = ColorHex;
        this.ColorType = ColorType;
    }

    public ColorType getColorType() {
        return ColorType;
    }

    public Item getProduct() {
        return Product;
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
