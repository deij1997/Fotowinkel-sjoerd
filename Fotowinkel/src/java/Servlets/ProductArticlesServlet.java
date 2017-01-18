/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.ListedArticle;
import Base.ShoppingCart;
import Base.ShoppingCartItem;
import Managers.ShoppingCartHolder;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
    private static List<ListedArticle> articles = null;

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
            if (articles == null)
            {
                Database db = new Database();
                articles = db.GetArticles();
            }

            //Get photo code
            String id = request.getParameter("id");
            //Get if first load
            String ft = request.getParameter("ft");
            boolean firsttime = ft == null ? false : ft.equals("true");

            //Load in the shopping cart if it's the first time
            if (firsttime)
            {
                Cookie[] cookies = request.getCookies();
                ShoppingCart cart = null;
                if (cookies != null)
                {
                    for (Cookie c : cookies)
                    {
                        if (c.getName().equals("cartID"))// cartID found, now we send the value
                        {
                            cart = ShoppingCartHolder.getInstance().getCartByID(c.getValue());
                            break;
                        }
                    }
                }
                if (cart == null)
                {
                    response.addCookie(new Cookie("cartID", ShoppingCartHolder.getRandomID()));
                }
                else
                {
                    //Get all the added products
                    Map ps = cart.getAllProducts();
                    Object[] products = ps.keySet().toArray();
                    Object[] amounts = ps.values().toArray();

                    for (int i = 0; i < ps.size(); i++)
                    {
                        //Make sure the shopping cart item supports the articles as well
                        ShoppingCartItem product = (ShoppingCartItem) products[i];

                        if (product.getProduct().GetCode().equals(id))
                        {
                            String article = product.getArticle().toLowerCase();
                            ListedArticle a = null;
                            for (ListedArticle art : articles)
                            {
                                if (art.getName().toLowerCase().equals(article))
                                {
                                    a = art;
                                    break;
                                }
                            }
                            if (a != null)
                            {
                                String color = product.getColorHex();
                                color = color.replace("#", "");
                                Integer orderamount = (Integer) amounts[i];

                                out.println("<div class=\"article\">\n"
                                            + "                                        <div class=\"center-article\">\n"
                                            + "                                            <img src=\"ProductArticleViewServlet?article=" + article + "&str=" + a.getStrength() + "&id=" + id + "&color=" + color + "&x1=" + a.getMinx() + "&y1=" + a.getMiny() + "&x2=" + a.getMaxx() + "&y2=" + a.getMaxy() + "\" class=\"article-preview\" alt=\"" + a.getName() + "\"/>\n"
                                            + "                                        </div>\n"
                                            + "                                        <input type=\"number\" min=\"0\" value=\"" + orderamount + "\"/>\n"
                                            + "                                    </div>");
                            }
                        }
                    }
                }
            }
            else
            {
                //Get color code
                String color = request.getParameter("color");
                if (id != null && !id.isEmpty())
                {
                    if (color == null || color.isEmpty() || color.equals("undefined"))
                    {
                        color = "000000";
                    }

                    for (ListedArticle a : articles)
                    {
                        out.println("<div class=\"article\">\n"
                                    + "                                        <div class=\"center-article\">\n"
                                    + "                                            <img src=\"ProductArticleViewServlet?article=" + a.getName() + "&str=" + a.getStrength() + "&id=" + id + "&color=" + color + "&x1=" + a.getMinx() + "&y1=" + a.getMiny() + "&x2=" + a.getMaxx() + "&y2=" + a.getMaxy() + "\" class=\"article-preview\" alt=\"" + a.getName() + "\"/>\n"
                                    + "                                        </div>\n"
                                    + "                                        <input type=\"number\" min=\"0\" value=\"0\"/>\n"
                                    + "                                    </div>");
                    }
                    //Show other items of photo code in those divs
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
