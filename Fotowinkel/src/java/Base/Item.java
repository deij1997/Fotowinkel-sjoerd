/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Exceptions.RandomiserFail;

/**
 *
 * @author Rowan
 */
public class Item {

    protected String code = "Not specified";
    protected double price;

    /**
     * Basic constructor for Item
     *
     * @param price The price of the item
     * @param generateCode Whether to generate a code or not
     */
    public Item(double price, boolean generateCode) {
        if (generateCode) {

            try {
                GenerateNewCode();
            } catch (RandomiserFail ex) {
                throw new UnsupportedOperationException("GenerateNewCode gaat fout.");
            }
        }
    }

    /**
     * Basic constructor for Item
     *
     * @param price The price of the item
     * @param code The code for the item
     */
    public Item(double price, String code) {
        this.code = code;
        this.price = price;
    }

    /**
     * Sets the price of this item to a new one
     *
     * @param newPrice The new price to set the item price to
     */
    public void SetPrice(double newPrice) {
        this.price = newPrice;
    }

    public double GetPrice() {
        return this.price;
    }

    /**
     * Generates a new code for the item
     *
     * @return The new code of the product
     * @throws Exceptions.RandomiserFail
     */
    public String GenerateNewCode() throws RandomiserFail {
        String[] codes = Encoder.GenerateCodeStrings(1);
        this.code = codes[0];
        return code;
    }

    public String GetCode() {
        return this.code;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Item other = (Item) obj;
        return !((this.code == null) ? (other.code != null) : !this.code.equals(other.code));
    }
    
    
}
