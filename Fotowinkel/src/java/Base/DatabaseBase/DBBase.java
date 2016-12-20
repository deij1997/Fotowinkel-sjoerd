/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import Base.LowerDatabase;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Base database class
 *
 * @author Rowan
 */
public class DBBase
{
    final static String DRIVER = "com.mysql.jdbc.Driver";
    private ReentrantLock lock = new ReentrantLock();
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
        lock.lock();
        try
        {
            dab = new LowerDatabase();
        }
        finally
        {
            lock.unlock();
        }
    }

    public final void endConnection() throws SQLException
    {
        lock.lock();
        try
        {
            dab.close();
        }
        finally
        {
            lock.unlock();
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        if (lock.isHeldByCurrentThread() && lock.isLocked())
        {
            lock.unlock();
        }

        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

}
