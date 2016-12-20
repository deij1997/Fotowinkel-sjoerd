/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author Rowan
 */
public class PreviewArticle
{
    private String name;
    private double price;
    private int sold;
    private int sent;
    private int printed;
    
    //Total being the amount of items, including sold
    private int total;
    private double totalprice;

    public PreviewArticle(String name, double price, int sold, int total, int sent, int printed)
    {
        this.name = name;
        this.price = price;
        this.sold = sold;
        this.total = total;
        this.sent = sent;
        this.printed = printed;
        this.totalprice = price * sold;
    }

    public int getSold()
    {
        return sold;
    }

    public void setSold(int sold)
    {
        this.sold = sold;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }
    
    public int getStock()
    {
        return getTotal() - getSold();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getSent()
    {
        return sent;
    }

    public void setSent(int sent)
    {
        this.sent = sent;
    }

    public int getPrinted()
    {
        return printed;
    }

    public void setPrinted(int printed)
    {
        this.printed = printed;
    }

    public double getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(int totalprice)
    {
        this.totalprice = totalprice;
    }
    
    public String getTotalPriceAsString()
    {
        return Photo.GetPriceAsString(getTotalprice());
    }

    public String getPriceAsString()
    {
        return Photo.GetPriceAsString(getPrice());
    }
}
