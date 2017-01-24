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
public class LanguageHandler
{
    private LanguageHandler()
    {

    }
    
    public static void setLanguage(String language, HttpServletRequest request, HttpServletResponse response)
    {
        response.addCookie(new Cookie("language", language));
    }

    public static String getLanguage(HttpServletRequest request)
    {
        String lang = "en";
        if (request != null)
        {
            if (request.getCookies() != null)
            {
                for (Cookie k : request.getCookies())
                {
                    if (k.getName().equals("language"))
                    {
                        lang = k.getValue();
                        break;
                    }
                }
            }
        }
        return lang;
    }
}
