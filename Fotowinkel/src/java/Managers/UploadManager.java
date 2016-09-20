/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Base.Encoder;
import Base.Photo;
import Exceptions.UploadFailed;
import java.util.ArrayList;

/**
 *
 * @author Rowan
 */
public class UploadManager
{

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
