/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.Encoder;
import Exceptions.RandomiserFail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database class to handle user insertions and getters
 * 
 * @author Rowan
 */
public class DBUserHandler extends DBBase
{
    
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
            Helpers.BCrypt.hashpw(password, salt), email, Encoder.GetHash(email)
        };
        dab.sendQuery(query, parameters);
        endConnection();
    }

    /**
     * Inserts a customer into the database
     *
     * @param email the customer email to add
     * @throws SQLException
     * @throws Exceptions.RandomiserFail
     */
    protected void InsertCustomer(String email) throws SQLException, RandomiserFail
    {
        setUpConnection();
        String query = "Insert into `klant`(`hash`, `email`) VALUES (?,?)";
        String[] parameters = new String[]
        {
            Encoder.GetHash(email), email
        };
        dab.sendQuery(query, parameters);
    }

    public List<String> getAllPhotographers() throws SQLException
    {
        setUpConnection();
        List<String> photographers = new ArrayList<String>();

        String query = "Select * From `fotograaf`";
        ResultSet rs2 = dab.getData(query, null);
        while (rs2.next())
        {
            photographers.add(rs2.getString("email"));
        }
        endConnection();
        return photographers;
    }
}
