/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Rowan
 */
public class Photo extends Item
{
    private static String DefaultImage = "at some point";
    private String previewLocation = "Image not found";
    private String fullLocation = "Image not found";
    private BufferedImage photo;

    //For the viewmanager; only used to get the location, code and price
    public Photo(double price, String code)
    {
        super(price, code);
        SetLocation();
    }

    /**
     * Creates a photo for the photographer to upload A code is not generated
     * with this
     *
     * @param price The price of the photo
     * @param photoBitmap The image of the photo
     */
    public Photo(double price, BufferedImage photoBitmap)
    {
        super(price, false);
        photo = photoBitmap;
        SetLocation();
    }

    /**
     * Uploads the photo to the server
     *
     * @throws Exceptions.UploadFailed
     */
    public void Upload() throws UploadFailed
    {
        if (this.photo != null)
        {
            //TODO
            //Save the photo to the server
            //Clear the photo to save memory
            photo = null;
        }
    }

    /**
     * Generates a link to full and preview location
     *
     * @deprecated Paths still not yet fixed
     */
    private void SetLocation()
    {
        //TODO
        //Get the server location and photo from it
        try
        {
        if (Photo.imagePresentAt("Preview Location"))
        {
            this.previewLocation = "Somewhere/From/Server/555-1.jpg";
        }
        if (Photo.imagePresentAt("Full location"))
        {
            this.fullLocation = "Somewhere/Else/555-1.png";
        }
        }
        catch (Exception e)
        {
            this.previewLocation = Photo.DefaultImage;
            this.fullLocation = Photo.DefaultImage;
        }
    }

    /**
     * Finds an image at a specified location
     *
     * @param location The relative location of the image
     * @throws java.lang.Exception
     * @return Whether a photo was at specified location
     */
    public static boolean imagePresentAt(final String location) throws Exception
    {
        boolean has = false;
        HttpURLConnection conn = null;
        URL url = new URL(location);
        
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("HEAD");
        
        String contentType = conn.getContentType();
        
        has = contentType.contains("image");

        return has;
    }

    public String GetFullLocation()
    {
        return this.fullLocation;
    }

    public String getPreviewLocation()
    {
        return this.previewLocation;
    }
}
