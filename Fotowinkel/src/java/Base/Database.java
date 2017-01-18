/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.DatabaseBase.DBGlobals;
import Exceptions.RandomiserFail;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jesse
 */
public class Database
{
    public Database()
    {
        
    }

    /**
     * Returns all photos belonging to a vaguer customer hash
     * 
     * @param code
     * @return
     * @throws SQLException
     */
    public List<Photo> GetPhotos(String code) throws SQLException
    {
        return DBGlobals.D_BPHOTO_RETRIEVER.GetPhotos(code);
    }

    /**
     * Returns all photos belonging to a customer hash
     * 
     * @param Klantid
     * @return
     * @throws SQLException
     */
    public List<Photo> GetPhotosByKlantHashedId(String Klantid) throws SQLException
    {
        return DBGlobals.D_BPHOTO_RETRIEVER.GetPhotosByKlantHashedId(Klantid);
    }

    /**
     * Returns all the photos
     * @deprecated only for testing
     * @return
     * @throws SQLException
     */
    public List<Photo> GetAllPhotos() throws SQLException
    {
        return DBGlobals.D_BPHOTO_RETRIEVER.GetAllPhotos();
    }

    /**
     * Gets the photo with specific hash
     * 
     * @param hashId
     * @return
     * @throws SQLException
     */
    public Photo GetPhoto(String hashId) throws SQLException
    {
        return DBGlobals.D_BPHOTO_RETRIEVER.GetPhoto(hashId);
    }

    /**
     * Updates an array of photos in the database
     * 
     * @param photos
     * @throws SQLException
     */
    public void UpdatePhotos(List<Photo> photos) throws SQLException
    {
        DBGlobals.D_B_ITEM_HANDLER.UpdatePhotos(photos);
    }

    /**
     * Updates a photo with the new photo
     * 
     * @param photo
     * @throws SQLException
     */
    public void UpdatePhoto(Photo photo) throws SQLException
    {
        UpdatePhotos(Arrays.asList(photo));
    }

    /**
     * Validates the given email and password
     * 
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean ValidateCredentials(String email, String password) throws SQLException
    {
        return DBGlobals.D_BVERIFY.ValidateCredentials(email, password);
    }
    
    /**
     * Returns the name of an email
     * 
     * @param email
     * @return
     * @throws SQLException
     */
    public String GetName(String email) throws SQLException
    {
        return DBGlobals.D_BVERIFY.GetName(email);
    }

    /**
     * Returns the email belonging to a user hash
     * 
     * @param Hash
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public String GetEmailFromHash(String Hash) throws SQLException, Exception
    {
        return DBGlobals.D_BVERIFY.GetEmailFromHash(Hash);
    }

    /**
     * Checks if the user is a photographer
     * 
     * @param emailorcode
     * @return
     * @throws SQLException
     */
    public boolean CheckIfPhotographerExists(String emailorcode) throws SQLException
    {
        return DBGlobals.D_BVERIFY.CheckIfPhotographerExists(emailorcode);
    }

    /**
     * Checks if the user is an administrator
     * 
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean CheckIfAdministratorExists(String email) throws SQLException
    {
        return DBGlobals.D_BVERIFY.CheckIfAdministratorExists(email);
    }

    /**
     * Checks if the user is a customer
     * 
     * @param emailorcode
     * @return
     * @throws SQLException
     */
    public boolean CheckIfCustomerExists(String emailorcode) throws SQLException
    {
        return DBGlobals.D_BVERIFY.CheckIfCustomerExists(emailorcode);
    }

    /**
     * Checks if a photo belongs to specified user. Automatically checks if the user is a photographer or not
     * 
     * @param photocode
     * @param user
     * @return
     * @throws SQLException
     */
    public boolean CheckIfPhotoBelongsToUser(String photocode, String user) throws SQLException
    {
        return CheckIfPhotoBelongsToUser(photocode, user, CheckIfPhotographerExists(user));
    }

    /**
     * Checks if a photo belongs to specified user
     * 
     * @param photocode
     * @param user
     * @param isPhotographer whether the user is a photographer or not
     * @return
     * @throws SQLException
     */
    public boolean CheckIfPhotoBelongsToUser(String photocode, String user, boolean isPhotographer) throws SQLException
    {
        return DBGlobals.D_BVERIFY.CheckIfPhotoBelongsToUser(photocode, user, isPhotographer);
    }

    /**
     * Registers a photographer
     *
     * @param email the photographer email
     * @param password the password
     * @throws SQLException
     * @throws Exceptions.RandomiserFail
     */
    public void RegisterPhotographer(String email, String password) throws SQLException, RandomiserFail
    {
        DBGlobals.D_BUSER_HANDLER.RegisterPhotographer(email, password);
    }

    /**
     * Inserts an array of photos into the database
     * 
     * @param photos
     * @param customer
     * @param photograhper
     * @throws SQLException
     * @throws RandomiserFail
     */
    public void InsertPhotos(List<Photo> photos, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        DBGlobals.D_B_ITEM_HANDLER.InsertPhotos(photos, customer, photograhper);
    }

    /**
     * Inserts a photo into the database
     * 
     * @param photo
     * @param customer
     * @param photograhper
     * @throws SQLException
     * @throws RandomiserFail
     */
    public void InsertPhoto(Photo photo, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        InsertPhotos(Arrays.asList(photo), customer, photograhper);
    }

    /**
     * Inserts an order into the database
     * 
     * @param items
     * @param customer
     * @param name
     * @param lastname
     * @param country
     * @param city
     * @param street
     * @param housenr
     * @param postcode
     * @param paymentmethod
     * @throws SQLException
     * @throws RandomiserFail
     * @throws Exception
     */
    public void InsertOrder(List<ShoppingCartItem> items, String customer, String name, String lastname, String country, String city, String street, String housenr, String postcode, String paymentmethod) throws SQLException, RandomiserFail, Exception
    {
        DBGlobals.D_B_ITEM_HANDLER.InsertOrder(items, customer, name, lastname, country, city, street, housenr, postcode, paymentmethod);
    }

     /**
     * Returns all items from a photographer
     * 
     * @param email
     * @return
     * @throws SQLException
     */
    public List<PreviewItem> GetFotograafItems(String email) throws SQLException
    {
        return DBGlobals.D_B_ITEM_HANDLER.GetPhotographerItems(email);
    }

    /**
     * Returns all registered photographers
     * 
     * @return
     * @throws SQLException
     */
    public List<String> getAllPhotographer() throws SQLException
    {
        return DBGlobals.D_BUSER_HANDLER.getAllPhotographers();
    }
    
    /**
     * Returns the total profit made from all products
     * 
     * @return
     * @throws SQLException
     */
    public String getTotalProfit() throws SQLException
    {
        return Photo.GetPriceAsString(DBGlobals.D_B_ITEM_HANDLER.GetTotalSale());
    }
    
    /**
     * Returns the list of article sales
     * 
     * @return
     * @throws java.sql.SQLException
     */
    public List<PreviewArticle> GetArticleSales() throws SQLException
    {
        return DBGlobals.D_B_ITEM_HANDLER.GetArticleSales();
    }
    
    /**
     * Returns the list of articles
     * 
     * @return
     * @throws SQLException
     */
    public List<ListedArticle> GetArticles() throws SQLException
    {
        return DBGlobals.D_B_ITEM_HANDLER.GetArticles();
    }
}
