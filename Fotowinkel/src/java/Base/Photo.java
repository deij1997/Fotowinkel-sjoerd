/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;

/**
 *
 * @author Rowan
 */
public class Photo extends Item
{
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
     * @deprecated Paths still not yet fixed
     */
    private void SetLocation()
    {
        //TODO
        //Get the server location and photo from it
        this.previewLocation = "Somewhere/From/Server/555-1.jpg";
        this.fullLocation = "Somewhere/Else/555-1.png";
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
