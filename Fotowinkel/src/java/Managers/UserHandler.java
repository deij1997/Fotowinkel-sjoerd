/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rowan
 */
public class UserHandler
{
    public static Cookie getUser(HttpServletRequest request)
    {
        Cookie user = null;
        for (Cookie k : request.getCookies())
        {
            if (k.getName().equals("user"))
            {
                user = k;
                break;
            }
        }
        return user;
    }

    public static String getUserAsString(HttpServletRequest request)
    {
        return getUser(request).getValue();
    }

    public static void setUser(String user, HttpServletRequest request, HttpServletResponse response)
    {
        response.addCookie(new Cookie("user", user));
    }
}