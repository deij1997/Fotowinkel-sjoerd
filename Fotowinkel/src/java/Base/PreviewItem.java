/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.util.Date;

/**
 *
 * @author Tu
 */
public class PreviewItem implements Comparable<PreviewItem>
{
    private String title;
    private Item item;
    private Date date;

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
