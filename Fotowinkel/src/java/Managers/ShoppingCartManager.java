/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import java.util.Map;
import paypalnvp.core.PayPal;
import paypalnvp.core.PayPal.Environment;
import paypalnvp.fields.Currency;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentItem;
import paypalnvp.profile.BaseProfile;
import paypalnvp.profile.Profile;
import paypalnvp.request.GetBalance;
import paypalnvp.request.SetExpressCheckout;

/**
 *
 * @author Rowan
 */
public class ShoppingCartManager
{
    public static void main(String[] args)
    {
        TestPaypal();
    }

    public static void TestPaypal()
    {
        /* set user - these are your credentials from paypal */
        Profile user = new BaseProfile.Builder("user name", "password").signature("AfcWX...").build();

        /* create new instance of paypal nvp */
        PayPal pp = new PayPal(user, Environment.SANDBOX);

        /* create items (items from a shopping basket) */
        PaymentItem item1 = new PaymentItem();
        item1.setAmount("12.00");

        PaymentItem item2 = new PaymentItem();
        item2.setAmount("4.30");
        item2.setDescription("Stuff");

        PaymentItem[] items =
        {
            item1, item2
        };

        /* create payment (now you can create payment from the items) */
        Payment payment = new Payment(items);
        payment.setCurrency(Currency.GBP);

        /* create set express checkout - the first paypal request */
        SetExpressCheckout setEC = new SetExpressCheckout(payment, "https://www.return_url.com", "https://www.cancel_url.com");

        /* send request and set response */
        pp.setResponse(setEC);

        /* now you have set express checkout containting
        request and response as well */
        Map<String, String> response = setEC.getNVPResponse();
        
        System.out.println(response);

        /* create get balance */
        GetBalance balance = new GetBalance();

        /* send request and set response */
        pp.setResponse(balance);

        /* get response */
        response = balance.getNVPResponse();
        System.out.println(response);
        
        
        System.out.println("Done");
    }
}
