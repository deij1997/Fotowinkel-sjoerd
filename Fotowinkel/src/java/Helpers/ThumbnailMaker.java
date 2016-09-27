package Helpers;

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
            BufferedImage Thumbnail, int scale, boolean isYScale) {
        //first we resize the image
        int y;
        int x;
        if (isYScale) {
            y = scale;
            x = ImageToModify.getWidth(null) * (y / scale);
        } else {
            x = scale;
            y = ImageToModify.getWidth(null) * (x / scale);
        }
        BufferedImage r = (BufferedImage) ImageToModify.getScaledInstance(x, y, 
                Image.SCALE_DEFAULT);
        //now we watermark it

        BufferedImage bufferedImage = new BufferedImage(r.getWidth(),
                r.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.drawImage(r, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return r;
    }

}