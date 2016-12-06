/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class DatabaseTest
{
    private static final Database dab = new Database();
    private static final String TestEmail = "Yoyo@feli.sp";
    private static boolean hasRegistered = false;

    public DatabaseTest()
    {
        try
        {
            dab.RegisterPhotographer(TestEmail, "wachtwoord");
        }
        catch (SQLException ex)
        {
            if (!hasRegistered)
            {
                if (!ex.getMessage().contains("Duplicate"))
                {
                    System.out.println("[ERROR] A SQL exception occurred: \r\n" + ex.getMessage());
                }
                else
                {
                    System.out.println("SQL error ignored.");
                }
            }
            hasRegistered = true;
        }
        catch (RandomiserFail ex)
        {
            System.out.println("Big randomiser fail error");
        }
    }

    @Test
    public void GetPhotosTest() throws SQLException
    {
        Assert.assertTrue(dab.GetAllPhotos().size() > 0);
    }

    @Test
    public void GetSpecificPhotosTest() throws SQLException
    {
        Assert.assertTrue(dab.GetPhotos("555").size() > 0);
        Assert.assertTrue(dab.GetPhotos("1").isEmpty());
    }

    @Test
    public void GetSpecificHashedPhotosTest() throws SQLException
    {
        Assert.assertTrue(dab.GetPhotosByKlantHashedId("555").size() > 0);
        Assert.assertTrue(dab.GetPhotosByKlantHashedId("1").isEmpty());
    }

    @Test
    public void CheckLoginTest() throws SQLException
    {
        Assert.assertTrue(dab.ValidateCredentials(TestEmail, "wachtwoord"));
        Assert.assertFalse(dab.ValidateCredentials(TestEmail, "wachtword1"));
        Assert.assertFalse(dab.ValidateCredentials("o" + TestEmail, "wachtwoord1"));
    }

    @Test
    public void CheckPhotographerExist() throws SQLException
    {
        //Email
        Assert.assertTrue(dab.CheckIfPhotographerExists("koekjes@fotograaf.be"));
        Assert.assertFalse(dab.CheckIfPhotographerExists("koekjes@fotograa12321312f.be"));

        //Code
        Assert.assertTrue(dab.CheckIfPhotographerExists("4170105123291238825551810843879149"));
        Assert.assertFalse(dab.CheckIfPhotographerExists("1"));
    }

    @Test
    public void CheckCustomerExist() throws SQLException
    {
        //Email
        Assert.assertTrue(dab.CheckIfCustomerExists("jessedeij@hotmail.com"));
        Assert.assertFalse(dab.CheckIfCustomerExists("kojes@fotog12322f.besdaasd"));

        //Code
        Assert.assertTrue(dab.CheckIfCustomerExists("555"));
        Assert.assertFalse(dab.CheckIfCustomerExists("1"));
    }

    @Test
    public void CheckSpecificPhotoExist() throws SQLException
    {
        Assert.assertTrue(dab.GetPhoto("1") != null);
        Assert.assertTrue(dab.GetPhoto("-1") == null);
    }

    @Test
    public void CheckPhotoExist() throws SQLException
    {
        String s = "";
        //Email
        Assert.assertTrue(dab.CheckIfPhotoBelongsToUser("1", "jessedeij@hotmail.com", false));
        Assert.assertFalse(dab.CheckIfPhotoBelongsToUser("1", "kojes@fotog12322f.besdaasd"));

        //Code
        Assert.assertTrue(dab.CheckIfPhotoBelongsToUser("1", "555"));
        Assert.assertFalse(dab.CheckIfPhotoBelongsToUser("1", "1"));
    }
}
