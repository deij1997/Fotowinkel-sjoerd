/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jesse
 */
public class Database
{
    final static String DRIVER = "com.mysql.jdbc.Driver";
    LowerDatabase dab;

    public Database()
    {
        try
        {
            Class.forName(DRIVER).newInstance();
            setUpConnection();
        }
        catch (Exception e)
        {
        }
    }

    public void setUpConnection() throws SQLException
    {
        if (dab == null)
        {
            dab = new LowerDatabase();
        }
    }

    public List<Photo> GetPhotos(String code) throws SQLException
    {
        List<Photo> photos = new ArrayList<Photo>();
        if (code == null || code.isEmpty())
        {
            return photos;
        }

        setUpConnection();
        String query = "Select * From `item` Where klantid Like ?";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                code + "%"
        });
        while (rs2.next())
        {
            photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
        }
        dab.close();
        return photos;

    }

    public List<Photo> GetPhotosByKlantHashedId(String Klantid) throws SQLException
    {
        setUpConnection();
        List<Photo> photos = new ArrayList<Photo>();
        String query = "Select * From `item` Where klantid = (Select `id` from `klant` where hash = ?)";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                Klantid
        });
        while (rs2.next())
        {
            photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
        }
        dab.close();
        return photos;

    }

    public List<Photo> GetAllPhotos() throws SQLException
    {
        setUpConnection();
        List<Photo> photos = new ArrayList<Photo>();
        String query = "Select * From `item`";
        ResultSet rs2 = dab.getData(query, null);
        while (rs2.next())
        {
            photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
        }
        dab.close();

        return photos;
    }

    public void UpdatePhotos(List<Photo> photos) throws SQLException
    {
        setUpConnection();
        for (Photo p : photos)
        {
            //String query = "UPDATE `item` SET `prijs`=" + p.price + ", `title`=" + p.GetTitle() + ",`description`=" + p.GetDescription() + " WHERE `code`=" + p.code;
            String pquery = "UPDATE `item` SET `prijs`=" + p.price + ", `title`=?,`description`=? WHERE `code`=?";
            dab.sendQuery(pquery, new String[]
                  {
                      p.GetTitle(), p.GetDescription(), p.code
            });
        }
        dab.close();
    }

    public void UpdatePhoto(Photo photo) throws SQLException
    {
        UpdatePhotos(Arrays.asList(photo));
    }

    public boolean ValidateCredentials(String email, String password) throws SQLException
    {
        setUpConnection();
        String query = "Select * From `fotograaf` where email =?, wachtwoord =?";
        dab.sendQuery(query, new String[]
              {
                  email, password
        });
        boolean ret = dab.hasFoundData();
        dab.close();
        return ret;
    }

    public boolean CheckIfPhotographerExists(String emailorcode) throws SQLException
    {
        setUpConnection();
        String query = "Select id From `fotograaf` where " + (emailorcode.contains("@") ? "email=?" : "hash=?");
        dab.sendQuery(query, new String[]
              {
                  emailorcode
        });
        boolean ret = dab.hasFoundData();
        dab.close();
        return ret;
    }

    public boolean CheckIfCustomerExists(String emailorcode) throws SQLException
    {
        setUpConnection();
        String query = "Select * From `klant` where " + (emailorcode.contains("@") ? "email=?" : "id=?");
        dab.sendQuery(query, new String[]
              {
                  emailorcode
        });
        boolean ret = dab.hasFoundData();
        return ret;
    }

    public boolean CheckIfPhotoBelongsToUser(String photocode, String user) throws SQLException
    {

        setUpConnection();
        String query = "Select id, hash from `klant` where " + (user.contains("@") ? "email" : "id") + " = (select `klantid` from `item` where code = ?)";
        dab.sendQuery(query, new String[]
              {
                  photocode
        });
        boolean belongsToAUser = dab.hasFoundData();

        ResultSet rs2 = dab.getData();
        if (belongsToAUser)
        {
            belongsToAUser = false;
            while (rs2.next())
            {
                if (String.valueOf(rs2.getInt("id")).equals(user) || rs2.getString("hash").equals(user))
                {
                    belongsToAUser = true;
                    break;
                }
            }
        }
        dab.close();

        return belongsToAUser;
    }

    /**
     * Inserts a customer into the database
     *
     * @param email the customer email to add
     * @throws SQLException
     * @throws Exceptions.RandomiserFail
     */
    private void InsertCustomer(String email) throws SQLException, RandomiserFail
    {
        setUpConnection();
        String query = "Insert into `klant`(`id`, `email`) VALUES (?,?)";
        String[] parameters = new String[]
        {
            Encoder.GetHash(email), email
        };
        dab.sendQuery(query, parameters);
    }

    public void InsertPhotos(List<Photo> photos, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        if (!CheckIfCustomerExists(customer))
        {
            InsertCustomer(customer);
        }

        for (Photo p : photos)
        {
            String pquery = "Insert into `item`(`code`, `klantid`, `prijs`, `fotograafid`, `title`, `description`) VALUES(?,?,?,?,?,?)";
            dab.sendQuery(pquery, new String[]
                  {
                      //TODO Please note that price can differ in dots and commas, depending on OS language
                      p.code, (customer.contains("@") ? Encoder.GetHash(customer) : customer), String.format(".2%f%n", p.price),
                      (photograhper.contains("@") ? Encoder.GetHash(photograhper) : photograhper), p.GetTitle(), p.GetDescription()
            });
        }
        dab.close();
    }

    public void InsertPhoto(Photo photo, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        InsertPhotos(Arrays.asList(photo), customer, photograhper);
    }
}
