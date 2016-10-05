/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rowan
 */
public class RandomiserFail extends Exception
{
    public RandomiserFail()
    {
        super("Randomisation of code failed");
    }
}
