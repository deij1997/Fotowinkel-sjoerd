/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tu
 */
public class PreviewItem implements Comparable<PreviewItem>, Serializable
{
    private String title;
    private Item item;
    private Date date;
    private List<ItemSalesInfo> sales = new ArrayList<ItemSalesInfo>();

    public PreviewItem(String title, Item item, Date date)
    {
        this.title = title;
        this.item = item;
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public List<ItemSalesInfo> getSales()
    {
        return sales;
    }

    public void addSale(ItemSalesInfo sale)
    {
        this.sales.add(sale);
    }
    
    public void setSales(List<ItemSalesInfo> sales)
    {
        this.sales = sales;
    }
    
    public String getTotalAsString()
    {
        return Photo.GetPriceAsString(getTotal());
    }
    
    public double getTotal()
    {
        double price = 0;
        for (ItemSalesInfo i : sales)
        {
            price += i.getTotalprice() + i.getTotal() * item.GetPrice();
        }
        return price;
    }
    
    public String getSaleAsString(ItemSalesInfo sale)
    {
        return Photo.GetPriceAsString(sale.getTotalprice() + sale.getTotal() * item.GetPrice());
    }
    
    public String getMarkupTitleIfNeeded()
    {
        return sales.size() > 0 ? "<a>" + getTitle() + "</a>" : getTitle();
    }

    @Override
    public String toString()
    {
        return "PreviewItem{" + "title=" + title + ", item=" + item + ", date=" + date + '}';
    }

    @Override
    public int compareTo(PreviewItem o)
    {
        return date.compareTo(o.date);
    }

}
