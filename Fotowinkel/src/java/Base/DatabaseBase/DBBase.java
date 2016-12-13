/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.LowerDatabase;
import java.sql.SQLException;

/**
 * Base database class
 * 
 * @author Rowan
 */
public class DBBase
{
    final static String DRIVER = "com.mysql.jdbc.Driver";
    protected LowerDatabase dab;
    private static Object k = null;
    
    public DBBase()
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
    
    public final void setUpConnection() throws SQLException
    {
        dab = new LowerDatabase();
    }
}
