/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.Encoder;
import Base.Photo;
import Base.ShoppingCart;
import Managers.ShoppingCartHolder;
import Managers.UserHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
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
            String klantcode = UserHandler.getUserAsString(request);
            Database db = new Database();

            ShoppingCart cart = ShoppingCartHolder.getInstance().getCurrentCart(request, response);

            //List<Photo> photos = db.GetPhotos(klantcode);
            double dtprice = 0;

            Map cc = cart.getAllProducts();
            Iterator it = cc.entrySet().iterator();
            
            
            while (it.hasNext())
            {
                Map.Entry pair = (Map.Entry) it.next();
                Photo p = db.GetPhoto(String.valueOf(pair.getKey()));
            
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
                int amnt = (Integer)pair.getValue();
                String amount = String.valueOf(amnt);
                
                String pricedetails = Photo.GetPriceAsString(p.GetPrice() * amnt);

                /* TODO output your page here. You may use following sample code. */
                out.println("<div class=\"col-md-12\">\n"
                            + "                        \n"
                            + "                        <div class=\"thumbnail\">\n"
                            + "                            <img id=\"myImg\" src='" + imgurl + "' style=\"width: 15%; max-height: 100%;\" class=\"pull-left\" alt='" + Encoder.HTMLEntityEncode(description) + "' onerror=\"this.onerror=null;this.src='Images/notfound.png'\">\n"
                            + "                            \n"
                            + "                            <div class=\"caption\">\n"
                            + "                                <h4 class=\"pull-right\">" + pricedetails + "</h4>\n"
                            + "                                <h4>\n"
                            + "                                    <a href=\"#\">" + Encoder.HTMLEntityEncode(title) + "</a>\n"
                            + "                                </h4>\n"
                            + "                                <p>" + Encoder.HTMLEntityEncode(description) + "</p>\n"
                            + "                                \n"
                            + "                                <div class=\"ratings\">\n"
                            + "                                    <p class=\"pull-right\">" + amount + " Stuks a la " + price + "&nbsp&nbsp</p>\n"
                            + "                                </div>\n"
                            + "                                \n"
                            + "                            </div>\n"
                            + "                            \n"
                            + "                        </div>\n"
                            + "                        \n"
                            + "                    </div>");
                dtprice += (p.GetPrice() * amnt);
            }
            String tprice = "€ " + String.format("%.2f", dtprice);
            out.println(
                    "                    \n"
                    + "                    <div class=\"col-sm-12 col-md-12\">\n"
                    + "                        \n"
                    + "                        <div class=\"col-sm-4 col-lg-12 col-md-4\">\n"
                    + "                            \n"
                    + "                            <div class=\"thumbnail\">\n"
                    + "                                \n"
                    + "                                <div class=\"caption\">\n"
                    + "                                    <h4 class=\"pull-left\">Totaalprijs</h4>\n"
                    + "                                    <h4 class=\"pull-right\">" + tprice + "</h4>\n"
                    + "                                </div>\n"
                    + "                                \n"
                    + "                                <div class=\"ratings\">\n"
                    + "                                    <p class=\"\"><a class=\"btn btn-primary\" target=\"_blank\" href=\"\">Afrekenen</a></p>\n"
                    + "                                    <!--<p> Quantity: <input type=\"number\" name=\"aantal\"style=\"width:50px;height:30px;\"></p>-->\n"
                    + "                                </div>\n"
                    + "                            \n"
                    + "                            </div>\n"
                    + "                        \n"
                    + "                        </div>\n"
                    + "                    \n"
                    + "                    </div>\n"
                    + "                \n"
                    + "                </div>");

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
}
