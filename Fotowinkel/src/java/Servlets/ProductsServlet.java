/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.Encoder;
import Base.Photo;
import Managers.UserHandler;
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
            String id = UserHandler.getUserAsString(request);
            List<Photo> photos = db.GetPhotosByKlantHashedId(id);

            if (photos.isEmpty())
            {
                throw new Exception("<b>Geen fotos gevonden!</b>");
            }

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
                            + "                        <div class=\"thumbnail\">\n"
                            + "                            <div class='center'>"
                            + "                                  <img id=\"myImg\" src='" + imgurl + "' style=\"max-height: 100%;\" class=\"previewPhoto " + p.GetCode() + " \" alt='" + Encoder.HTMLEntityEncode(description) + "' "
                            + "                                     onerror=\"removeButtons(event);\"\n" + "\" > </img>"
                            + "                                     <ol class=\"carousel-indicators custombutt\">\n"
                            + "                                         <li class=\"gree\"></li>\n"
                            + "                                         <li class=\"norml active\"></li>\n"
                            + "                                         <li class=\"sepia \"></li>\n"
                            + "                                     </ol>"
                            + "                             </div>\n"
                            + "                            <div class=\"caption\">\n"
                            + "                                <h4 class=\"pull-right\">" + price + "</h4>\n"
                            + "                                <h4><a href=\"#\">" + Encoder.HTMLEntityEncode(title) + "</a>\n"
                            + "                                </h4>\n"
                            + "                                <div style=\"text-overflow: ellipsis; max-height: 70%\">" + Encoder.HTMLEntityEncode(description) + "</div>\n"
                            + "                            </div>\n"
                            + "                            <div class=\"ratings\">\n"
                            + "                             <input id='color_"+p.GetCode()+"' type='hidden' value =\"norml\"/>"
                            + "                                <p class=\"pull-right\"><a id='" + p.GetCode() + "' class=\"btn addtocart btn-primary\" href=\"\">Bestel</a></p>\n"
                            + "                                <p> Aantal: <input id='" + p.GetCode() + "_amnt' type=\"number\" min='0' value='1' name=\"aantal\"style=\"width:50px;height:30px;\"></p>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>");
            }

        }
        catch (Exception ehroar)
        {
            out.println("<h1>Oh nee! :(</h1> \nEr ging iets fout, probeer het (later) opnieuw. <br /> \n<b>Error</b>: \n" + ehroar.getMessage());
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
