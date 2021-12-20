package alex.falendysh.epam_exhibition.util.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"/profile", "/profile/*", "/application", "/manage", "/manage/*"})
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (session.getAttribute("userInSession") == null) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
