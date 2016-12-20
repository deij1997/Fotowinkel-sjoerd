/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.Encoder;
import Base.Item;
import Base.ItemSalesInfo;
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
        String query = "Select `code`, `prijs`, `title`, `date`, `naam`, `bedrukt`, `verzonden`, `totaal`, `totaalprijs` from `item` \n"
                       + "INNER JOIN \n"
                       + "(\n"
                       + "	SELECT `itemid`, `naam`, COUNT(`naam`) as `totaal`,\n"
                       + "		SUM(`bedrukt`) as `bedrukt`, \n"
                       + "		SUM(`verzonden`) as `verzonden`,\n"
                       + "		SUM(`prijs`) as `totaalprijs`\n"
                       + "	FROM `bestelling` \n"
                       + "	INNER JOIN `voorwerp`  \n"
                       + "	ON bestelling.voorwerpid = voorwerp.id\n"
                       + "	INNER JOIN `voorwerp_assortiment`\n"
                       + "	ON voorwerp.naam = voorwerp_assortiment.voorwerpnaam\n"
                       + "	GROUP BY `naam`, `itemid`\n"
                       + "	ORDER BY `itemid`\n"
                       + ") as `QUICK`\n"
                       + "ON QUICK.itemid = item.id\n"
                       + "Where fotograafid = (Select `id` from `fotograaf` where email = ?)";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                email
        });

        String code = null;
        PreviewItem last = null;

        while (rs2.next())
        {
            String newcode = rs2.getString("code");
            Double prijs = rs2.getDouble("prijs");
            String title = rs2.getString("title");
            Date date = rs2.getDate("date");
            ItemSalesInfo i = new ItemSalesInfo(
                    rs2.getString("naam"), rs2.getInt("bedrukt"), rs2.getInt("verzonden"), rs2.getInt("totaal"), rs2.getInt("totaalprijs")
            );
            Item item = new Photo(prijs, code);

            if (last == null)
            {
                last = new PreviewItem(title, item, date);
                code = newcode;
            }

            //If the codes do not match, do not add it as sale, 
            // But save it to the list
            // And continue with the new object
            if (code != null && !code.equals(newcode))
            {
                //Else
                //Add the last item to the list
                previewItems.add(last);
                //And create a new last object
                last = new PreviewItem(title, item, date);
            }
            last.addSale(i);
            code = newcode;
        }
        if (last != null)
        {
            previewItems.add(last);
        }
        dab.close();
        return previewItems;
    }
}