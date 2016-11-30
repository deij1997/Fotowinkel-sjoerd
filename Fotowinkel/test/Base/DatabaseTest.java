/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class DatabaseTest
{
    private Database dab = new Database();
    
    public DatabaseTest()
    {
    }
    
    static double random = 0;
    @BeforeClass
    public static void setUpBaseClass() {
            random = Math.random();
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
    public void CheckregisterTest() throws SQLException
    {
        try{
         dab.RegisterPhotographer("koekjes"+random+"@fotograaf.be", "wachtwoord");

        }catch(Exception ex){
            Assert.fail("Random error! Run opnieuw!");
        }
        CheckLoginTest();

    }
    @Test
    public void CheckLoginTest() throws SQLException
    {
        Assert.assertTrue(dab.ValidateCredentials("koekjes"+ random+"@fotograaf.be", "wachtwoord"));
        Assert.assertFalse(dab.ValidateCredentials("koekjes"+ random+"@fotograaf.be", "wachtword1"));
        Assert.assertFalse(dab.ValidateCredentials("koekjes"+ random+"@fotograaf.be1", "wachtwoord1"));
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
        Assert.assertTrue(dab.GetPhoto("0") == null);
    }
    
    @Test
    public void CheckPhotoExist() throws SQLException
    {
        //Email
        Assert.assertTrue(dab.CheckIfPhotoBelongsToUser("1", "jessedeij@hotmail.com"));
        Assert.assertFalse(dab.CheckIfPhotoBelongsToUser("1" , "kojes@fotog12322f.besdaasd"));
        
        //Code
        Assert.assertTrue(dab.CheckIfPhotoBelongsToUser("1", "555"));
        Assert.assertFalse(dab.CheckIfPhotoBelongsToUser("1" , "1"));
    }
}
