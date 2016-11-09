/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Photo;
import Base.Upload;
import Managers.UploadManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Rowan
 */
@WebServlet(name = "UploadServlet", urlPatterns =
    {
        "/UploadServlet"
})
@MultipartConfig
public class UploadServlet extends HttpServlet
{
    final int MAX_FILE_SIZE = 5000 * 1024;
    final int MAX_REQUEST_SIZE = 5000 * 1024;
    final int THRESHOLD_SIZE = 5000 * 1024;
    public static String FULL_UPLOAD_DIRECTORY = "/fullimages";
    public static String PREVIEW_UPLOAD_DIRECTORY = "/previewimages";
    public static String WATERMARK_LOCATION;

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
        WATERMARK_LOCATION = request.getServletContext().getRealPath("") + "/Images/watermark.png";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try
        {
            //Handle the file data here
            //Checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request))
            {
                PrintWriter writer = response.getWriter();
                writer.println("Request does not contain upload data");
                writer.flush();
                return;
            }

            //Configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            //Creates the directory if it does not exist
            File uploadDir = new File(FULL_UPLOAD_DIRECTORY);
            if (!uploadDir.exists())
            {
                uploadDir.mkdir();
            }
            //Creates the directory if it does not exist
            uploadDir = new File(PREVIEW_UPLOAD_DIRECTORY);
            if (!uploadDir.exists())
            {
                uploadDir.mkdir();
            }

            //Get all files
            try
            {
                // parses the request's content to extract file data
                List<InputStream> files = new ArrayList<InputStream>();
                for (Part part : request.getParts())
                {
                    files.add(part.getInputStream());
                }

                //Call UploadManager to convert them
                List<Photo> photos = UploadManager.CreatePhotosFromUploads(files);

                //Create an Upload
                //TODO: ADD EMAIL FIELD AT THE UPLOAD
                //GET WITH "REQUEST.GETPARAMETER("NAMEOFTHEEMAILFIELD, NOT ID")
                Upload pupload = new Upload();

                pupload.AddPhotos(photos);
                pupload.Push(request);

                out.print("Upload successvol!");

            }
            catch (Exception ex)
            {
                out.println("<h1>Oh nee! :(</h1> \nEr ging iets fout, probeer het (later) opnieuw. <br /> \n<b>Error</b>: \n" + ex.getMessage());
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

}
