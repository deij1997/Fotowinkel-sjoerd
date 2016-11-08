package Servlets;

import Base.Database;
import Base.Item;
import Base.ShoppingCart;
import Managers.ShoppingCartHolder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martijn
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getMethod().equals("POST")) {
PrintWriter out = response.getWriter();
            Cookie cookie = null;
            Cookie[] cookies = null;
            ShoppingCart cart;
            // Get an array of Cookies associated with this domain
            // And we also check if there is a cartID
            cookies = request.getCookies();
            if (cookies != null) {
                boolean f = false;
                for (Cookie c : cookies) {

                    if (c.getName().equals("cartID"))// cartID found, now we send the value
                    {
                        f = true;
                        cart = ShoppingCartHolder.getInstance().GetCartByID(c.getValue());
                        if (cart == null)//checking if the cartID is one that we know
                        {
                            //disregard our current cartID and get a new one
                            cookie = new Cookie("cartID", ShoppingCartHolder.getRandomID());
                            response.addCookie(cookie);
                        } else {
                            Item product = new Database().GetPhoto(request.getParameter("code"));
                            if(product!=null) cart.AddItemToBasket(product, 0);
                            else out.println("product is null: "+request.getParameter("code"));
                            }
                        }
                    }

                }
            }
//        String productname = "n";
//        PrintWriter out = response.getWriter();
//        try {
//            /* TODO output your page here. You may use following sample code. */
//
////            out.println("<h3> Buy a" + productname + "</h3>");
////            out.println("                                <p class=\"pull-right\"><a class=\"btn btn-primary\" target=\"_blank\" href=\"\">Bestel</a></p>\n"
////                    + "                                <p> Quantity: <input type=\"number\" name=\"aantal\"style=\"width:50px;height:30px;\"></p>\n");
//
//        } finally {
//            out.close();
//        }
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
