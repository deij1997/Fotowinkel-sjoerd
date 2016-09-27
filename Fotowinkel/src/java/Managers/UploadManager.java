/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Encoder;
import Base.Photo;
import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.apache.tomcat.util.http.fileupload.FileUpload;

/**
 *
 * @author Rowan
 */
public class UploadManager
{
    
    public static String[] CreatePhotosFromUploads(ArrayList<FileUpload> files)
    {
        //TODO
        //Create photos from files
        for (FileUpload file : files)
        {   
            //BufferedImage k = file;
            //Photo toadd = new Photo(5.00, k);
        }
        
        throw new UnsupportedOperationException("Not yet implemented");
    }    

    /**
     * Uploads a photo to the server
     *
     * @param photos The photos to upload
     * @return The code to give to the customer
     * @throws Exceptions.UploadFailed
     */
    public static String[] UploadPhotos(ArrayList<Photo> photos) throws UploadFailed
    {
        String[] usedCodes = Encoder.GenerateCodeStrings(photos.size());
        for (Photo p : photos)
        {
            p.Upload();
        }

        return usedCodes;
    }
}
