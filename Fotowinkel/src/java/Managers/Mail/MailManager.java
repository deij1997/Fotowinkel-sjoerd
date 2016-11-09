/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers.Mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rowan
 */
public class MailManager
{
    private final Properties props = new Properties();
    
    private Session session;
    private Message message;
    
    
    public MailManager()
    {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }
    
    private void PrepareMail()
    {
        session = Session.getInstance(props, new javax.mail.Authenticator()
                                      {
                                          @Override
                                          protected PasswordAuthentication getPasswordAuthentication()
                                          {
                                              return MailDataStorage.getAuth();
                                          }
                                      });

        message = new MimeMessage(session);
    }

    public void Mail(String toemail, String content, String subject) throws MessagingException, UnsupportedEncodingException
    {
        PrepareMail();
        
        message.setFrom(new InternetAddress(MailDataStorage.SENDER_MAIL, MailDataStorage.SENDER_NAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail));
        message.setSubject(subject);

        String body = MailDataStorage.EMAIL_BODY;

        //message.setText(String.format(body, content));
        message.setContent(body.replace("%s", content), "text/html; charset=utf-8");
        message.saveChanges();
        Transport.send(message);
    }
}
