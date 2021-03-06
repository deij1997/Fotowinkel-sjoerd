/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Database;
import Base.ItemSalesInfo;
import Base.Photo;
import Base.PreviewItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
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
@WebServlet(name = "OverviewProductServlet", urlPatterns
        = {
            "/OverviewProductServlet"
        })
public class OverviewProductServlet extends HttpServlet {

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
            String fotograaf = request.getParameter("selection");
            String language = request.getParameter("language");
            Database db = new Database();
            List<PreviewItem> items = db.GetFotograafItems(fotograaf);
            Collections.sort(items);
            String title = null;
            String price = null;
            String date = null;
            String total = null;
            String article = null;
            String send = null;
            String print = null;
            String errorPhotographer = null;

            if (language.equals("en")) {
                title = "Title";
                price = "Price";
                date = "Date";
                total = "Total";
                article = "Article";
                send = "Send";
                print = "Printed";
                errorPhotographer = "<h2>Oh no! :(</h2>"
                        + "<p>There are no photos sold from the selected photographer</p>";
            } else if (language.equals("nl")) {
                title = "Titel";
                price = "Prijs";
                date = "Datum";
                total = "Totaal";
                article = "Artikel";
                send = "Verzonden";
                print = "Geprint";
                errorPhotographer = "<h2>Oh nee! :(</h2>"
                        + "<p>Er zijn nog geen fotos verkocht door deze fotograaf</p>";
            }

            if (items != null) {
                if (!items.isEmpty()) {
                    out.println("                          <table class=\"table table-striped freespace tablestress\"> \n"
                            + "                                <tr>\n"
                            + "                                    <td><b>" + title + "</b></td>\n"
                            + "                                    <td><b>" + price + "</td>\n"
                            + "                                    <td><b>" + date + "</b></td>\n"
                            + "                                    <td><b>" + total + "</td>\n"
                            + "                                </tr>");

                    boolean iswhite = true;

                    for (PreviewItem item : items) {

                        out.println("                                  <table class=\"panel panel-default freespace\">"
                                + "                                <table class=\"table table-striped freespace lightenup tb" + (iswhite ? " white" : "") + "\">"
                                + "                                <tr data-toggle=\"collapse\" data-target=\"#" + item.hashCode() + "\" class=\"clickable\"> \n"
                                + "                                    <td><a>" + item.getTitle() + "</a></td>\n"
                                + "                                    <td>" + Photo.GetPriceAsString(item.getItem().GetPrice()) + "</td>\n"
                                + "                                    <td>" + item.getDate() + "</td>\n"
                                + "                                    <td>" + item.getTotalAsString() + "</td>\n"
                                + "                                </tr>     \n"
                                + "                                </table>"
                                + "                                <div id=\"" + item.hashCode() + "\" class=\"panel-body smallfreespace collapse\">\n"
                                + "                                    <div class=\"\">\n"
                                + "                                        <table class=\"table table-striped\"> \n"
                                + "                                            <tbody>\n"
                                + "                                                <tr>\n"
                                + "                                                    <td><b>" + article + "</b></td>\n"
                                + "                                                    <td><b>" + total + "</b></td>\n"
                                + "                                                    <td><b>" + send + "</b></td>\n"
                                + "                                                    <td><b>" + print + "</b></td>\n"
                                + "                                                    <td><b>" + price + "</b></td>\n"
                                + "                                                </tr>\n"
                                + "                                                \n");
                        iswhite = !iswhite;
                        for (ItemSalesInfo i : item.getSales()) {
                            out.println("                                                <tr> \n"
                                    + "                                                    <td>" + i.getObjectname() + "</td>\n"
                                    + "                                                    <td>" + i.getTotal() + "</td>\n"
                                    + "                                                    <td>" + i.getSent() + "</td>\n"
                                    + "                                                    <td>" + i.getPrinted() + "</td>\n"
                                    + "                                                    <td>" + item.getSaleAsString(i) + "</td>\n"
                                    + "                                                </tr>\n");
                        }
                        out.println("                                            </tbody>\n"
                                + "                                        </table>  \n"
                                + "                                    </div>\n"
                                + "                                </div>\n"
                                + "                             </table>"
                                + "                             </table>");
                    }
                } else {
                    out.println(errorPhotographer);
                }
            }
        } catch (Exception ehroar) {
            out.println("<h1>Oh nee! :(</h1> \nEr ging iets fout, probeer het (later) opnieuw. <br /> \n<b>Error</b>: \n" + ehroar.getMessage());

        } finally {
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
