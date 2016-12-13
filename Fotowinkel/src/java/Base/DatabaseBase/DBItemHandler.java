/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.Encoder;
import Base.Item;
import Base.Photo;
import Base.PreviewItem;
import Exceptions.RandomiserFail;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Database class to handle photo and order insertions
 * 
 * @author Rowan
 */
public class DBItemHandler extends DBBase
{
    public void UpdatePhotos(List<Photo> photos) throws SQLException
    {
        setUpConnection();
        for (Photo p : photos)
        {
            String pquery = "UPDATE `item` SET `prijs`=" + p.GetPrice() + ", `title`=?,`description`=? WHERE `code`=?";
            dab.sendQuery(pquery, new String[]
                  {
                      p.GetTitle(), p.GetDescription(), p.GetCode()
            });
        }
        dab.close();
    }

    public void InsertPhotos(List<Photo> photos, String customer, String photograhper) throws SQLException, RandomiserFail
    {
        setUpConnection();
        if (!new DBVerify().CheckIfCustomerExists(customer))
        {
            new DBUserHandler().InsertCustomer(customer);
        }
        setUpConnection();

        for (Photo p : photos)
        {
            String pquery = "Insert into `item`(`code`, `klantid`, `prijs`, `fotograafid`, `title`, `description`) VALUES(?,?,?,?,?,?)";
            dab.sendQuery(pquery, new String[]
                  {
                      p.GetCode(), String.valueOf(GetIDFromHash((customer.contains("@") ? Encoder.GetHash(customer) : customer), false)), String.format(Locale.ENGLISH, "%.02f", p.GetPrice()),
                      String.valueOf(GetIDFromHash((photograhper.contains("@") ? Encoder.GetHash(photograhper) : photograhper), true)), p.GetTitle(), p.GetDescription()
            });
        }
        dab.close();
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
    
    /**
     * Returns all items from a photographer
     * 
     * @param email
     * @return
     * @throws SQLException
     */
    public List<PreviewItem> GetPhotographerItems(String email) throws SQLException
    {
        setUpConnection();
        List<PreviewItem> previewItems = new ArrayList<PreviewItem>();
        String query = "Select code, prijs, title, date From `item` Where fotograafid = (Select `id` from `fotograaf` where email = ?)";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                email
        });
        while (rs2.next())
        {
            Double prijs = rs2.getDouble("prijs");
            String code = rs2.getString("code");
            String title = rs2.getString("title");
            Date date = rs2.getDate("date");
            Item item = new Photo(prijs, code);
            PreviewItem previewItem = new PreviewItem(title, item, date);
            previewItems.add(previewItem);
        }
        dab.close();
        return previewItems;
    }
}
