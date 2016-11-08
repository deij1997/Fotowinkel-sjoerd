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
    private PreparedStatement statement = null;
    private ResultSet result = null;

    public LowerDatabase() throws SQLException
    {
        if (con == null || con.isClosed())
        {
            con = DriverManager.getConnection(CONNECTION_URL, ACCOUNT_NAME, PASSWORD);
        }
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
        statement = con.prepareStatement(query);
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
    protected void close() throws SQLException
    {
        if (result != null || !result.isClosed())
        {
            result.close();
        }
        if (statement != null || !statement.isClosed())
        {
            statement.close();
        }
        if (con != null || !con.isClosed())
        {
            con.close();
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        this.close();
        super.finalize();
    }
}
