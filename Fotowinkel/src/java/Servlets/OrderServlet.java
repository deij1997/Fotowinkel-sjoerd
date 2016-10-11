/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.Photo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesse
 */
@WebServlet(name = "OrderServlet", urlPatterns =
    {
        "/OrderServlet"
})
public class OrderServlet extends HttpServlet
{
    public static String FULL_UPLOAD_DIRECTORY = "/fullimages";
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

        FULL_UPLOAD_DIRECTORY = request.getServletContext().getRealPath("") + "/fullimages";
        PREVIEW_UPLOAD_DIRECTORY = request.getServletContext().getRealPath("") + "/previewimages";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            String klantcode = "555";

            Database db = new Database();

            List<Photo> photos = db.GetPhotos(klantcode);

            for (Photo p : photos)
            {
                String imgurl = p.getPreviewLocation();
                String price = p.GetPriceAsString();
                String title = "test";
                String description = "een mooie foto.";
                String amount = "1";
                
                /* TODO output your page here. You may use following sample code. */
                out.println("<div class=\"col-sm-4 col-md-12\">\n"
                            + "                        \n"
                            + "                        <div class=\"thumbnail\">\n"
                            + "                            <img src=\" " + imgurl + " \" style=\"max-width: 15%\" class=\"pull-left\" alt=\"\">\n"
                            + "                            \n"
                            + "                            <div class=\"caption\">\n"
                            + "                                <h4 class=\"pull-right\">" + price + "</h4>\n"
                            + "                                <h4>\n"
                            + "                                    <a href=\"#\">" + title + "</a>\n"
                            + "                                </h4>\n"
                            + "                                <p>" + description + "</p>\n"
                            + "                                \n"
                            + "                                <div class=\"ratings\">\n"
                            + "                                    <p class=\"pull-right\"> Stuks </p>\n"
                            + "                                    <p class=\"pull-right\">" + amount + "&nbsp&nbsp</p>\n"
                            + "                                </div>\n"
                            + "                                \n"
                            + "                            </div>\n"
                            + "                            \n"
                            + "                        </div>\n"
                            + "                        \n"
                            + "                    </div>");
            }

        }
        catch (Exception ehroar)
        {
            out.println(ehroar.getMessage());
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
/*
<div class="col-sm-4 col-md-12">
                        
                        <div class="thumbnail">
                            <img src="http://www.smart-promotions.nl/upload/800x600/Chocolate_Chip_Koekje.jpg" style="max-width: 15%" class="pull-left" alt="">
                            
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4>
                                    <a href="#">koekske</a>
                                </h4>
                                <p>lekker koekske.</p>
                                
                                <div class="ratings">
                                    <p class="pull-right"> Stuks </p>
                                    <p class="pull-right">3&nbsp&nbsp</p>
                                </div>
                                
                            </div>
                            
                        </div>
                        
                    </div>
*/
