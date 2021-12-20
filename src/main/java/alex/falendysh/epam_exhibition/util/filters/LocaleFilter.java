package alex.falendysh.epam_exhibition.util.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    private static final String LANG = "lang";
    private static final String LOCALE = "locale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (req.getParameter(LOCALE) != null) {
            session.setAttribute(LANG, req.getParameter(LOCALE));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
