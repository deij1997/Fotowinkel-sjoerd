package Managers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martijn
 */
public class ParameterHolder {

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
}
