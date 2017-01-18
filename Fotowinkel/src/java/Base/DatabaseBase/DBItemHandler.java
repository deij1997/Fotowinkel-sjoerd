/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.Encoder;
import Base.Item;
import Base.ItemSalesInfo;
import Base.ListedArticle;
import Base.LowerDatabase;
import Base.Photo;
import Base.PreviewArticle;
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
        endConnection();
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
        endConnection();
    }

    public void InsertOrder(List<String> items, String customer, String name, String lastname, String country, String city, String street, String housenr, String postcode, String paymentmethod) throws SQLException, RandomiserFail, Exception
    {
        String query = null;
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
        endConnection();
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
        LowerDatabase dab = new LowerDatabase();
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
                    rs2.getString("naam"),
                    rs2.getInt("bedrukt"),
                    rs2.getInt("verzonden"),
                    rs2.getInt("totaal"),
                    rs2.getDouble("totaalprijs")
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

    /**
     * Gets the total sales profit
     *
     * @return
     * @throws SQLException
     */
    public double GetTotalSale() throws SQLException
    {
        setUpConnection();
        String query = "SELECT SUM(`iprijs`) + SUM(`prijs`) as `total` FROM `bestelling` \n"
                       + "INNER JOIN \n"
                       + "(\n"
                       + "    SELECT `prijs` as `iprijs`, `id` FROM `item`\n"
                       + ") as `item2`\n"
                       + "ON item2.id = bestelling.itemid\n"
                       + "INNER JOIN `voorwerp`\n"
                       + "ON voorwerpid = voorwerp.id\n"
                       + "INNER JOIN `voorwerp_assortiment`\n"
                       + "ON naam = voorwerp_assortiment.voorwerpnaam";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
        });
        double ret = 0.0;
        while (rs2.next())
        {
            ret = rs2.getDouble("total");
        }
        return ret;
    }

    public List<PreviewArticle> GetArticleSales() throws SQLException
    {
        setUpConnection();
        List<PreviewArticle> previewItems = new ArrayList<PreviewArticle>();
        String query = "SELECT *\n"
                       + "	FROM `voorwerp_assortiment`\n"
                       + "INNER JOIN\n"
                       + "(\n"
                       + "    SELECT SUM(`verzonden`) as `verzonden`,\n"
                       + "    SUM(`bedrukt`) as `bedrukt`, \n"
                       + "    COUNT(`id`) as `voorraad`,\n"
                       + "    COUNT(`orderid`) as `verkocht`,\n"
                       + "    `naam`\n"
                       + "    FROM\n"
                       + "    (\n"
                       + "    	SELECT voorwerp.id as `id`, `orderid`, `verzonden`, `bedrukt`, `naam` FROM `voorwerp`\n"
                       + "        LEFT JOIN `bestelling`\n"
                       + "        ON bestelling.voorwerpid = voorwerp.id\n"
                       + "    ) as `bestelling2`\n"
                       + "    GROUP BY `naam`\n"
                       + ") as `voorwerp2`\n"
                       + "ON voorwerp_assortiment.voorwerpnaam = voorwerp2.naam\n"
                       + "GROUP BY voorwerpnaam";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
        });

        while (rs2.next())
        {
            previewItems.add(new PreviewArticle(rs2.getString("naam"), rs2.getDouble("prijs"), rs2.getInt("verkocht"), rs2.getInt("voorraad"), rs2.getInt("verzonden"), rs2.getInt("bedrukt")));
        }
        endConnection();
        return previewItems;
    }

    public List<ListedArticle> GetArticles() throws SQLException
    {
        setUpConnection();
        List<ListedArticle> articles = new ArrayList<ListedArticle>();
        String query = "SELECT * FROM `voorwerp_assortiment`";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
        });

        while (rs2.next())
        {
            articles.add(new ListedArticle(rs2.getInt("minx"),
                                           rs2.getInt("maxx"),
                                           rs2.getInt("miny"),
                                           rs2.getInt("maxy"),
                                           rs2.getDouble("strength"),
                                           rs2.getString("voorwerpnaam"), rs2.getDouble("prijs")));
        }
        endConnection();
        return articles;
    }
}
