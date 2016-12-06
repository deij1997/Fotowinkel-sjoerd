/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Database;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rowan
 */
public class UserHandler
{
    private static boolean loggedIn = false;

    private UserHandler()
    {

    }

    /**
     * Sets how the user was logged in (through login form (true) or through the
     * customer page (false))
     *
     * @param loginform
     */
    public static void setLoginMethod(boolean loginform)
    {
        loggedIn = loginform;
    }

    public static Cookie getUser(HttpServletRequest request)
    {
        Cookie user = null;
        if (request != null)
        {
            if (request.getCookies() != null)
            {
                for (Cookie k : request.getCookies())
                {
                    if (k.getName().equals("user"))
                    {
                        user = k;
                        break;
                    }
                }
            }
        }
        return user;
    }

    public static boolean isUserLoggedIn(HttpServletRequest request)
    {
        return (getUser(request) != null);
    }

    public static String getUserAsString(HttpServletRequest request)
    {
        return isUserLoggedIn(request) ? getUser(request).getValue() : "";
    }

    public static void setUser(String user, HttpServletRequest request, HttpServletResponse response)
    {
        if (user == null || user.isEmpty())
        {
            return;
        }

        boolean doSet = true;

        if (getUser(request) != null)
        {
            try
            {
                doSet = !userIsPhotographer(request);
            }
            catch (SQLException ex)
            {
                doSet = false;
            }
        }
        if (doSet)
        {
            response.addCookie(new Cookie("user", user));
        }
    }

    public static boolean usedLoginForm()
    {
        return loggedIn;
    }

    public static boolean userIsPhotographer(HttpServletRequest request) throws SQLException
    {
        //If the user logged in through the login form, it will never be a photographer
        if (!usedLoginForm())
        {
            return false;
        }

        //Else, get cookie, and verify if the logged in user is a photographer
        Cookie user = getUser(request);
        if (user == null)
        {
            return false;
        }

        //Check if this user is a photographer
        return new Database().CheckIfPhotographerExists(user.getValue());
    }
}
