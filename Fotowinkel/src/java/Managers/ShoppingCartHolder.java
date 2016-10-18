/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

/**
 *
 * @author Martijn
 */
public class ShoppingCartHolder {
    
    HashMap carts = new HashMap();
    private static SecureRandom random = new SecureRandom();
    private static final ShoppingCartHolder INSTANCE = new ShoppingCartHolder();

    private ShoppingCartHolder() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    
     public static ShoppingCartHolder getInstance() {
        return INSTANCE;
    }
     
    
    public void AddCart(ShoppingCart cart, String id)
    {
        carts.put(id, cart);
    }
    
    public ShoppingCart GetCartByID(String ID)
    {
        return (ShoppingCart)carts.get(ID);
    }
    
    public static String getRandomID()
    {
         return new BigInteger(130, random).toString(32);
    }
    
    
}
