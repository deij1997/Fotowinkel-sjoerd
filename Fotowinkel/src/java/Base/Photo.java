/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.UploadFailed;
import Helpers.WaterMarker;
import Servlets.OrderServlet;
import Servlets.UploadServlet;
import static Servlets.UploadServlet.WATERMARK_LOCATION;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Rowan
 */
public class Photo extends Item
{
    public static final double DEFAULT_PRICE = 5.00;
    private static BufferedImage WaterMarkImage;
    private static final String MISSING_LOCATION = "???";
    private String previewLocation = "Image not found";
    private String fullLocation = "Image not found";
    private BufferedImage photo;
    private String title;
    private String description;

    //For the viewmanager; only used to get the location, code and price
    public Photo(double price, String code)
    {
        super(price, code);

        SetLocation();
    }

    //For the viewmanager; only used to get the location, code and price
    public Photo(double price, String code, String title, String description)
    {
        super(price, code);
        title = this.title;
        description = this.description;
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
        super(price, true);
        if (WaterMarkImage == null)
        {
            //Get new watermark image
            File file = new File(WATERMARK_LOCATION);
            try
            {
                WaterMarkImage = ImageIO.read(new FileInputStream(file));
            }
            catch (IOException e)
            {
                System.out.println("[ERROR] Could not retrieve watermark image");
            }
        }
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
            //Save the photo to the server
            try
            {
                //Save the full to FULL_UPLOAD_DIRECTORY
                File fulloutputfile = new File(UploadServlet.FULL_UPLOAD_DIRECTORY + "/" + code + ".png");
                fulloutputfile.createNewFile();
                ImageIO.write(photo, "png", fulloutputfile);

                //Crop and make the preview, then save it to PREVIEW_UPLOAD_DIRECTORY
                photo = WaterMarker.AddToImage(photo, WaterMarkImage, 500, true);
                File prevoutputfile = new File(UploadServlet.PREVIEW_UPLOAD_DIRECTORY + "/" + code + ".jpg");
                prevoutputfile.createNewFile();
                ImageIO.write(photo, "jpg", prevoutputfile);
                //Clear the photo to save memory
                photo = null;
                SetLocation();
            }
            catch (IOException ex)
            {
                throw new UploadFailed(ex.getMessage());
            }
        }
    }

    /**
     * Generates a link to full and preview location
     */
    private void SetLocation()
    {
        //Get the server location and photo from it
        try
        {
            if (Photo.imagePresentAt(OrderServlet.PREVIEW_UPLOAD_DIRECTORY + "\\" + code + ".jpg"))
            {
                this.previewLocation = "previewimages" + "/" + code + ".jpg";
            }
            if (Photo.imagePresentAt(OrderServlet.FULL_UPLOAD_DIRECTORY + "\\" + code + ".png"))
            {
                this.fullLocation = "fullimages" + "/" + code + ".png";
            }
        }
        catch (Exception e)
        {
            this.previewLocation = Photo.MISSING_LOCATION;
            this.fullLocation = Photo.MISSING_LOCATION;
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
        URL url = null;
        try
        {
            url = new URL(location);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");

            String contentType = conn.getContentType();

            has = contentType.contains("image");
        }
        catch (Exception e)
        {
            // it's probably a file
            File f = new File(location);
            return f.exists();
        }

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

    public String GetTitle()
    {
        return this.title;
    }

    public String GetDescription()
    {
        return this.description;
    }
}
