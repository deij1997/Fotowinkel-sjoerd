package Servlets;

import Helpers.WaterMarker;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

@WebServlet(name = "CropperServlet", urlPatterns =
    {
        "/CropperServlet"
})
public class CropperServlet
        extends HttpServlet
{
    final int MAX_FILE_SIZE = 5120000;
    final int MAX_REQUEST_SIZE = 5120000;
    final int THRESHOLD_SIZE = 5120000;
    private static BufferedImage WaterMarkImage;
    public static String FULL_UPLOAD_DIRECTORY = "/fullimages";
    public static String PREVIEW_UPLOAD_DIRECTORY = "/previewimages";
    public static String WATERMARK_LOCATION;
    String image_in_file;
    private static final long serialVersionUID = 1L;
    String name = null;
    String fileType = null;
    String fileTypeandURL = null;
    String extension = null;
    String datetime = null;
    String image = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        this.image_in_file = request.getParameter("image_file");
        this.name = request.getParameter("image_name");
        FULL_UPLOAD_DIRECTORY = request.getServletContext().getRealPath("") + "/fullimages/";
        PREVIEW_UPLOAD_DIRECTORY = request.getServletContext().getRealPath("") + "/previewimages/";
        WATERMARK_LOCATION = request.getServletContext().getRealPath("") + "/Images/watermark.png";

        int i = this.image_in_file.indexOf(":");
        if (i > 0)
        {
            this.fileTypeandURL = this.image_in_file.substring(i + 1);
        }
        int j = this.fileTypeandURL.indexOf(";");
        if (j > 0)
        {
            this.fileType = this.fileTypeandURL.substring(0, j);
        }
        int k = this.image_in_file.indexOf(",");
        if (k > 0)
        {
            this.image = this.image_in_file.substring(k + 1);
        }
        if (this.fileType.equalsIgnoreCase("image/jpeg"))
        {
            this.extension = "jpg";
        }
        else if (this.fileType.equalsIgnoreCase("image/png"))
        {
            this.extension = "png";
        }
        else if (this.fileType.equalsIgnoreCase("image/gif"))
        {
            this.extension = "gif";
        }
        byte[] imageByteArray = decodeImage(this.image);

        File yourFile = new File(FULL_UPLOAD_DIRECTORY + this.name + "." + this.extension);
        FileOutputStream imageOutFile = new FileOutputStream(yourFile, false);
        imageOutFile.write(imageByteArray);
        InputStream in = new ByteArrayInputStream(imageByteArray);
        if (WaterMarkImage == null)
        {
            File file = new File(WATERMARK_LOCATION);
            try
            {
                WaterMarkImage = ImageIO.read(file);
            }
            catch (IOException e)
            {
                System.out.println("[ERROR] Could not retrieve watermark image");
            }
        }
        BufferedImage photo = ImageIO.read(in);
        photo = WaterMarker.AddToImage(photo, WaterMarkImage, 500, true);
        File prevoutputfile = new File(PREVIEW_UPLOAD_DIRECTORY + "/" + this.name + ".jpg");
        ImageIO.write(photo, "jpg", prevoutputfile);

        imageOutFile.close();

        session.setAttribute("image", this.image_in_file);

        response.sendRedirect("Products.jsp");
    }

    public static byte[] decodeImage(String imageDataString)
    {
        return Base64.decodeBase64(imageDataString);
    }
}
