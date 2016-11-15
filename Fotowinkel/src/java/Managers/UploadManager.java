/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Database;
import Base.Encoder;
import Base.Photo;
import Exceptions.NotOfCorrectType;
import Exceptions.RandomiserFail;
import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
     * @param useremail The email of the photographer
     * @param customer The email of the customer
     * @return The code to give to the customer
     * @throws Exceptions.UploadFailed
     * @throws java.sql.SQLException
     */
    public static String[] UploadPhotos(List<Photo> photos, String useremail, String customer) throws UploadFailed, SQLException
    {
        Database db = new Database();

        try
        {
            String[] usedCodes = Encoder.GenerateCodeStrings(photos.size());

            int index = 0;
            for (Photo p : photos)
            {
                p.SetCode(usedCodes[index++]);
                p.Upload();
            }
            db.InsertPhotos(photos, customer, useremail);

            return usedCodes;
        }
        catch (RandomiserFail ex)
        {
            throw new UploadFailed();
        }
    }
}
