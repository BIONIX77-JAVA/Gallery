//package alex.falendysh.epam_exhibition.servlet;
//
//import alex.falendysh.epam_exhibition.dao.impl.JdbcUserDAO;
//import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
//import alex.falendysh.epam_exhibition.exceptions.DBException;
//import alex.falendysh.epam_exhibition.model.User;
//import alex.falendysh.epam_exhibition.util.filters.Utils;
//import org.apache.log4j.Logger;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(urlPatterns = {"/login"})
//public class LoginServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private static final Logger log = Logger.getLogger(LoginServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginPage.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//
//        Optional<User> user = Optional.empty();
//        boolean hasError = false;
//        String errorMessage = null;
//
//        try {
//            UserDao<User> userDao = new JdbcUserDAO();
//            user = JdbcUserDAO.validateUser(email, password);
//            if (!user.isPresent()) {
//                hasError = true;
//                errorMessage = "login or password are invalid";
//            }
//        } catch (DBException e) {
//            errorMessage = e.getMessage();
//            hasError = true;
//            log.error(e.getMessage());
//        }
//
//        if (hasError) {
//            user = Optional.of(new User());
//            req.setAttribute("errorMessage", errorMessage);
//            req.setAttribute("user", user);
//            doGet(req, resp);
//        } else {
//            HttpSession session = req.getSession();
//            Utils.setUserInSession(session, user);
//            resp.sendRedirect(req.getContextPath() + "/profile");
//        }
//    }
//}
