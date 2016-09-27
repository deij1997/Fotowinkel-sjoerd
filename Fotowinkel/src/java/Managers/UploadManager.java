/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Encoder;
import Base.Photo;
import Exceptions.NotOfCorrectType;
import Exceptions.UploadFailed;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Rowan
 */
public class UploadManager
{
    public static ArrayList<Photo> CreatePhotosFromUploads(ArrayList<File> files) throws NotOfCorrectType
    {
        ArrayList<Photo> photos = new ArrayList<Photo>();
        
        //Create photos from files
        for (File file : files)
        {
            try
            {
                BufferedImage in = ImageIO.read(file);
                
                /*
                BufferedImage newImage = new BufferedImage(
                in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
                
                Graphics2D g = newImage.createGraphics();
                g.drawImage(in, 0, 0, null);
                g.dispose();
                */
                
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
