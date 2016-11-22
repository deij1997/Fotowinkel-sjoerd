/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Exceptions.NotAColorException;
import Helpers.ColorUtils;
import Helpers.ImageHelper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rowan
 */
@WebServlet(name = "imgServlet", urlPatterns =
    {
        "/imgServlet"
})
public class imgServlet extends HttpServlet
{

    public static String PREVIEW_UPLOAD_DIRECTORY = "/previewimages";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        PREVIEW_UPLOAD_DIRECTORY = request.getServletContext().getRealPath("") + "/previewimages";
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();

        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String strength = request.getParameter("light");

        BufferedImage img = ImageHelper.getImage(PREVIEW_UPLOAD_DIRECTORY + "/" + id + ".jpg", request.getServletContext().getRealPath("") + "/Images/notfound.png");

        try
        {
            if (type.equals("norml"))
            {
                //Return normal image
                ImageIO.write(img, "JPG", out);
            }
            else
            {
                if (type.equals("gree"))
                {
                    img = ImageHelper.ToGrayScale(img);
                    //Return greyscaled image
                    ImageIO.write(img, "JPG", out);
                }
                else
                {
                    if (type.equals("sepia"))
                    {
                        img = ImageHelper.ToSepia(img);
                        //Return sepia image
                        ImageIO.write(img, "JPG", out);
                    }
                    else
                    {
                        int lightenwith = ImageHelper.CALCULATE_LIGHT_LEVEL;;
                        if (strength != null && !strength.equals(""))
                        {
                            try
                            {
                                lightenwith = Integer.parseInt(strength);
                            }
                            catch (Exception e)
                            {
                                lightenwith = ImageHelper.CALCULATE_LIGHT_LEVEL;
                            }
                            finally
                            {
                                if (lightenwith != ImageHelper.CALCULATE_LIGHT_LEVEL)
                                {
                                    if (lightenwith < -255)
                                    {
                                        lightenwith = -255;
                                    }
                                    else
                                    {
                                        if (lightenwith > 255)
                                        {
                                            lightenwith = 255;
                                        }
                                    }
                                }
                            }
                        }

                        try
                        {
                            img = ImageHelper.ToColourScale(img, ColorUtils.getColor(type), lightenwith);
                            //Return coloured image
                            ImageIO.write(img, "JPG", out);
                        }
                        catch (NotAColorException ex)
                        {
                            //Return nothing.
                        }
                    }
                }
            }

        }
        finally
        {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
