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
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tu
 */
@WebServlet(name = "ProductsServlet", urlPatterns =
    {
        "/ProductsServlet"
})
public class ProductsServlet extends HttpServlet
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

            Database db = new Database();

            List<Photo> photos = db.GetAllPhotos();

            for (Photo p : photos)
            {
                String imgurl = p.getPreviewLocation();
                String price = p.GetPriceAsString();
                String title = p.GetTitle();
                String description = p.GetDescription();
                if (title == null)
                {
                    title = "Geen titel opgegeven";
                }
                if (description == null)
                {
                    description = "Zonder beschrijving";
                }

                /* TODO output your page here. You may use following sample code. */
                out.println("<div class=\"col-sm-4 col-lg-4 col-md-4\">\n"
                            + "                        <div class=\"thumbnail\" style=\"max-height: 380px\">\n"
                            + "                            <img src=\"" + imgurl + "\" style=\"width: 99%\" alt=\"\" onerror=\"this.onerror=null;this.src='Images/notfound.png'\">\n"
                            + "                            <div class=\"caption\">\n"
                            + "                                <h4 class=\"pull-right\">" + price + "</h4>\n"
                            + "                                <h4><a href=\"#\">" + title + "</a>\n"
                            + "                                </h4>\n"
                            + "                                <div style=\"text-overflow: ellipsis; max-height: 70%\">" + description + "</div>\n"
                            + "                            </div>\n"
                            + "                            <div class=\"ratings\">\n"
                            + "                                <p class=\"pull-right\"><a class=\"btn btn-primary\" target=\"_blank\" href=\"\">Bestel</a></p>\n"
                            + "                                <p> Quantity: <input type=\"number\" name=\"aantal\"style=\"width:50px;height:30px;\"></p>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>");
            }

        }
        catch (SQLException ehroar)
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
