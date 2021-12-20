package alex.falendysh.epam_exhibition.util.filters;

import alex.falendysh.epam_exhibition.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class Utils {

    private final static Logger log = Logger.getLogger(Utils.class);
    public final static String USER_NAME_ATTR = "userNameAttr";

    public static void setUserInSession(HttpSession session, Optional<Object> user) {
        session.setAttribute("userInSession", user);
    }

    public static User getUserInSession(HttpSession session) {
        if (session.getAttribute("userInSession") == null) return null;
        return (User) session.getAttribute("userInSession");
    }

    public static void setUTF8Encoding(ServletRequest req, ServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
    }

}
