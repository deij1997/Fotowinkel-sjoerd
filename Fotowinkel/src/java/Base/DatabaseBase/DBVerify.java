/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.DatabaseBase;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for all verification and hashing methods; to verify users, to check e-mails and codes and to get names and codes
 * 
 * @author Rowan
 */
public class DBVerify extends DBBase
{
    public boolean ValidateCredentials(String email, String password) throws SQLException
    {
        setUpConnection();
        String query = "Select * From `fotograaf` where `email` =?";
        String query2 = "select * from `admin` where `email` =?";

        boolean ret = false;
        if (Validate(email, password, query))
        {
            ret = true;
        }
        else
        {
            if (Validate(email, password, query2))
            {
                ret = true;
            }
        }

        dab.close();
        return ret;
    }

    private boolean Validate(String email, String password, String query) throws SQLException
    {
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                email
        });
        boolean ret = false;
        while (rs2.next())
        {
            if (Helpers.BCrypt.checkpw(password, rs2.getString("wachtwoord")))
            {
                ret = true;
                break;

            }
        }
        return ret;
    }
    
    public String GetName(String email) throws SQLException
    {
        setUpConnection();

        String query = "Select `naam` from `admin` where email=?";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                email
        });
        String name = "";
        while (rs2.next())
        {
            name = rs2.getString("naam");
        }
        dab.close();
        return name;
    }

    public String GetEmailFromHash(String Hash) throws SQLException, Exception
    {
        if (Hash.contains("@"))
        {
            return Hash;
        }

        String who = "", ret = "";
        if (CheckIfCustomerExists(Hash))
        {
            who = "klant";
        }
        else
        {
            if (CheckIfPhotographerExists(Hash))
            {
                who = "fotograaf";
            }
            else
            {
                throw new Exception("Given email-ID " + Hash + " does not exist!");
            }
        }
        setUpConnection();
        String query = "Select email From `" + who + "` where hash=?";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                Hash
        });
        while (rs2.next())
        {
            ret = rs2.getString("email");
            break;
        }

        dab.close();

        if (ret.equals(""))
        {
            throw new Exception("Given email-ID " + Hash + " does not exist!");
        }
        return ret;
    }
    
    public boolean CheckIfPhotographerExists(String emailorcode) throws SQLException
    {
        setUpConnection();
        String query = "Select id From `fotograaf` where " + (emailorcode.contains("@") ? "email=?" : "hash=?");
        dab.sendQuery(query, new String[]
              {
                  emailorcode
        });
        boolean ret = dab.hasFoundData();
        dab.close();
        return ret;
    }

    public boolean CheckIfAdministratorExists(String email) throws SQLException
    {
        setUpConnection();
        String query = "Select id From `admin` where email=?";
        dab.sendQuery(query, new String[]
              {
                  email
        });
        boolean ret = dab.hasFoundData();
        dab.close();
        return ret;
    }

    public boolean CheckIfCustomerExists(String emailorcode) throws SQLException
    {
        setUpConnection();
        String query = "Select * From `klant` where " + (emailorcode.contains("@") ? "email=?" : "id=?");
        dab.sendQuery(query, new String[]
              {
                  emailorcode
        });
        boolean ret = dab.hasFoundData();
        dab.close();
        return ret;
    }

    public boolean CheckIfPhotoBelongsToUser(String photocode, String user, boolean isPhotographer) throws SQLException
    {
        setUpConnection();

        String query = "Select id, hash, email from " + (isPhotographer ? "`fotograaf`" : "`klant`") + " where id = (select `" + (isPhotographer ? "fotograafid" : "klantid") + "` from `item` where code = ?)";
        ResultSet rs2 = dab.getData(query, new String[]
                            {
                                photocode
        });
        boolean belongsToAUser = false;
        while (rs2.next())
        {
            if (String.valueOf(rs2.getInt("id")).equals(user) || rs2.getString("hash").equals(user) || rs2.getString("email").equals(user))
            {
                belongsToAUser = true;
                break;
            }
        }
        dab.close();

        return belongsToAUser;
    }
}
