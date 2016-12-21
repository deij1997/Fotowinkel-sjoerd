/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Rowan
 */
public class ImageUtils
{
    public static final int CALCULATE_WRAP_LOSS = -1;

    public static void main(String[] args) throws IOException
    {
        BufferedImage in = ImageIO.read(new File("E:\\Google Drive\\Google Photos\\singing hamster.png"));
        BufferedImage i = wrapImage(in, 1, true, 0);

        try
        {
            File f = new File("E:\\School\\test\\biepboop.png");
            ImageIO.write(i, "jpg", f);
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Wraps an image around the edge it should wrap (much like a frame on a
     * mug)
     *
     * @param image The image to wrap
     * @param strength The strength of the wrap
     * @param inWidth Whether the width should wrap (true), or the height
     * (false)
     * @param lossamount How much width, or height, is allowed to be lost from
     * the compression. This will also force it off if it's too much
     * @return The wrapped image
     */
    public static BufferedImage wrapImage(BufferedImage image, double strength, boolean inWidth, int lossamount)
    {
        int w = image.getWidth(), h = image.getHeight();
        double bulgeStrength = strength;
        BufferedImage ret = new BufferedImage(w, h, image.getType());

        int cx = w / 2;
        int cy = h / 2;
        int bulgeRadius = inWidth ? cy : cx;

        for (int x = 0; x < w; x++)
        {
            for (int y = 0; y < h; y++)
            {
                //Calculate deltas
                int dx = x - cx;
                int dy = y - cy;
                double distanceSquared = dx * dx + dy * dy;
                //Save x and y values to revoke pointers
                int sx = x;
                int sy = y;
                if (distanceSquared < bulgeRadius * bulgeRadius)
                {
                    //Distance to affect
                    double distance = Math.sqrt(distanceSquared);
                    //Radius
                    double r = distance / bulgeRadius;
                    //??
                    double a = Math.atan2(dy, dx);
                    double rn = Math.pow(r, bulgeStrength) * distance;
                    double newX = rn * Math.cos(a) + cx;
                    double newY = rn * Math.sin(a) + cy;
                    sx += (newX - x);
                    sy += (newY - y);
                }
                if (sx >= 0 && sx < w && sy >= 0 && sy < h)
                {
                    int rgb = image.getRGB(sx, sy);
                    ret.setRGB(x, y, rgb);
                }
            }
        }

        //Give it the loss
        if (lossamount != 0)
        {
            int newW = w - (inWidth ? lossamount : 0);
            int newH = h - (!inWidth ? lossamount : 0);
            ret = stretchImage(ret, newW, newH);
        }

        return ret;
    }

    /**
     * Stretches a given image into a new resolution
     *
     * @param img The image to stretch
     * @param newW The width to stretch the image to
     * @param newH The height to stretch the image to
     * @return The new image
     */
    public static BufferedImage stretchImage(BufferedImage img, int newW, int newH)
    {
        return stretchImage(img, newW, newH, 0, 0);
    }

    /**
     * Stretches a given image into a new resolution, where the wrap loss is
     * auto-calculated
     *
     * @param img The image to stretch
     * @param newW The width to stretch the image to
     * @param newH The height to stretch the image to
     * @param strength How strong the image should wrap
     * @return The new image
     */
    public static BufferedImage stretchImage(BufferedImage img, int newW, int newH, double strength)
    {
        return stretchImage(img, newW, newH, strength, CALCULATE_WRAP_LOSS);
    }

    /**
     * Stretches a given image into a new resolution with specified wrap
     * settings
     *
     * @param img The image to stretch
     * @param newW The width to stretch the image to
     * @param newH The height to stretch the image to
     * @param strength How strong the image should wrap
     * @param wrapamount How much the image should wrap
     * @return The new image
     */
    public static BufferedImage stretchImage(BufferedImage img, int newW, int newH, double strength, int wrapamount)
    {
        if (strength != 0)
        {
            //Calculate the new ratio
            double newratio = newW / newH;
            double currentratio = img.getWidth() / img.getHeight();
            //If the newratio is bigger than the currentratio, this means that 
            //   the height has gotten relatively bigger
            //   thus meaning that it should wrap in height
            boolean inWidth = !(newratio > currentratio);

            if (wrapamount == CALCULATE_WRAP_LOSS)
            {
                double difference = (newratio / currentratio) * (inWidth ? newW : newH);
                wrapamount = (int) difference;
            }

            //Wrap the side overflow. img = that
            img = wrapImage(img, strength, inWidth, wrapamount);
        }

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    /**
     * Copies an image over another image
     *
     * @param bottom The image that's below the top image
     * @param top The image that's on top of the bottom image
     * @param minx The x1 position of the top image on the bottom image
     * @param miny The y1 position of the top image on the bottom image
     * @param maxx The x2 position of the top image on the bottom image
     * @param maxy The y2 position of the top image on the bottom image
     * @param strength The strength of the wrap
     * @param lossamount The amount of pixels the image should lose from
     * wrapping
     * @return An image made of the two other images
     */
    public static BufferedImage copyOverImage(BufferedImage bottom, BufferedImage top, int minx, int miny, int maxx, int maxy, double strength, int lossamount)
    {
        if (bottom == null || top == null)
        {
            throw new IllegalArgumentException("Bottom or top image is null");
        }
        if (minx < 0 || miny < 0 || maxx > bottom.getWidth() || maxy > bottom.getHeight())
        {
            throw new IllegalArgumentException("Top image cannot be out of bounds of bottom image");
        }

        int newW = Math.abs(maxx - minx);
        int newH = Math.abs(maxy - miny);

        top = stretchImage(top, newW, newH, strength, lossamount);

        //Copy over Bottom
        BufferedImage bufferedImage = new BufferedImage(bottom.getWidth(), bottom.getHeight(), bottom.getType());
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(bottom, 0, 0, null);
        g2d.drawImage(top, minx, miny, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return bufferedImage;
    }

    /**
     * Copies an image over another image
     *
     * @param bottom The image that's below the top image
     * @param top The image that's on top of the bottom image
     * @param minx The x1 position of the top image on the bottom image
     * @param miny The y1 position of the top image on the bottom image
     * @param maxx The x2 position of the top image on the bottom image
     * @param maxy The y2 position of the top image on the bottom image
     * @param strength The strength of the wrap wrapping
     * @return An image made of the two other images
     */
    public static BufferedImage copyOverImage(BufferedImage bottom, BufferedImage top, int minx, int miny, int maxx, int maxy, double strength)
    {
        return copyOverImage(bottom, top, minx, miny, maxx, maxy, strength, CALCULATE_WRAP_LOSS);
    }

    /**
     * Copies an image over another image
     *
     * @param bottom The image that's below the top image
     * @param top The image that's on top of the bottom image
     * @param minx The x1 position of the top image on the bottom image
     * @param miny The y1 position of the top image on the bottom image
     * @param maxx The x2 position of the top image on the bottom image
     * @param maxy The y2 position of the top image on the bottom image
     * @return An image made of the two other images
     */
    public static BufferedImage copyOverImage(BufferedImage bottom, BufferedImage top, int minx, int miny, int maxx, int maxy)
    {
        return copyOverImage(bottom, top, minx, miny, maxx, maxy, 0, 0);
    }
}
