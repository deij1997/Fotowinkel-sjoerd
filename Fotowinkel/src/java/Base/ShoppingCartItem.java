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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + (this.Product != null ? this.Product.hashCode() : 0);
        hash = 89 * hash + (this.ColorType != null ? this.ColorType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ShoppingCartItem other = (ShoppingCartItem) obj;
        if (this.Product != other.Product && (this.Product == null || !this.Product.equals(other.Product)))
        {
            return false;
        }
        return this.ColorType == other.ColorType;
    }
    
    
    
    

}
