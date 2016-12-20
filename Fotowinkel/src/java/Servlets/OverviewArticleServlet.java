/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.PreviewArticle;
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
@WebServlet(name = "OverviewArticleServlet", urlPatterns =
    {
        "/OverviewArticleServlet"
})
public class OverviewArticleServlet extends HttpServlet
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
            Database db = new Database();
            List<PreviewArticle> articles = db.GetArticles();

            out.println("<tr>\n"
                        + "                                    <td><b>Artikel</b></td>\n"
                        + "                                    <td><b>Prijs</td>\n"
                        + "                                    <td><b>Verzonden</b></td>\n"
                        + "                                    <td><b>Geprint</b></td>\n"
                        + "                                    <td><b>Verkocht</b></td>\n"
                        + "                                    <td><b>In voorraad</b></td>\n"
                        + "                                    <td><b>Totaal winst</b></td>\n"
                        + "                                </tr>\n");

            for (PreviewArticle a : articles)
            {

                out.println("<tr> \n"
                            + "                                    <td>" + a.getName() + "</td>\n"
                            + "                                    <td>" + a.getPriceAsString()+ "</td>\n"
                            + "                                    <td>" + a.getSent()+ "</td>\n"
                            + "                                    <td>" + a.getPrinted()+ "</td>\n"
                            + "                                    <td>" + a.getSold()+ "</td>\n"
                            + "                                    <td>" + a.getTotal()+ "</td>\n"
                            + "                                    <td>" + a.getTotalPriceAsString()+ "</td>\n"
                            + "                                </tr>");
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
