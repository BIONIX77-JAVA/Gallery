package alex.falendysh.epam_exhibition.servlet;

import alex.falendysh.epam_exhibition.dao.impl.JdbcUserDAO;
import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.util.filters.Utils;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/profile/delete"})
public class DeleteUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(DeleteUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        UserDao<User> userDao = new JdbcUserDAO();
        userDao.delete(Objects.requireNonNull(user).getId());
        resp.sendRedirect(req.getContextPath() + "/logout");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }
}
