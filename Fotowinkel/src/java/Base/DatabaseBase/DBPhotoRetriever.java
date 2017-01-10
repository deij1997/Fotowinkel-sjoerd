/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.Photo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rowan
 */
public class DBPhotoRetriever extends DBBase
{
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
        endConnection();

        return photos;
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
        endConnection();
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
        endConnection();
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
        endConnection();

        return ret;
    }
}
