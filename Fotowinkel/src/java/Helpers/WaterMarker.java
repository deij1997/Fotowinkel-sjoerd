package Helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martijn
 */
public final class WaterMarker {

    private WaterMarker() {//Java doesn't allow for static classes?
    }

    public static BufferedImage AddToImage(BufferedImage ImageToModify,
            BufferedImage WaterMark, int scale, boolean isYScale) throws IllegalArgumentException {
        if (ImageToModify == null || WaterMark == null) {
            throw new IllegalArgumentException("Uploaded image to modify or WaterMark is null.");
        }
        if (scale <= 0) {
            throw new IllegalArgumentException("Image scale cannot be 0 or negative.");
        }
        //Calculate new scale
        int y = isYScale ? scale : ImageToModify.getWidth() * scale / ImageToModify.getHeight();
        int x = !isYScale ? scale : ImageToModify.getHeight() * scale / ImageToModify.getWidth();

        BufferedImage r = toBufferedImage(ImageToModify.getScaledInstance(y, x,
                Image.SCALE_DEFAULT));
        //Add watermark
        BufferedImage bufferedImage = new BufferedImage(r.getWidth(), r.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        //g2d.drawImage(r, 0, 0, null);
        g2d.drawImage(WaterMark, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        return bufferedImage;
    }

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
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
