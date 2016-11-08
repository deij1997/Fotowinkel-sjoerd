/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.UploadFailed;
import Managers.Mail.MailManager;
import Managers.UploadManager;
import Managers.UserHandler;
import java.io.UnsupportedEncodingException;
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
     * @param request the server request
     * @throws Exceptions.UploadFailed
     * @throws java.sql.SQLException
     * @throws javax.mail.MessagingException
     * @throws java.io.UnsupportedEncodingException
     */
    public void Push(HttpServletRequest request) throws UploadFailed, SQLException, MessagingException, UnsupportedEncodingException, Exception
    {
        //Get uploaderemail
        String uploaderEmail = new Database().GetEmailFromHash(UserHandler.getUserAsString(request));
        UploadManager.UploadPhotos(photos, uploaderEmail, customer);
        EmailCustomer();
    }
    
    public void EmailCustomer() throws MessagingException, UnsupportedEncodingException
    {
        //TODO: make something for the body
        MailManager mm = new MailManager();
        mm.Mail(customer, "", "Uw fotos staan klaar!");
    }
    
    public void EmailUploader() throws UnsupportedEncodingException, MessagingException
    {
        //TODO: make something for the body
        MailManager mm = new MailManager();
        mm.Mail("", "", "Uw fotos zijn geupload!");
    }
}
