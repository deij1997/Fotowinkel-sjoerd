package Servlets;

import Base.Database;
import Base.Enums.ColorType;
import Base.Photo;
import Base.ShoppingCart;
import Base.ShoppingCartItem;
import Managers.ShoppingCartHolder;
import Managers.UserHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "AddToCartServlet", urlPatterns =
    {
        "/AddToCartServlet"
})
public class AddToCartServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        // Get an array of Cookies associated with this domain
        // And we also check if there is a cartID

        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie c : cookies)
            {
                if (c.getName().equals("cartID"))// cartID found, now we send the value
                {
                    ShoppingCart cart = ShoppingCartHolder.getInstance().getCartByID(c.getValue());
                    if (cart == null)//checking if the cartID is one that we know
                    {
                        //disregard our current cartID and get a new one
                        response.addCookie(new Cookie("cartID", ShoppingCartHolder.getRandomID()));
                    }
                    else
                    {
                        String hash = request.getParameter("it");
                        String color = request.getParameter("color");
                        if (color == null)
                        {
                            color = "000000";
                        }
                        color = "#" + color;
                        String articletype = request.getParameter("type");
                        if (articletype == null)
                        {
                            articletype = "standaard";
                        }

                        //Create a product from the given Hash
                        Photo product = new Database().GetPhoto(hash);
                        //If it's an existing product
                        if (product != null)
                        {
                            int amount = Integer.valueOf(request.getParameter("amnt"));
                            if (amount >= 0)
                            {
                                if (new Database().CheckIfPhotoBelongsToUser(hash, UserHandler.getUserAsString(request)))
                                {
                                    ShoppingCartItem e = new ShoppingCartItem(product, color, articletype, ColorType.getTypeFromString(request.getParameter("color")));

                                    if (amount == 0)
                                    {
                                        boolean succ = cart.RemoveItemFromBasket(e);
                                        String success = succ ? "successfully" : "unsuccessfully";
                                        if (succ)
                                        {
                                            out.println("removed " + product.GetTitle() + " (" + e.getArticle() + ") " + success);
                                        }
                                    }
                                    else
                                    {
                                        cart.AddItemToBasket(e, amount);
                                        out.println("added " + amount + " " + e.getArticle() + " of '" + product.GetTitle() + "' successfully");
                                    }
                                }
                                else
                                {
                                    out.println("you're not allowed to do that.");
                                }
                            }
                            else
                            {
                                out.println("invalid amount");
                            }
                        }
                        else
                        {
                            out.println("product is null: " + request.getParameter("code"));
                        }
                    }
                    break;
                }
            }

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
        try
        {
            processRequest(request, response);
        }
        catch (SQLException ex)
        {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
