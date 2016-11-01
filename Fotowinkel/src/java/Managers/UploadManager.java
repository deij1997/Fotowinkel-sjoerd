/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Encoder;
import Base.Photo;
import Exceptions.NotOfCorrectType;
import Exceptions.RandomiserFail;
import Exceptions.UploadFailed;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Rowan
 */
public class UploadManager
{
    public static List<Photo> CreatePhotosFromUploads(List<InputStream> files) throws NotOfCorrectType
    {
        List<Photo> photos = new ArrayList<Photo>();
        
        //Create photos from files
        for (InputStream file : files)
        {
            try
            {
                BufferedImage in = ImageIO.read(file);
                
                photos.add(new Photo(Photo.DEFAULT_PRICE, in));
            }
            catch (Exception ex)
            {
                throw new NotOfCorrectType();
            }
        }

        return photos;
    }

    /**
     * Uploads a photo to the server
     *
     * @param photos The photos to upload
     * @return The code to give to the customer
     * @throws Exceptions.UploadFailed
     */
    public static String[] UploadPhotos(List<Photo> photos) throws UploadFailed
    {
        try {
            String[] usedCodes = Encoder.GenerateCodeStrings(photos.size());
            
            int index = 0;
            for (Photo p : photos)
            {
                p.SetCode(usedCodes[index++]);
                p.Upload();
            }
            
            return usedCodes;
        } catch (RandomiserFail ex) {
            Logger.getLogger(UploadManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new UploadFailed();
        }
    }
}
