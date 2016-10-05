package Base;

import java.util.HashMap;

/**
 *
 * @author Martijn
 * oh god, I have no idea what I'm doing
 */
public class ShoppingCart {
    HashMap hm = new HashMap();
    String CookieHash;
    
    
    
    public String GetCookieHash()
    {
        return CookieHash;
    }
    
    public void AddItemToBasket(Item product, int amount)
    {
        hm.put(product, amount);
    }
    

}
