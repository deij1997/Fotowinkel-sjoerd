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
public class ItemSalesInfo
{
    private String objectname;
    private int printed;
    private int sent;
    private int total;
    private double totalprice;

    public ItemSalesInfo(String objectname, int printed, int sent, int total)
    {
        this.objectname = objectname;
        this.printed = printed;
        this.sent = sent;
        this.total = total;
    }
    
    public ItemSalesInfo(String objectname, int printed, int sent, int total, double totalprice)
    {
        this.objectname = objectname;
        this.printed = printed;
        this.sent = sent;
        this.total = total;
        this.totalprice = totalprice;
    }

    public String getObjectname()
    {
        return objectname;
    }

    public void setObjectname(String objectname)
    {
        this.objectname = objectname;
    }

    public int getPrinted()
    {
        return printed;
    }

    public void setPrinted(int printed)
    {
        this.printed = printed;
    }

    public int getSent()
    {
        return sent;
    }

    public void setSent(int sent)
    {
        this.sent = sent;
    }

    public int getTotal()
    {
        return total;
    }

    public double getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(double totalprice)
    {
        this.totalprice = totalprice;
    }
    
    public String getTotalPriceAsString()
    {
        return Photo.GetPriceAsString(totalprice);
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "ItemSalesInfo{" + "objectname=" + objectname + ", printed=" + printed + ", sent=" + sent + ", total=" + total + '}';
    }
}
