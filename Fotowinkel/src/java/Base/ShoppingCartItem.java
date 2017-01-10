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
    private final ColorType ColorType;

    public ShoppingCartItem(Item Product, String ColorHex, String article, ColorType ColorType)
    {
        this.Product = Product;
        this.ColorHex = ColorHex;
        this.ColorType = ColorType;
        
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
        return ColorType;
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
        if (this.ColorType == ColorType.HEX)
        {
            return ColorHex;
        }
        else
        {
            return this.ColorType.toString();
        }

    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + (this.Product != null ? this.Product.hashCode() : 0);
        hash = 29 * hash + (this.article != null ? this.article.hashCode() : 0);
        hash = 29 * hash + (this.ColorType != null ? this.ColorType.hashCode() : 0);
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
        return this.ColorType == other.ColorType;
    }

    @Override
    public String toString()
    {
        return "Item{" + "ColorHex=" + ColorHex + ", article=" + article + '}';
    }
}
