/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesse
 */
public class Database
{
    private final static String url1 = "jdbc:mysql://web0095.zxcs.nl/u4951p4091_fotowinkel";
    private final static String user1 = "u4951p4091_prof";
    private final static String pass1 = "fotos";
    private final static String driver = "com.mysql.jdbc.Driver";
    private Connection con;

    public Connection getCon()
    {
        return con;
    }

    public Database()
    {
        createConnection();
    }

    public void createConnection()
    {
        try
        {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url1, user1, pass1);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Test the connection of the database using a simple query
    //Return a boolean indicating if the test was succesfull
    private boolean TestConnection()
    {
        boolean succes = false;
        try
        {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("Select * From Test");
            while (result.next())
            {
                System.out.print(result.getInt("lel"));
            }
            succes = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return succes;
    }

    //Test the connection of the database using the TestConnection method
    public void Test()
    {
        if (TestConnection())
        {
            System.out.print("Het werkt!!!!!!11111!!!");

        }
        else
        {
            System.out.print("QQQQQQQQQQQQQQQQQQQQQ, tranen vol gestacked!");
        }
    }

    public List<Photo> GetPhotos(String code) throws SQLException
    {
        List<Photo> photos = new ArrayList<Photo>();
        PreparedStatement state2 = null;
        ResultSet rs2 = null;
        String query = "Select * From `item` Where klantid Like '" + code + "%'";
        try
        {
            state2 = con.prepareStatement(query);
            rs2 = state2.executeQuery();
            while (rs2.next())
            {
                photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (rs2 != null)
            {
                rs2.close();;
            }
            if (state2 != null)
            {
                state2.close();
            }
            if (con != null)
            {
                con.close();
            }
        }

        return photos;

    }

    public List<Photo> GetPhotosByKlantHashedId(int Klantid) throws SQLException
    {
        List<Photo> photos = new ArrayList<Photo>();
        PreparedStatement state2 = null;
        ResultSet rs2 = null;
        String query = "Select * From item Where klanthashedid = " + Klantid + "";
        try
        {
            state2 = con.prepareStatement(query);
            rs2 = state2.executeQuery();
            while (rs2.next())
            {
                photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (rs2 != null)
            {
                rs2.close();;
            }
            if (state2 != null)
            {
                state2.close();
            }
            if (con != null)
            {
                con.close();
            }
        }

        return photos;

    }

    public List<Photo> GetAllPhotos() throws SQLException
    {
        List<Photo> photos = new ArrayList<Photo>();
        PreparedStatement state2 = null;
        ResultSet rs2 = null;
        String query = "Select * From `item`";
        try
        {
            state2 = con.prepareStatement(query);
            rs2 = state2.executeQuery();
            while (rs2.next())
            {
                photos.add(new Photo(rs2.getDouble("prijs"), rs2.getString("code"), rs2.getString("title"), rs2.getString("description")));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (rs2 != null)
            {
                rs2.close();;
            }
            if (state2 != null)
            {
                state2.close();
            }
            if (con != null)
            {
                con.close();
            }
        }

        return photos;
    }

    public void UpdatePhotos(List<Photo> photos) throws SQLException
    {
        PreparedStatement state2 = null;
        ResultSet rs2 = null;
        try
        {
            for (Photo p : photos)
            {
                String query = "UPDATE `item` SET `prijs`=" + p.price + ", `title`=" + p.GetTitle() + ",`description`=" + p.GetDescription() + " WHERE `code`=" + p.code;
                state2 = con.prepareStatement(query);
                rs2 = state2.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if (rs2 != null)
            {
                rs2.close();;
            }
            if (state2 != null)
            {
                state2.close();
            }
            if (con != null)
            {
                con.close();
            }
        }
    }

    public void UpdatePhoto(Photo photo) throws SQLException
    {
        List<Photo> photos = new ArrayList<Photo>();
        photos.add(photo);
        UpdatePhotos(photos);
    }
}
