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
public class UploadFailed extends Exception
{
    public UploadFailed()
    {
        super("Upload failed. Try again, if the issue persists, contact an administrator.");
    }
}
