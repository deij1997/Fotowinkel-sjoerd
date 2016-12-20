/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rowan
 */
public class LowerDatabase
{
    final static String CONNECTION_URL = "jdbc:mysql://web0095.zxcs.nl/u4951p4091_fotowinkel";
    final static String ACCOUNT_NAME = "u4951p4091_prof";
    final static String PASSWORD = "fotos";
    static Connection con;
    static Integer connections = 0;
    private ResultSet result = null;

    public LowerDatabase() throws SQLException
    {
        if (con == null || con.isClosed())
        {
            con = DriverManager.getConnection(CONNECTION_URL, ACCOUNT_NAME, PASSWORD);
        }
        addConnections(1);
    }

    /**
     * Sends a query to the database
     *
     * @param query The query to send
     * @param parameters The query string parameters
     * @throws SQLException
     */
    public void sendQuery(String query, String[] parameters) throws SQLException
    {
        PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 0;
        if (parameters != null)
        {
            for (String s : parameters)
            {
                statement.setString(++i, s);
            }
        }

        if (query.toLowerCase().startsWith("insert into"))
        {
            statement.executeUpdate();
            result = statement.getGeneratedKeys();
        }
        else
        {
            result = statement.executeQuery();
        }
    }

    /**
     * Returns the data given by a query
     *
     * @param query The query that needs to be sent
     * @param parameters The query string parameters
     * @return A resultset containing the data received from the query
     * @throws SQLException
     */
    public ResultSet getData(String query, String[] parameters) throws SQLException
    {
        this.sendQuery(query, parameters);
        return result;
    }

    public ResultSet getMutatedData() throws SQLException
    {
        return getData();
    }

    /**
     * Returns the last resultset of data
     *
     * @return the resultset
     */
    public ResultSet getData()
    {
        return result;
    }

    /**
     * Returns whether the resultset contains data. False if null
     *
     * @return Whether the set is empty or not.
     * @throws SQLException
     */
    public boolean hasFoundData() throws SQLException
    {
        if (result == null)
        {
            return false;
        }
        boolean datafound = false;
        while (result.next())
        {
            datafound = true;
            break;
        }
        result.first();
        return datafound;
    }

    /**
     * Closes the connection to the database
     *
     * @throws SQLException
     */
    public void close() throws SQLException
    {
        if (result != null)
        {
            if (!result.isClosed())
            {
                result.close();
            }
        }
        addConnections(-1);
        if (con != null || !con.isClosed())
        {
            if (connections == 0)
            {
                con.close();
            }
        }
    }
    
    private synchronized void addConnections(int i)
    {
        connections += i;
    }
}
