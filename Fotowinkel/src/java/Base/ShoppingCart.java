package Base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Martijn
 */
public class ShoppingCart
{
    Map hm = new HashMap();

    public ShoppingCart()
    {

    }

    public void AddItemToBasket(ShoppingCartItem product, int amount)
    {
        hm.put(product, amount);
    }

    public boolean RemoveItemFromBasket(ShoppingCartItem product)
    {
        for (Iterator<Object> it = hm.keySet().iterator(); it.hasNext();)
        {
            if (product.equals(it.next()))
            {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public Map getAllProducts()
    {
        return hm;
    }

    public void clear()
    {
        hm.clear();
    }
}
