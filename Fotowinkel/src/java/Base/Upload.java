/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;
import Exceptions.UploadFailed;
import Managers.Mail.MailManager;
import Managers.UploadManager;
import Managers.UserHandler;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Rowan
 */
public class Upload
{
    private final List<Photo> photos = new ArrayList<Photo>();
    private final String customer;

    private final String customerbody = "<h1>Hallo!</h1>\n"
                                        + "<p>Uw fotos staan klaar!</p>\n"
                                        + "<p>U kunt deze in de volgende link terugvinden:</p>\n"
                                        + "<p>%s</p>\n"
                                        + "<br />\n"
                                        + "<p>Met vriendelijke groet,</p>\n"
                                        + "<p>Team Fotowinkel Sjoerd</p>";
    private final String uploaderbody = "";

    /**
     * @deprecated Email is required.
     * @throws Exception
     */
    public Upload() throws Exception
    {
        throw new Exception("Email is required!");
    }

    public Upload(String customer)
    {

        this.customer = customer;
    }

    public void AddPhoto(Photo p)
    {
        photos.add(p);
    }

    public void AddPhotos(List<Photo> photos)
    {
        for (Photo p : photos)
        {
            AddPhoto(p);
        }
    }

    /**
     * Pushes the upload to the server, database and e-mails the customer
     *
     * @param request the server request
     * @throws Exceptions.UploadFailed
     * @throws java.sql.SQLException
     * @throws javax.mail.MessagingException
     * @throws java.io.UnsupportedEncodingException
     */
    public void Push(final HttpServletRequest request) throws UploadFailed, SQLException, MessagingException, UnsupportedEncodingException, Exception
    {
        final String user = UserHandler.getUser(request).getValue();

        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    //Get uploaderemail
                    String uploader = user;
                    String[] ids = UploadManager.UploadPhotos(photos, uploader, customer);
                    String id = ids[0].substring(0, ids[0].length() - 2);
                    EmailCustomer(request);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }.start();
    }

    public void EmailCustomer(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException, RandomiserFail, UnknownHostException
    {
        MailManager mm = new MailManager();
        InetAddress inetAddress;
        inetAddress = InetAddress.getLocalHost();
        String hostname = inetAddress.getHostName();
        String serverAddress = inetAddress.toString();
        serverAddress = serverAddress.replace(hostname, "");
        mm.Mail(customer, String.format(customerbody, "http://" + serverAddress + ":8080/Fotowinkel/Products.jsp?id=" + Encoder.GetHash(customer)), "Uw fotos staan klaar!");
    }

    public void EmailCustomer(String uploadID) throws MessagingException, UnsupportedEncodingException
    {
        MailManager mm = new MailManager();
        mm.Mail(customer, String.format(customerbody, "http://localhost:8080/Fotowinkel/Products.jsp?id=" + uploadID), "Uw fotos staan klaar!");
    }

    public void EmailUploader(HttpServletRequest request) throws UnsupportedEncodingException, MessagingException, Exception
    {
        String uploaderEmail;
        try
        {
            uploaderEmail = new Database().GetEmailFromHash(UserHandler.getUserAsString(request));
        }
        catch (Exception ex)
        {
            throw new Exception("No uploader found");
        }
        MailManager mm = new MailManager();
        mm.Mail(uploaderEmail, uploaderbody, "Uw fotos zijn geupload!");
    }
}
