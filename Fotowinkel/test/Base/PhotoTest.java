/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.UploadFailed;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rowan
 */
public class PhotoTest
{
    Photo p;

    public PhotoTest()
    {
        p = new Photo(0, new BufferedImage(1, 1, 8));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void TestLocationChecker() throws Exception
    {
        assertTrue("Location checker not functional!", Photo.imagePresentAt("https://www.google.nl/images/nav_logo242.png"));
        assertFalse("Location checker not correct! \r\nFile does not exist", Photo.imagePresentAt("https://www.google.nl/images/nav_logo2422.png"));
        assertFalse("Location checker not correct! \r\nFile is not of image format", Photo.imagePresentAt("https://www.google.nl"));
    }

    @Test
    public void TestUpload()
    {
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
    public void TestCodeGeneration()
    {
        String olds = p.code;
        String news = p.GenerateNewCode();
        assertNotSame("Photo was not generated a new unique code!", olds, news);
    }

    @Test
    public void TestSettersAndGetters()
    {
        p.GetFullLocation();
        p.getPreviewLocation();
        p.SetPrice(1);
        if (p.price != 1)
        {
            assert false;
        }
    }
}
