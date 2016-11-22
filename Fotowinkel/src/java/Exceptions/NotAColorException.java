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
public class NotAColorException extends Exception
{
    public NotAColorException()
    {
        super("Given string was not a color");
    }
}
