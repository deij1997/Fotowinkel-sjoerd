package thumbtest;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martijn
 */
public final class ThumbnailMaker {

    private ThumbnailMaker() {//Java doesn't allow for static classes?
    }

    public static BufferedImage MakeThumbnail(BufferedImage ImageToModify, 
            BufferedImage WaterMark, int scale, boolean isYScale) {
        //first we resize the image
        int y;
        int x;
        if (isYScale) {
            y = scale;
            x =   ImageToModify.getHeight() * scale/ImageToModify.getWidth();
                    System.out.println("x: "+x+" y: "+y);
        } else {
            x = scale;
            y = ImageToModify.getWidth() * scale/ImageToModify.getHeight();
        }

        BufferedImage r = toBufferedImage(ImageToModify.getScaledInstance(y,x, 
                Image.SCALE_DEFAULT));
        //now we watermark it

        BufferedImage bufferedImage = new BufferedImage(r.getWidth(),
                r.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(r, 0, 0, null);
        g2d.drawImage(WaterMark, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        

        return bufferedImage;
    }
    
    /**
 * Converts a given Image into a BufferedImage
 *
 * @param img The Image to be converted
 * @return The converted BufferedImage
 */
static BufferedImage toBufferedImage(Image img)
{
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
}

}