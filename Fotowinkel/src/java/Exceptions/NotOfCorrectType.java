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
public class NotOfCorrectType extends Exception
{
    public NotOfCorrectType()
    {
        super("Upload failed. File was not an image.");
    }
}

