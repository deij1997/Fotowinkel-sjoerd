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
    private static String url1 = "jdbc:mysql://188.166.100.75:3306/S32CDB";
    private static String user1 = "S32C";
    private static String pass1 = "proftaak";
    private Connection con;

    public Connection getCon()
    {
        return con;
    }

    public Database()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url1, user1, pass1);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //Sets the connection that needs to be used when collecting data from the database
    //Returns the connection
    public static Connection SetConnection() throws SQLException
    {
        Connection connection;
        return connection = DriverManager.getConnection(url1, user1, pass1);
    }

    //Test the connection of the database using a simple query
    //Return a boolean indicating if the test was succesfull
    private boolean TestConnection()
    {
        boolean succes = false;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url1, user1, pass1);
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
        Connection con = null;
        Statement state1 = null;
        ResultSet rs1 = null;
        PreparedStatement state2 = null;
        ResultSet rs2 = null;
        String query = "Select * From Item Where Naam Like '" + code + "%'";
        try
        {
            con = Database.SetConnection();
            state2 = con.prepareStatement(query);
            rs2 = state2.executeQuery();
            while (rs2.next())
            {
                photos.add(new Photo(rs2.getDouble("Prijs"), rs2.getString("Naam")));
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
}
