/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Managers.Mail.MailManager;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class MailTest
{

    public MailTest()
    {
    }

    @Test
    public void SendTestMail()
    {
        try
        {
            new MailManager().Mail("jessedeij@hotmail.com", "test", "test");
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }
}
