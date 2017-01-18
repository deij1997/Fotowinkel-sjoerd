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
public class ListedArticle extends Article
{
    private int minx;
    private int maxx;
    private int miny;
    private int maxy;
    private double strength;
    private String path;

    public ListedArticle(String name, double price)
    {
        super(name, price);
        this.path = "Images/" + name + ".jpg";
    }
    
    public ListedArticle(int minx, int maxx, int miny, int maxy, double strength, String name, double price)
    {
        this(name, price);
        this.minx = minx;
        this.maxx = maxx;
        this.miny = miny;
        this.maxy = maxy;
        this.strength = strength;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
    
    public int getMinx()
    {
        return minx;
    }

    public void setMinx(int minx)
    {
        this.minx = minx;
    }

    public int getMaxx()
    {
        return maxx;
    }

    public void setMaxx(int maxx)
    {
        this.maxx = maxx;
    }

    public int getMiny()
    {
        return miny;
    }

    public void setMiny(int miny)
    {
        this.miny = miny;
    }

    public int getMaxy()
    {
        return maxy;
    }

    public void setMaxy(int maxy)
    {
        this.maxy = maxy;
    }

    public double getStrength()
    {
        return strength;
    }

    public void setStrength(double strength)
    {
        this.strength = strength;
    }
}
