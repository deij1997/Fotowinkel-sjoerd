/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 *
 * @author Rowan
 */
public class ErrorHandler
{
    private ErrorHandler(){}
    
    public static String getLanguageError(String language, Exception ehroar)
    {
        return language.toLowerCase().equals("nl")
               ? "<h1>Oh nee! :(</h1> \nEr ging iets fout, probeer het (later) opnieuw. <br /> \n<b>Error</b>: \n" + ehroar.getMessage()
               : "<h1>Oh no! :(</h1> \nA unexpected error has occurred, try again later. <br /> \n<b>Error</b>: \n" + ehroar.getMessage();
    }

    public static String getLanguageSpecifiedError(String language, String messageNL, String messageEN)
    {
        return language.equals("nl")
               ? "<h1>Oh nee! :(</h1> \n" + messageNL
               : "<h1>Oh no! :(</h1> \n" + messageEN;
    }
}
