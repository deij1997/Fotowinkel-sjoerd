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
@WebServlet(name = "ProductsServlet", urlPatterns = {"/ProductsServlet"})
public class ProductsServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Database db = new Database();

            List<Photo> photos = db.GetAllPhotos();
            for (Photo p : photos) {
                out.println("<div class=\"col-sm-4 col-lg-4 col-md-4\">\n"
                        + "<div class=\"thumbnail\">"
                        + "<img src=\"http://www.ceramics-sa-cape.co.za/wp-content/uploads/2015/07/profile3-320x150.jpg\" alt=\"\">"
                        + "<div class=\"caption\">"
                        + "<h4 class=\"pull-right\">" + p.GetPrice()+"</h4>"
                        + "<h4><a href=\"#\">"+p.GetTitle()+"</a></h4>"
                        + "<p>"+p.GetDescription()+"</p>"
                        + "</div>"
                        + "<div class=\"ratings\">"
                        + "<p class=\"pull-right\"><a class=\"btn btn-primary\" target=\"_blank\" href=\"\">Buy</a></p>"
                        + "<p> Quantity: <input type=\"number\" name=\"aantal\"style=\"width:50px;height:30px;\"></p>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }

        }        
        catch (SQLException sql)
        {
            out.println(sql.getMessage());
        } 
        
        finally {
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
