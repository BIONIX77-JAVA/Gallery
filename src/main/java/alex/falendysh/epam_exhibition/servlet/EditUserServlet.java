package alex.falendysh.epam_exhibition.servlet;

import alex.falendysh.epam_exhibition.dao.impl.JdbcUserDAO;
import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.util.filters.Utils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;

@WebServlet(urlPatterns = {"/profile/edit"})
public class EditUserServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = Utils.getUserInSession(session);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/editUserPage.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(USER_ID));
        String role = req.getParameter(ROLE);
        String email = req.getParameter(EMAIL);
        String firstname = req.getParameter(FIRST_NAME);
        String lastname = req.getParameter(LAST_NAME);
        String newPassword1 = req.getParameter("newPassword1");
        String newPassword2 = req.getParameter("newPassword2");
        HttpSession session = req.getSession();
        User userInSession = Utils.getUserInSession(session);
        if (!newPassword1.isEmpty() || !newPassword2.isEmpty()) {
            if (!newPassword1.equals(newPassword2)) {
                req.setAttribute("errorMessage", "passwords are not equals");
                doGet(req, resp);
                return;
            }
        }
        User newData = new User();
        UserDao<User> userDao = new JdbcUserDAO();
        newData.setId(id);
        newData.setEmail(email);
        newData.setRole();
        newData.setFirstName(firstname);
        newData.setLastName(lastname);
        newData.setPassword(Objects.requireNonNull(userInSession).getPassword());
        newData.setSalt(userInSession.getSalt());
        if (newPassword1.isEmpty()) userDao.update(newData);
        else userDao.update(newData);
        Utils.setUserInSession(session, java.util.Optional.of(newData));
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
