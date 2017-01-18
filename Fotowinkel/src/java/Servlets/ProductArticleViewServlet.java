/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Helpers.ColorUtils;
import Helpers.ImageHelper;
import Helpers.ImageUtils;
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
@WebServlet(name = "ProductArticleViewServlet", urlPatterns =
    {
        "/ProductArticleViewServlet"
})
public class ProductArticleViewServlet extends HttpServlet
{
    public static String PREVIEW_UPLOAD_DIRECTORY = "/previewimages";
    public static String ARTICLE_DIRECTORY = "/Images";

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
        ARTICLE_DIRECTORY = request.getServletContext().getRealPath("") + "/Images";

        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        try
        {
            String type = request.getParameter("color");
            String id = request.getParameter("id");
            String article = request.getParameter("article");
            int minx = Integer.valueOf(request.getParameter("x1"));
            int miny = Integer.valueOf(request.getParameter("y1"));
            int maxx = Integer.valueOf(request.getParameter("x2"));
            int maxy = Integer.valueOf(request.getParameter("y2"));
            String strength = request.getParameter("str");
            if (strength == null)
            {
                strength = "0";
            }
            double str = Double.valueOf(strength);

            BufferedImage img = ImageHelper.getImage(PREVIEW_UPLOAD_DIRECTORY + "/" + id + ".jpg", request.getServletContext().getRealPath("") + "/Images/notfound.png");

            if (!type.equals("000000"))
            {
                img = ImageHelper.ToColourScale(img, ColorUtils.getColor(type), ImageHelper.CALCULATE_LIGHT_LEVEL);
            }
            //Wrap the image
            img = ImageUtils.wrapImage(img, str, true, 0);
            if (!article.toLowerCase().equals("standaard"))
            {
                //copy the image over the article
                BufferedImage iarticle = ImageHelper.getImage(ARTICLE_DIRECTORY + "/" + article + ".jpg", request.getServletContext().getRealPath("") + "/Images/notfound.png");
                img = ImageUtils.copyOverImage(iarticle, img, minx, miny, maxx, maxy, str, 0);
            }
            ImageIO.write(img, "PNG", out);
        }
        catch (Exception e)
        {
            
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
