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
    private UserHandler()
    {

    }

    /**
     * Sets how the user was logged in (through login form (true) or through the
     * customer page (false))
     *
     * @param loginform
     * @param request
     * @param response
     */
    public static void setLoginMethod(boolean loginform, HttpServletRequest request, HttpServletResponse response)
    {
        response.addCookie(new Cookie("lim", loginform ? "t" : "f"));
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

    public static String getUserAsString(HttpServletRequest request) throws SQLException
    {
        if (isUserLoggedIn(request))
        {

            if (userIsAdministrator(request))
            {
                return new Database().GetName(getUser(request).getValue());
            }

            return getUser(request).getValue();
        }
        else
        {
            return "";
        }
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

    public static boolean usedLoginForm(HttpServletRequest request)
    {
        if (request != null && request.getCookies() != null)
        {
            for (Cookie k : request.getCookies())
            {
                if (k.getName().equals("lim"))
                {
                    return "t".equals(k.getValue());
                }
            }
        }
        return false;
    }

    public static boolean userIsPhotographer(HttpServletRequest request) throws SQLException
    {
        //If the user logged in through the login form, it will never be a photographer
        if (!usedLoginForm(request))
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

    public static boolean userIsAdministrator(HttpServletRequest request) throws SQLException
    {
        //If the user logged in through the login form, it will never be a photographer
        if (!usedLoginForm(request))
        {
            return false;
        }

        //Else, get cookie, and verify if the logged in user is a photographer
        Cookie user = getUser(request);
        if (user == null)
        {
            return false;
        }

        if (!user.getValue().contains("@"))
        {
            return false;
        }

        //Check if this user is a photographer
        return new Database().CheckIfAdministratorExists(user.getValue());
    }
}
