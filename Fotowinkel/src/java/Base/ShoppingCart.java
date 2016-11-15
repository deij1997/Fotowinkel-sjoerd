package Base;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martijn
 * oh god, I have no idea what I'm doing
 */
public class ShoppingCart {
    Map hm = new HashMap();
 
    public ShoppingCart()
    {
        
    }
    
    public void AddItemToBasket(Item product, int amount)
    {
        hm.put(product.code, amount);
    }
    
    public Map getAllProducts()
    {
        return hm;
    }
    

}
