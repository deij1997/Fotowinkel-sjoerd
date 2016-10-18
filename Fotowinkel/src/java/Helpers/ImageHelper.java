/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Rowan
 */
public class ImageHelper
{
    public static int CALCULATE_LIGHT_LEVEL = 404;
    static Color sepia = new Color(70, 50, 5);

    public static void main(String[] args) throws IOException
    {
        BufferedImage in = ImageIO.read(new File("E:\\Google Drive\\Google Photos\\singing hamster.png"));
        BufferedImage i = ToColourScale(in, sepia, 0);
        BufferedImage in2 = ImageIO.read(new File("E:\\Google Drive\\Google Photos\\singing hamster.png"));
        BufferedImage i2 = ToSepia(in2);

        try
        {
            File f = new File("E:\\School\\test\\colouradded.png");
            ImageIO.write(i, "jpg", f);
            File f2 = new File("E:\\School\\test\\sepiaadded.png");
            ImageIO.write(i2, "jpg", f2);
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    public static BufferedImage ToGrayScale(BufferedImage img)
    {
        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                //Get combined pixel colour
                int p = img.getRGB(x, y);
                //Get alpha and colours (can also be done through new Color()
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                //Get average (to make it grey)
                int avg = (r + g + b) / 3;
                //Set a new combined pixel colour
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                //Set its RGB colours
                img.setRGB(x, y, p);
            }
        }

        return img;
    }

    public static BufferedImage ToSepia(BufferedImage img)
    {
        return ToColourScale(ToGrayScale(img), sepia, 0);
    }

    /**
     * @param img the image to adjust
     * @param colour the colour to add over it
     * @param lightenwith lightens the image with an amount, to prevent the
     * image from going too dark (use CALCULATE_LIGHT_LEVEL to calculate on
     * default)
     *
     * @return an image converted to the single colour
     */
    public static BufferedImage ToColourScale(BufferedImage img, Color colour, int lightenwith)
    {
        int width = img.getWidth();
        int height = img.getHeight();

        if (lightenwith == CALCULATE_LIGHT_LEVEL)
        {
            int r = colour.getRed(), b = colour.getBlue(), g = colour.getGreen();
            lightenwith = r > b ? (b > g ? b / 2 : g / 2) : (r > g ? r / 2 : g / 2);

        }

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                //Get combined pixel colour
                int p = img.getRGB(x, y);
                //Get alpha and colours (can also be done through new Color()
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                r += colour.getRed() - lightenwith;
                if (r > 255)
                {
                    r = 255;
                }
                int g = (p >> 8) & 0xff;
                g += colour.getGreen() - lightenwith;
                if (g > 255)
                {
                    g = 255;
                }
                int b = p & 0xff;
                b += colour.getBlue() - lightenwith;
                if (b > 255)
                {
                    b = 255;
                }
                //Set a new combined pixel colour
                p = (a << 24) | (r << 16) | (g << 8) | b;
                //Set its RGB colours
                img.setRGB(x, y, p);
            }
        }

        return img;
    }
}
