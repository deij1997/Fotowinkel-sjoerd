/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers.Mail;

import javax.mail.PasswordAuthentication;

/**
 *
 * @author Rowan
 */
public class MailDataStorage
{
    private final static String SENDER_PASSWORD = "Em72@Gmai111";
    public final static String SENDER_MAIL = "s21mplumbum@gmail.com";
    public final static String SENDER_NAME = "Fotowinkel Sjoerd";
    
    public final static String EMAIL_BODY = "%s";
    
    public static PasswordAuthentication getAuth()
    {
        return new PasswordAuthentication(SENDER_MAIL, SENDER_PASSWORD);
    }
}
