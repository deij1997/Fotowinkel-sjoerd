package Base;

import Base.Enums.ColorType;

/**
 *
 * @author Martijn
 */
public class ShoppingCartItem
{

    private final Item Product;
    private final String ColorHex;
    private final String article;
    private final ColorType colorType;

    public ShoppingCartItem(Item Product, String ColorHex, String article)
    {
        this.Product = Product;
        this.ColorHex = ColorHex;        
        this.colorType = ColorType.getTypeFromString(this.ColorHex);
        
        if (article == null || article.isEmpty() || article.equals("null"))
        {
            article = "standaard";
        }
        this.article = article;
    }

    public String getArticle()
    {
        return article;
    }
    
    public ColorType getColorType()
    {
        return colorType;
    }

    public Item getProduct()
    {
        return Product;
    }

    public String getColorHex()
    {
        return ColorHex;
    }

    public String getColourName()
    {
        if (this.colorType == colorType.HEX)
        {
            return ColorHex;
        }
        else
        {
            return this.colorType.toString();
        }

    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + (this.Product != null ? this.Product.hashCode() : 0);
        hash = 29 * hash + (this.article != null ? this.article.hashCode() : 0);
        hash = 29 * hash + (this.colorType != null ? this.colorType.hashCode() : 0);
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
        if ((this.article == null) ? (other.article != null) : !this.article.equals(other.article))
        {
            return false;
        }
        if (this.Product != other.Product && (this.Product == null || !this.Product.equals(other.Product)))
        {
            return false;
        }
        if (this.ColorHex != other.ColorHex && (this.ColorHex == null || !this.ColorHex.equals(other.ColorHex)))
        {
            return false;
        }
        return this.colorType == other.colorType;
    }

    @Override
    public String toString()
    {
        return "Item{" + "ColorHex=" + ColorHex + ", article=" + article + '}';
    }
}
