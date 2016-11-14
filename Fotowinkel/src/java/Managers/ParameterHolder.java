package Managers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martijn
 */
public class ParameterHolder {
    
    private static int ProductAmount = 0;

    public static void setViewingProduct(String product, HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("product", product));
    }

    public static Cookie getViewingProduct(HttpServletRequest request) {
        Cookie product = null;
        for (Cookie k : request.getCookies()) {
            if (k.getName().equals("product")) {
                product = k;
                break;
            }
        }
        return product;
    }
    
    public static void setProductAmount(int am)
    {
        ProductAmount = am;
    }
    
    public static int getProductAmount()
    {
        return ProductAmount;
    }
    
    public static String getUserID()
    {
        return "e";
    }
    
}
