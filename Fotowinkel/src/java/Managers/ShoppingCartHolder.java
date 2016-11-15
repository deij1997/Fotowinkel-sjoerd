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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martijn
 */
public class ShoppingCartHolder
{

    HashMap carts = new HashMap();
    private static SecureRandom random = new SecureRandom();
    private static final ShoppingCartHolder INSTANCE = new ShoppingCartHolder();

    private ShoppingCartHolder()
    {
        if (INSTANCE != null)
        {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static ShoppingCartHolder getInstance()
    {
        return INSTANCE;
    }

    public void AddCart(ShoppingCart cart, String id)
    {
        carts.put(id, cart);
    }

    public ShoppingCart GetCartByID(String ID)
    {
        return (ShoppingCart) carts.get(ID);
    }

    public static String getRandomID()
    {
        return new BigInteger(130, random).toString(32);
    }

    public void checkIfNewCartNeeded(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        ShoppingCart cart;
        boolean f = false;
        if (cookies != null)
        {
            for (Cookie c : cookies)
            {
                if (c.getName().equals("cartID"))// cartID found, now we send the value
                {
                    f = true;
                    cart = ShoppingCartHolder.getInstance().GetCartByID(c.getValue());
                    if (cart == null)//checking if the cartID is one that we know
                    {
                        //disregard our current cartID and get a new one
                        NewCookie(response);
                    }
                    else
                    {
                        return;//cart exists
                    }
                    break;
                }
            }
        }
        if (!f)
        {
            //there are somehow no cookies for this domain, so we also give ourselves a new ID
            NewCookie(response);
        }

    }

    private void NewCookie(HttpServletResponse response)
    {
        //cartID does not exist, so we assign a random one
        Cookie cookie = new Cookie("cartID", ShoppingCartHolder.getRandomID());

        response.addCookie(cookie);
        //send this stuff to the cartManager
        ShoppingCart cart = new ShoppingCart();
        ShoppingCartHolder.getInstance().AddCart(cart, cookie.getValue());
    }

}
