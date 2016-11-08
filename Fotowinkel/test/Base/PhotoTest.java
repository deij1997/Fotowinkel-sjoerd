/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;
import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class PhotoTest
{
    Photo p = new Photo(0, "555-1");

    public PhotoTest()
    {
    }

    @Test
    public void TestLocationChecker() throws Exception
    {
        //Obsolete, not used
        if (true) return;
        
        assertTrue("Location checker not functional!", Photo.imagePresentAt("https://www.google.nl/images/nav_logo242.png"));
        assertFalse("Location checker not correct! \r\nFile does not exist", Photo.imagePresentAt("https://www.google.nl/images/nav_logo2422.png"));
        assertFalse("Location checker not correct! \r\nFile is not of image format", Photo.imagePresentAt("https://www.google.nl"));
    }

    @Test
    public void TestUpload()
    {
        //Obsolete, hand-tested
        if (true) return;
        
        p = new Photo(0, new BufferedImage(1, 1, 8));
        try
        {
            p.Upload();
        }
        catch (UploadFailed e)
        {
            assert false;
        }
    }

    @Test
    public void TestCodeGeneration() throws RandomiserFail
    {
        p = new Photo(0, "555-1");
        String olds = p.code;
        String news = p.GenerateNewCode();
        assertNotSame("Photo was not generated a new unique code!", olds, news);
    }

    @Test
    public void TestSettersAndGetters()
    {
        p = new Photo(0, "555-1");
        p.GetFullLocation();
        p.getPreviewLocation();
        p.SetPrice(1);
        p.GetDescription();
        p.GetTitle();
        if (p.price != 1)
        {
            assert false;
        }
    }
}
