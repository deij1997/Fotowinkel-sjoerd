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
import java.util.Locale;

/**
 *
 * @author Jesse
 */
public class Database
{
    final static String DRIVER = "com.mysql.jdbc.Driver";
    LowerDatabase dab;
    private static Object k = null;

    public Database()
    {
        try
        {
            if (k == null)
            {
                k = Class.forName(DRIVER).newInstance();
            }
            setUpConnection();
        }
        catch (Exception e)
        {
        }
    }

    public void setUpConnection() throws SQLException
    {
        dab = new LowerDatabase();
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

    public Photo GetPhoto(String hashId) throws SQLException
    {
        setUpConnection();
        Photo ret = null;
        String query = "Select * From `item` where code = ?";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                hashId
        });

        while (rs2.next())
        {
            ret = new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description"));
            break;
        }
        dab.close();

        return ret;
    }

    public void UpdatePhotos(List<Photo> photos) throws SQLException
    {
        setUpConnection();
        for (Photo p : photos)
        {
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
        String query = "Select * From `fotograaf` where `email` =?";

        
         ResultSet rs2 = dab.getData(query, new String[]
                            {
                                email
        });
         boolean ret = false;
        while (rs2.next())
        {
            if(Helpers.BCrypt.checkpw(password, rs2.getString("wachtwoord"))){
                ret = true;
                break;

            }
        }
        dab.close();
        dab.close();
        return ret;
    }

    public String GetEmailFromHash(String Hash) throws SQLException, Exception
    {
        if (Hash.contains("@"))
        {
            return Hash;
        }

        String who = "", ret = "";
        if (CheckIfCustomerExists(Hash))
        {
            who = "klant";
        }
        else
        {
            if (CheckIfPhotographerExists(Hash))
            {
                who = "fotograaf";
            }
            else
            {
                throw new Exception("Given email-ID " + Hash + " does not exist!");
            }
        }
        setUpConnection();
        String query = "Select email From `" + who + "` where hash=?";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                Hash
        });
        while (rs2.next())
        {
            ret = rs2.getString("email");
            break;
        }

        dab.close();

        if (ret.equals(""))
        {
            throw new Exception("Given email-ID " + Hash + " does not exist!");
        }
        return ret;
    }

    private int GetIDFromHash(String hash, boolean isPhotographer) throws SQLException
    {
        int ret = -1;
        setUpConnection();
        String query = "Select id from " + (isPhotographer ? "`fotograaf`" : "`klant`") + " where hash=?";
        ResultSet r = dab.getData(query, new String[]
                          {
                              hash
        });

        while (r.next())
        {
            ret = r.getInt("id");
            break;
        }
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
        dab.close();
        return ret;
    }

    public boolean CheckIfPhotoBelongsToUser(String photocode, String user) throws SQLException
    {
        return CheckIfPhotoBelongsToUser(photocode, user, CheckIfPhotographerExists(user));
    }

    public boolean CheckIfPhotoBelongsToUser(String photocode, String user, boolean isPhotographer) throws SQLException
    {
        setUpConnection();

        String query = "Select id, hash, email from " + (isPhotographer ? "`fotograaf`" : "`klant`") + " where id = (select `klantid` from `item` where code = ?)";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                photocode
        });
        boolean belongsToAUser = false;
        while (rs2.next())
        {
            if (String.valueOf(rs2.getInt("id")).equals(user) || rs2.getString("hash").equals(user) || rs2.getString("email").equals(user))
            {
                belongsToAUser = true;
                break;
            }
        }
        dab.close();

        return belongsToAUser;
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
        InsertPhotographer(email, password);
    }

    /**
     * Inserts a photographer into the database
     *
     * @param email the photographer email to add
     * @param password
     * @throws SQLException
     * @throws Exceptions.RandomiserFail
     */
    private void InsertPhotographer(String email, String password) throws SQLException, RandomiserFail
    {
        setUpConnection();
        String query = "Insert into `fotograaf`(`wachtwoord`, `email`, `hash`) VALUES(?,?,?)";
        String salt = Helpers.BCrypt.gensalt();
        String[] parameters = new String[]
        {
            Helpers.BCrypt.hashpw(password, salt)
            , email, Encoder.GetHash(email)
        };
        dab.sendQuery(query, parameters);
        dab.close();
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
        String query = "Insert into `klant`(`hash`, `email`) VALUES (?,?)";
        String[] parameters = new String[]
        {
            Encoder.GetHash(email), email
        };
        dab.sendQuery(query, parameters);
    }

    public void InsertPhotos(List<Photo> photos, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        setUpConnection();
        if (!CheckIfCustomerExists(customer))
        {
            InsertCustomer(customer);
        }
        setUpConnection();

        for (Photo p : photos)
        {
            String pquery = "Insert into `item`(`code`, `klantid`, `prijs`, `fotograafid`, `title`, `description`) VALUES(?,?,?,?,?,?)";
            dab.sendQuery(pquery, new String[]
                  {
                      //TODO Please note that price can differ in dots and commas, depending on OS language
                      p.code, String.valueOf(GetIDFromHash((customer.contains("@") ? Encoder.GetHash(customer) : customer), false)), String.format(Locale.ENGLISH, "%.02f", p.price),
                      String.valueOf(GetIDFromHash((photograhper.contains("@") ? Encoder.GetHash(photograhper) : photograhper), true)), p.GetTitle(), p.GetDescription()
            });
        }
        dab.close();
    }

    public void InsertPhoto(Photo photo, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        InsertPhotos(Arrays.asList(photo), customer, photograhper);
    }

    public void InsertOrder(List<String> items, String customer, String name, String lastname, String country, String city, String street, String housenr, String postcode, String paymentmethod) throws SQLException, RandomiserFail, Exception
    {
        String query = "";
        String[] parameters;

        if (items.isEmpty())
        {
            return;
        }

        setUpConnection();
        //Insert order
        query = "Insert into `order` (`klantid`, `betaalmethode`) VALUES (?,?)";
        parameters = new String[]
        {
            String.valueOf(GetIDFromHash(customer, false)), paymentmethod
        };
        dab.sendQuery(query, parameters);
        ResultSet IDs = dab.getMutatedData();
        int ID = 0;
        if (IDs.next())
        {
            ID = IDs.getInt("id");
        }
        if (ID == 0)
        {
            throw new Exception("No order could be inserted");
        }
        setUpConnection();

        //Insert bestelling
        for (String i : items)
        {
            query = "Insert into `bestelling` (`itemid`, `orderid`) VALUES (?,?)";
            parameters = new String[]
            {
                String.valueOf(i), String.valueOf(ID)
            };
            dab.sendQuery(query, parameters);
        }
        setUpConnection();

        //Insert adress with order
        query = "Insert into `adresgegevens`(`orderid`, `voornaam`, `achternaam`, `huisnr`, `straat`, `woonplaats`, `landcode`, `postcode`) VALUES (?,?,?,?,?,?,?,?)";
        parameters = new String[]
        {
            String.valueOf(ID), name, lastname, housenr, street, city, country, postcode
        };
        dab.sendQuery(query, parameters);
        dab.close();
    }
}
