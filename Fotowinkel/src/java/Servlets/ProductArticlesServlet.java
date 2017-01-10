/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.ListedArticle;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
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
 * @author Rowan
 */
@WebServlet(name = "ProductArticlesServlet", urlPatterns =
    {
        "/ProductArticlesServlet"
})
public class ProductArticlesServlet extends HttpServlet
{

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
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try
        {
            //Get photo code
            String id = request.getParameter("id");
            //Get color code
            String color = request.getParameter("color");

            if (id != null && !id.isEmpty())
            {
                if (color == null || color.isEmpty() || color.equals("undefined"))
                {
                    color = "000000";
                }
                Database db = new Database();
                List<ListedArticle> articles = db.GetArticles();

                for (ListedArticle a : articles)
                {
                    //TODO
                    //Add price tag over div
                    out.println("<div class=\"article\">\n"
                                + "                                        <div class=\"center-article\">\n"
                                + "                                            <img src=\"ProductArticleViewServlet?str=" + a.getStrength() + "&id=" + id + "&color=" + color + "&x1=" + a.getMinx() + "&y1=" + a.getMiny() + "&x2=" + a.getMaxx() + "&y2=" + a.getMaxy() + "\" class=\"article-preview\" alt=\"" + a.getName() + "\"/>\n"
                                + "                                        </div>\n"
                                + "                                        <input type=\"number\" min=\"0\" value=\"0\"/>\n"
                                + "                                    </div>");
                }

                //Show other items of photo code in those divs
            }
        }
        catch (Exception e)
        {
            printStackTrace();
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
