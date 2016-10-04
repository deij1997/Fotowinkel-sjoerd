/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.Photo;
import Managers.UploadManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

/**
 *
 * @author Rowan
 */
@WebServlet(name = "UploadServlet", urlPatterns =
    {
        "/UploadServlet"
})
public class UploadServlet extends HttpServlet
{
    final int MAX_FILE_SIZE = 5000 * 1024;
    final int MAX_REQUEST_SIZE = 5000 * 1024;
    final int THRESHOLD_SIZE = 5000 * 1024;
    public static final String FULL_UPLOAD_DIRECTORY = "/fullimages";
    public static final String PREVIEW_UPLOAD_DIRECTORY = "/previewimages";


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

            //Constructs the directory path to store upload file
            String uploadPath = getServletContext().getRealPath("") + File.separator + FULL_UPLOAD_DIRECTORY;
            //Creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
            {
                uploadDir.mkdir();
            }
            //Constructs the directory path to store upload file
            uploadPath = getServletContext().getRealPath("") + File.separator + PREVIEW_UPLOAD_DIRECTORY;
            //Creates the directory if it does not exist
            uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
            {
                uploadDir.mkdir();
            }

            //Get all files
            try
            {
                // parses the request's content to extract file data
                List formItems = upload.parseRequest((RequestContext) request);
                ArrayList<File> files = new ArrayList<File>();
                Iterator iter = formItems.iterator();

                // iterates over form's fields
                while (iter.hasNext())
                {
                    FileItem item = (FileItem) iter.next();
                    // processes only fields that are not form fields
                    if (!item.isFormField())
                    {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        files.add(new File(filePath));
                    }
                }

                //Call UploadManager to convert them
                ArrayList<Photo> photos = UploadManager.CreatePhotosFromUploads(files);
                //Upload all files
                UploadManager.UploadPhotos(photos);

                request.setAttribute("message", "Upload has been done successfully!");
            }
            catch (Exception ex)
            {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
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
