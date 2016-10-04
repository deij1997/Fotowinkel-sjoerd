package Helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martijn
 */
public final class WaterMarker
{

    private WaterMarker()
    {//Java doesn't allow for static classes?
    }

    public static BufferedImage AddToImage(BufferedImage ImageToModify,
                                           BufferedImage WaterMark, int scale, boolean isYScale)
    {
        //Calculate new scale
        int y = isYScale ? scale : ImageToModify.getWidth() * scale / ImageToModify.getHeight();
        int x = !isYScale ? scale : ImageToModify.getHeight() * scale / ImageToModify.getWidth();

        BufferedImage r = (BufferedImage) ImageToModify.getScaledInstance(y, x, Image.SCALE_DEFAULT);
        //Add watermark
        BufferedImage bufferedImage = new BufferedImage(r.getWidth(), r.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(r, 0, 0, null);
        g2d.drawImage(WaterMark, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return bufferedImage;
    }

}
